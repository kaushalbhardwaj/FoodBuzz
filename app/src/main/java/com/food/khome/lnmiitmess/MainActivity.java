package com.food.khome.lnmiitmess;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.food.khome.lnmiitmess.Tools.SharedPreference;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceFav;
import com.food.khome.lnmiitmess.Tools.SharedPreferencePing;
import com.food.khome.lnmiitmess.Tools.SharedPreferencePingDay;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceWeek;

public class MainActivity extends AppCompatActivity {
    Button loginButton,signupButton;
    public static final String MyPREFERENCES = "UserInfo" ;
    SharedPreferences sharedpreferences;
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            SharedPreferenceWeek.putSharedPreferInfo(getApplicationContext(), "2");
            SharedPreferenceFav.putSharedPreferInfoFirst(getApplicationContext());
            SharedPreference.setDateDatabase(getApplicationContext(), "date02042016");
            SharedPreferencePing.putSharedPreferInfoFirst(getApplicationContext());
            SharedPreferencePingDay.putSharedPreferInfoFirst(getApplicationContext());


            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }


        /*SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {

            SharedPreferenceWeek.putSharedPreferInfo(getApplicationContext(), "2");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }*/
        /*SharedPreferences prefsI10 = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefsI10.getBoolean("firstTime", false)) {

            SharedPreferenceFav.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = prefsI10.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        else{


           // Toast.makeText(MainActivity.this, "fadadsfadf", Toast.LENGTH_SHORT).show();
        }*/
       /* SharedPreferences prefs3 = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs3.getBoolean("firstI", false)) {

            SharedPreference.setDateDatabase(getApplicationContext(), "date09022016");

            SharedPreferences.Editor editor = prefs3.edit();
            editor.putBoolean("firstI", true);
            editor.commit();
        }*/


       /* SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pre.getBoolean("firstTime", false)) {

            SharedPreferencePing.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = pre.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }*/
        /*SharedPreferences pre3 = PreferenceManager.getDefaultSharedPreferences(this);
        if(!pre3.getBoolean("firstTime", false)) {

            SharedPreferencePingDay.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = pre3.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }*/

       /* SharedPreferences prefsI33 = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefsI33.getBoolean("firstTime", false)) {

            //SharedPreferenceFav.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = prefsI33.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
*/


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i=new Intent(getApplicationContext(),MainPage.class);
                startActivity(i);
                finish();

                /*sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                UserInfo user1= SharedPreference.getSharedPreferInfo(getApplicationContext());
                if(user1.getUid()!=null)
                {

                    Intent i=new Intent(getApplicationContext(),MainPage.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                    finish();

                }*/
            }
        }, SPLASH_TIME_OUT);



    }
}
