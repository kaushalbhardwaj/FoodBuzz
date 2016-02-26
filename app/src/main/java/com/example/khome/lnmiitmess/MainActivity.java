package com.example.khome.lnmiitmess;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.SharedPreference;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceFav;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;
import com.example.khome.lnmiitmess.Tools.UserInfo;

public class MainActivity extends AppCompatActivity {
    Button loginButton,signupButton;
    public static final String MyPREFERENCES = "UserInfo" ;
    SharedPreferences sharedpreferences;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {

            SharedPreferenceWeek.putSharedPreferInfo(getApplicationContext(), "1");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
        SharedPreferences prefsI = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefsI.getBoolean("firstI", false)) {

            SharedPreferenceFav.putSharedPreferInfoFirst(getApplicationContext());

            SharedPreferences.Editor editor = prefsI.edit();
            editor.putBoolean("firstI", true);
            editor.commit();
        }
        else{


           // Toast.makeText(MainActivity.this, "fadadsfadf", Toast.LENGTH_SHORT).show();
        }




        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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

                }
            }
        }, SPLASH_TIME_OUT);



    }
}
