package com.food.khome.lnmiitmess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.food.khome.lnmiitmess.Tools.SharedPreference;
import com.food.khome.lnmiitmess.Tools.UserInfo;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        UserInfo user1=new UserInfo(null,null,null,null,null);
        SharedPreference.putSharedPreferInfo(getApplicationContext(),user1 );
        UserInfo u=SharedPreference.getSharedPreferInfo(getApplicationContext());
        //Toast.makeText(Logout.this, ""+u.getEmail()+" ok", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
