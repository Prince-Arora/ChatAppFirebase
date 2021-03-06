package com.example.laptop.chatappfirebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mToolbar;
//private DatabaseReference mdbref;
    private EditText mStatusget;
    private Button mStatusBut;
    private ProgressDialog mprogress;

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
       mToolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.status_app_bar_layout);
        setSupportActionBar(mToolbar);
        mAuth = FirebaseAuth.getInstance();
        mStatusget =(EditText)findViewById(R.id.edittextstatus);
        String status_value=getIntent().getStringExtra("status");
        mStatusget.setText(status_value);
        mprogress=new ProgressDialog(this);
        getSupportActionBar().setTitle("Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStatusBut=(Button)findViewById(R.id.status_upload_button);
       // mdbref=
        mStatusBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mprogress.setTitle("Upload Status");
                mprogress.setMessage("Please Wait...");
                mprogress.show();
                String getstatus=mStatusget.getText().toString();
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String uid = currentUser.getUid();
                myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("Status");
                myRef.setValue(getstatus).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful())
                   {
                       mprogress.dismiss();
                       finish();
                   }
                   else
                   {
                       mprogress.hide();
                       Toast.makeText(StatusActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                   }

                    }
                });

            }
        });


    }

    }

