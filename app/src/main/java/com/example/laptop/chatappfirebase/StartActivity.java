package com.example.laptop.chatappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

        private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mButton=(Button)findViewById(R.id.register_button);
    mButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent (StartActivity.this,RegisterActivity.class);
            startActivity(i);
        }
    });
    }

}
