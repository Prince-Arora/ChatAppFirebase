package com.example.laptop.chatappfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
private EditText mEmail;
private EditText mPassword;
private Button mButton;
private ProgressDialog mprogressdialog;
private android.support.v7.widget.Toolbar  mToolbar;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail=(EditText)findViewById(R.id.Edit1_register);
        mPassword=(EditText)findViewById(R.id.Edit2_register);
        mButton=(Button) findViewById(R.id.create_account_button);
        mToolbar=(android.support.v7.widget.Toolbar )findViewById(R.id.register_appbar);
        mprogressdialog=new ProgressDialog(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth=FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = mEmail.getText().toString();
                String Password = mPassword.getText().toString();

                if (!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Password)) {
                    mprogressdialog.setTitle("Registering User...");
                    mprogressdialog.setMessage("Please wait unti you are registered..");
                    mprogressdialog.setCanceledOnTouchOutside(false);
                    mprogressdialog.show();
                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mprogressdialog.dismiss();
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
