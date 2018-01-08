package com.example.laptop.chatappfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UsersActivity extends AppCompatActivity {
private Toolbar mToolbar;
private RecyclerView muserlist;
private DatabaseReference muser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        mToolbar=(Toolbar)findViewById(R.id.users_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Users List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        muserlist=(RecyclerView)findViewById(R.id.recycler_view);
        muserlist.setHasFixedSize(true);
        muserlist.setLayoutManager(new LinearLayoutManager(this));
        muser= FirebaseDatabase.getInstance().getReference().child("Users");
    }
    @Override
    protected void onStart()
    {
    super.onStart();
    FirebaseRecyclerAdapter<Users,UsersViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
            Users.class,R.layout.user_display,UsersViewHolder.class,muser) {
        @Override
        protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {
        viewHolder.SetName(model.GetName());
        }

    };
    muserlist.setAdapter(firebaseRecyclerAdapter);
    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder
    {
            View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }
        public void  SetName(String nm) {
            TextView mText = (TextView) mView.findViewById(R.id.user_text1);
            mText.setText(nm);

        }    }



}
