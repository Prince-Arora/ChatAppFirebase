package com.example.laptop.chatappfirebase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by LAPTOP on 05-01-2018.
 */

public class sectionsPageAdapter extends FragmentPagerAdapter {


    public sectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                RequestFragment req = new RequestFragment();
                return req;
            }
            case 1: {

                ChatsFragment chat = new ChatsFragment();
                return chat;
            }
            case 2: {
                FriendsFragment friend = new FriendsFragment();
                return friend;
            }
            default: {
                return null;
            }
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int pos) {
            switch (pos) {
            case 0:
                return "REQUESTS";
            case 1:
                return "CHATS";
            case 2:
                return "FRIENDS";
            default:
                return null;

        }
    }
}

