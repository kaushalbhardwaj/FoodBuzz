package com.food.khome.lnmiitmess;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by khome on 20/1/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);



    }
}
