package com.example.khome.lnmiitmess;

/**
 * Created by khome on 28/2/16.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;

/**
 * @author Nilanchala
 *         <p/>
 *         Broadcast reciever, starts when the device gets starts.
 *         Start your repeating alarm here.
 */
public class DeviceBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            AlarmReceiver.startAlarm(context);
            //Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
            System.out.println("got device boot");
        }
    }
}