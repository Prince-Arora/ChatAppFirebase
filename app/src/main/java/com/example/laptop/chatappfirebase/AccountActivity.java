package com.example.laptop.chatappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
   private FirebaseUser muser;
    private Button mbutton;
    private TextView disName;
    private TextView disStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        muser = FirebaseAuth.getInstance().getCurrentUser();
        mbutton=(Button)findViewById(R.id.button_changestatus);
        String curr_user_id=muser.getUid();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users").child(curr_user_id);
        disName=(TextView)findViewById(R.id.setting_displayname);
        disStatus=(TextView)findViewById(R.id.settings_displaystatus);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            String name=dataSnapshot.child("Name").getValue().toString();
                String Profile_pic=dataSnapshot.child("Profile_pic").getValue().toString();
                String status=dataSnapshot.child("Status").getValue().toString();
                String Thumb=dataSnapshot.child("thumbnail").getValue().toString();
                disName.setText(name);
                disStatus.setText(status);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(AccountActivity.this,StatusActivity.class);
                startActivity(i);
            }
        });
    }
    }

