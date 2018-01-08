package com.example.laptop.chatappfirebase;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
 private FirebaseAuth mAuth;

 private android.support.v4.view.ViewPager mViewPager;

 private TabLayout mTablayout;

 private sectionsPageAdapter mSectionsPageAdapter;

    private android.support.v7.widget.Toolbar  mToolbar;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mTablayout=(android.support.design.widget.TabLayout)findViewById(R.id.tabs_layout);
        mToolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.main_page_activity);
        mViewPager=(android.support.v4.view.ViewPager)findViewById(R.id.viewpager);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Cool Chat");
        mSectionsPageAdapter=new sectionsPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
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
             if(item.getItemId()==R.id.Account_settings_menu)
             {
               Intent i=new Intent(MainActivity.this,AccountActivity.class);
               startActivity(i);
             }
             if(item.getItemId()==R.id.main_menu_users)
             {
                 Intent i=new Intent(MainActivity.this,UsersActivity.class);
                 startActivity(i);
             }
             return true;
    }
}
