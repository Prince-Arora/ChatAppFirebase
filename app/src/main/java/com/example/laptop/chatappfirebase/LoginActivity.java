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

public class LoginActivity extends AppCompatActivity {
private EditText email;
private EditText pass;
private FirebaseAuth Auth;
private ProgressDialog prog;
private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Auth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.Edit1_login);
        pass=(EditText)findViewById(R.id.Edit2_login);
        prog=new ProgressDialog(this);
        mButton=(Button)findViewById(R.id.login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaill=email.getText().toString();
                String passs=pass.getText().toString();
                if (!TextUtils.isEmpty(emaill) || !TextUtils.isEmpty(passs)) {
                    prog.setTitle("Logging In User...");
                    prog.setMessage("Please wait for some time...");
                    prog.setCanceledOnTouchOutside(false);
                    prog.show();
                    Auth.signInWithEmailAndPassword(emaill, passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                prog.dismiss();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.addFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);

                            }
                            else
                            {
                                prog.hide();
                                Toast.makeText(LoginActivity.this, "No account exists..Please firstly register your account", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
