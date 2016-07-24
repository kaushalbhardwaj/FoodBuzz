package com.food.khome.lnmiitmess.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by khome on 4/3/16.
 */
public class SharedPreferencePing {

    public static final String MyPREFERENCES = "MealPing" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfoFirst(Context con)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();


        editor.putString("pm1", "01");
        editor.putString("pm2", "01");
        editor.putString("pm3", "01");
        editor.putString("pm4", "01");

        editor.commit();
        return true;

    }
    public static boolean putSharedPreferInfo(Context con,String s1,String s2)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(s1,s2);
        editor.commit();
        return true;

    }
    public static String getSharedPreferInfo(Context con,String s1)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String s2=sharedpreferences.getString(s1,null);
        return s2;
    }


}
