package com.example.laptop.chatappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private Button mButton;
    private ProgressDialog mprogressdialog;
    private android.support.v7.widget.Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail = (EditText) findViewById(R.id.Edit1_register);
        mPassword = (EditText) findViewById(R.id.Edit2_register);
        mName = (EditText) findViewById(R.id.edit_text_name);
        mButton = (Button) findViewById(R.id.create_account_button);
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.register_appbar);
        mprogressdialog = new ProgressDialog(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = mEmail.getText().toString();
                String Password = mPassword.getText().toString();
                final String Name = mName.getText().toString();
                if (!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Password) || TextUtils.isEmpty(Name)) {
                    mprogressdialog.setTitle("Registering User...");
                    mprogressdialog.setMessage("Please wait until you are registered...");
                    mprogressdialog.setCanceledOnTouchOutside(false);
                    mprogressdialog.show();
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = currentUser.getUid();
                                myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                                HashMap<String, String> values = new HashMap<>();
                                values.put("Name", Name);
                                values.put("Status", "hey! there I m using chat App");
                                values.put("Profile_pic", "default");
                                values.put("thumbnail", "default");
                                myRef.setValue(values).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                            i.addFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            mprogressdialog.hide();
                                            Toast.makeText(RegisterActivity.this, "Cannot sign in Please check the form and try again..", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }

                    });
                }
            }
        });
    }
}
