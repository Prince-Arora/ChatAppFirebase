package com.example.laptop.chatappfirebase;

/**
 * Created by LAPTOP on 07-01-2018.
 */

public class Users {
public String Name;
public String status;
public String iMageUrl;

public Users(){

    }
    public Users(String name, String status, String iMageUrl) {
        Name = name;
        this.status = status;
        this.iMageUrl = iMageUrl;
    }
    public String GetName()
    {
        return Name;
    }
    public String GetStatus()
    {
        return status;
    }
    public String ImageUrl()
    {
        return iMageUrl;
    }

}

