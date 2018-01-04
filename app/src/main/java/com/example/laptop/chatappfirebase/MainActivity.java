package com.example.laptop.chatappfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
 private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar  mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mToolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.main_page_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Cool Chat");

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       if(currentUser == null )
       {
           Intent startactivity=new Intent(MainActivity.this,StartActivity.class);
           startActivity(startactivity);
           finish();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
             super.onOptionsItemSelected(item);
             if(item.getItemId()==R.id.main_menu_logout)
             {

                 FirebaseAuth.getInstance().signOut();
                 Intent startactivity=new Intent(MainActivity.this,StartActivity.class);
                 startActivity(startactivity);
                 finish();
             }
             return true;
    }
}
