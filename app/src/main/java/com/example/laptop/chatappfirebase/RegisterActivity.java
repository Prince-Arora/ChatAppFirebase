package com.example.laptop.chatappfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmail=(EditText)findViewById(R.id.Edit1_register);
        mPassword=(EditText)findViewById(R.id.Edit2_register);
        mButton=(Button) findViewById(R.id.create_account_button);
        mAuth=FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
