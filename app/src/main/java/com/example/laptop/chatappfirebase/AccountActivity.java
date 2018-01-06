package com.example.laptop.chatappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Random;

public class AccountActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseUser muser;
    private Button mbutton;
    private TextView disName;
    private TextView disStatus;
    private Button mchangeimg;
    private static int GALLERY_PICK = 1;
    private ProgressDialog mprogress;
    private StorageReference mStorageRef;
private de.hdodenhof.circleimageview.CircleImageView disimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        muser = FirebaseAuth.getInstance().getCurrentUser();
        mbutton = (Button) findViewById(R.id.button_changestatus);
        mchangeimg = (Button) findViewById(R.id.button_changeImage);

        String curr_user_id = muser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(curr_user_id);
        disName = (TextView) findViewById(R.id.setting_displayname);
        disStatus = (TextView) findViewById(R.id.settings_displaystatus);
        disimg=(de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.profile_image);
        mprogress=new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("Name").getValue().toString();
                String Profile_pic = dataSnapshot.child("Profile_pic").getValue().toString();
                String status = dataSnapshot.child("Status").getValue().toString();
                String Thumb = dataSnapshot.child("thumbnail").getValue().toString();
                disName.setText(name);
                disStatus.setText(status);
                Picasso.with(AccountActivity.this).load(Profile_pic).into(disimg);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String status_value = disStatus.getText().toString();
                Intent i = new Intent(AccountActivity.this, StatusActivity.class);
                i.putExtra("status", status_value);
                startActivity(i);
            }
        });
        mchangeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Image"), GALLERY_PICK);

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageuri = data.getData();
    mprogress.setTitle("Uploading...");
    mprogress.setMessage("Wait for some time...");
    mprogress.show();


            CropImage.activity(imageuri).setAspectRatio(1,1)
                    .start(this);
            String uuiidd=muser.getUid();
            StorageReference fileref = mStorageRef.child("Profile_images").child(uuiidd +".jpg");
            fileref.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {

                        String downloadurl=task.getResult().getDownloadUrl().toString();
                        mDatabase.child("Profile_pic").setValue(downloadurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    mprogress.dismiss();

                                }
                                else
                                {
                                    Toast.makeText(AccountActivity.this, "Failed", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });

                    } else {
                        mprogress.hide();
                        Toast.makeText(AccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }



    }

}

