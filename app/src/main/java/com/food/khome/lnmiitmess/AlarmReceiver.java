package com.food.khome.lnmiitmess;

/**
 * Created by khome on 7/2/16.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.food.khome.lnmiitmess.Tools.SharedPreferenceWeek;

import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        //Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        System.out.println("inside alarm manager");
        Calendar cal=Calendar.getInstance();
        int a= cal.get(Calendar.DAY_OF_WEEK);
        if(a==Calendar.SUNDAY)
        {
            String s1= SharedPreferenceWeek.getSharedPreferInfo(context);
            System.out.println("got alarm manager  =="+s1);

            if(s1.equals("1")) {
                SharedPreferenceWeek.putSharedPreferInfo(context, "2");
            }
            else {
                SharedPreferenceWeek.putSharedPreferInfo(context, "1");
            }
        }
    }

    public static void startAlarm(Context con) {
        PendingIntent pendingIntent;
        AlarmManager manager = (AlarmManager) con.getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;
        Intent alarmIntent = new Intent(con, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(con, 0, alarmIntent, 0);

        /* Set the alarm to start at 10:30 AM */
        Date dat  = new Date();//initializes to now
        Calendar calendar_now = Calendar.getInstance();
        calendar_now.setTime(dat);
        Calendar calendar_alarm = Calendar.getInstance();
        calendar_alarm.setTime(dat);
        calendar_alarm.setTimeInMillis(System.currentTimeMillis());
        calendar_alarm.set(Calendar.HOUR_OF_DAY, 22);
        calendar_alarm.set(Calendar.MINUTE, 1);

        /*if(calendar_alarm.before(calendar_now)){//if its in the past increment
            calendar_alarm.add(Calendar.DATE,1);
        }*/
        //SET YOUR AlarmManager here





        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar_alarm.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY  , pendingIntent);
    }
}
/*public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        int a= MealInfo.getWeek();
        if(a== Calendar.SUNDAY)
        {
            String a2=SharedPreferenceWeek.getSharedPreferInfo(context);
            if(a2.equals("1"))
            {

                SharedPreferenceWeek.putSharedPreferInfo(context,"2");
            }
            else
            {
                SharedPreferenceWeek.putSharedPreferInfo(context,"1");

            }
        }
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
        Toast.makeText(context, "ok alarm manager", Toast.LENGTH_SHORT).show();
        setResultCode(Activity.RESULT_OK);
    }
}
*/