package com.food.khome.lnmiitmess.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by khome on 5/2/16.
 */

public class SharedPreferenceFav {

    public static final String MyPREFERENCES = "MealFav" ;
    public static SharedPreferences sharedpreferences;
    public static boolean putSharedPreferInfoFirst(Context con)
    {
        sharedpreferences = con.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();



        String a1,a2,a3;
        String idd1,idd2,idd3;
        for(int i=1;i<=2;i++)
        {
            for(int j=1;j<=7;j++)
            {
                for(int k=1;k<=4;k++)
                {
                    a1=i+"";
                    a2=j+"";
                    a3=k+"";
                    idd1="fm"+a1+a2+a3;
                    idd2="bm"+a1+a2+a3;
                    idd3="dm"+a1+a2+a3;
                    editor.putString(idd1, "10");
                    editor.putString(idd2, "10");
                    editor.putString(idd3, "10");


                }

            }

        }
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
