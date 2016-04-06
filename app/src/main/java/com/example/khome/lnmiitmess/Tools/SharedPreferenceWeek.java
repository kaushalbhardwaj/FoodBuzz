package com.example.khome.lnmiitmess.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by khome on 7/2/16.
 */
public class SharedPreferenceWeek {

    public static final String MyPREFERENCES = "Weekoe" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfo(Context con,String u)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("OddEven", u);
        editor.commit();
        return true;

    }
    public static String getSharedPreferInfo(Context con)
    {

        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        return sharedpreferences.getString("OddEven",null);
    }
}
