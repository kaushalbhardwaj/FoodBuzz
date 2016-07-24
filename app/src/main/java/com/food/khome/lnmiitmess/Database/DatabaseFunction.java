package com.food.khome.lnmiitmess.Database;

import android.content.Context;

import com.food.khome.lnmiitmess.DatabaseHandler;

import java.util.List;

/**
 * Created by khome on 5/2/16.
 */
public class DatabaseFunction {
    public static DatabaseHandler dh=null;
    public static List<MenuInfo> li;
    public static void addMenuItem(Context acon,MenuInfo mi)
    {
        dh=new DatabaseHandler(acon);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            //Toast.makeText(acon, "error sql exception", Toast.LENGTH_SHORT).show();

        }

        MenuInfo con=new MenuInfo();
        con.setTitle(mi.getTitle());
        con.setDetail(mi.getDetail());
        con.setItemId(mi.getItemId());
        dh.insert(con);

    }

    public static List<MenuInfo> getMenuItem(Context acon)
    {
        dh=new DatabaseHandler(acon);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            //Toast.makeText(acon, "error sql exception", Toast.LENGTH_SHORT).show();

        }

        li=dh.getData();
        return li;
    }
    public static MenuInfo getParData(Context acon,String id)
    {
        dh=new DatabaseHandler(acon);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            //Toast.makeText(acon, "error sql exception", Toast.LENGTH_SHORT).show();

        }

        MenuInfo mii=dh.getParticularData(id);
        return mii;
    }
    public static void updateMenuItem(Context acon,MenuInfo mi)
    {
        dh=new DatabaseHandler(acon);
        try {
            dh.open();
        }
        catch(Exception e)
        {
            //Toast.makeText(acon, "error sql exception", Toast.LENGTH_SHORT).show();

        }

        dh.update(mi);
    }
}

