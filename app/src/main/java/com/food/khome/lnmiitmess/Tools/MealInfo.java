package com.food.khome.lnmiitmess.Tools;

import java.util.Calendar;

/**
 * Created by khome on 7/2/16.
 */
public class MealInfo {
    public static Calendar calendar;
    public static String getMealInfo()
    {
        calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        //12 hour format
        int hour = calendar.get(Calendar.HOUR);
        //24 hour format
        int hourofday = calendar.get(Calendar.HOUR_OF_DAY);
        if(hourofday>=0&&hourofday<10)
        {
            return "breakfast";

        }
        else if(hourofday>=10&&hourofday<15)
        {
            return "lunch";

        }
        else if(hourofday>=15&&hourofday<19)
        {
            return "snacks";

        }
        else if(hourofday>=19&&hourofday<22)
        {
            return "dinner";

        }
        else
        {
            return "breakfastn";

        }

    }
    public static int getWeek()
    {
        return calendar.get(Calendar.DAY_OF_WEEK);

    }
}
