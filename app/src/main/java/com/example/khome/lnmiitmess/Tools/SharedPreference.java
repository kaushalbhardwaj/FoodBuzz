package com.example.khome.lnmiitmess.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.khome.lnmiitmess.signup;

/**
 * Created by khome on 3/2/16.
 */
public class SharedPreference {

    public static final String MyPREFERENCES = "UserInfo" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfo(Context con,UserInfo u)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("nameKey", u.getUname());
        editor.putString("emailKey", u.getEmail());
        editor.putString("rollKey", u.getRollno());
        editor.putString("uidKey",u.getUid());
        editor.putString("passKey", u.getPassword());
        editor.commit();
        return true;

    }
    public static UserInfo getSharedPreferInfo(Context con)
    {
        UserInfo user1=new UserInfo();

        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        user1.setUname(sharedpreferences.getString("nameKey", null));
        user1.setEmail(sharedpreferences.getString("emailKey", null));
        user1.setRollno(sharedpreferences.getString("rollKey", null));
        user1.setUid(sharedpreferences.getString("uidKey", null));
        user1.setPassword(sharedpreferences.getString("passKey", null));
        return user1;
    }

    public static boolean setDateDatabase(Context con,String s)
    {

        SharedPreferences sp = con.getSharedPreferences("DatabaseDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("DateData", s);

        editor.commit();
        return true;
    }

    public static String getDateDatabase(Context con)
    {

        SharedPreferences sp = con.getSharedPreferences("DatabaseDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        String s=sp.getString("DateData", null);
        return s;

    }
}
