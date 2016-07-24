package com.food.khome.lnmiitmess;

/**
 * Created by khome on 5/2/16.
 */

import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

import com.food.khome.lnmiitmess.Database.MenuInfo;
import com.food.khome.lnmiitmess.Database.MySqliteHelper;

import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;
public class DatabaseHandler {

    MySqliteHelper sh;
     SQLiteDatabase db;
    public DatabaseHandler(Context context)
    {
        sh=new MySqliteHelper(context);

    }
    public void open() throws SQLException
    {
        db=sh.getWritableDatabase();
    }
    public void close()
    {
        sh.close();
    }
    public long insert(MenuInfo con)
    {
        ContentValues cv=new ContentValues();
        cv.put(MySqliteHelper.ColId, con.getItemId());
        cv.put(MySqliteHelper.ColTitle,con.getTitle());
        cv.put(MySqliteHelper.ColDetail, con.getDetail());

        long i=db.insert(MySqliteHelper.TBName, null, cv);
        return i;
    }
    public void update(MenuInfo con)
    {
        ContentValues cv=new ContentValues();
        cv.put(MySqliteHelper.ColTitle, con.getTitle());
        cv.put(MySqliteHelper.ColDetail,con.getDetail());
        db.update(MySqliteHelper.TBName, cv, MySqliteHelper.ColId + "='" + con.getItemId()+"'", null);
    }
    public void delete(long i)
    {
        db.delete(MySqliteHelper.TBName, MySqliteHelper.ColId + "=" + i, null);

    }
    public List<MenuInfo> getData()
    {
        MenuInfo c;
        List<MenuInfo> con=new ArrayList<MenuInfo>();
        Cursor curs=db.query(MySqliteHelper.TBName,new String[] {MySqliteHelper.ColId,MySqliteHelper.ColTitle,MySqliteHelper.ColDetail}
                ,null,null,null,null,null,null);
        curs.moveToFirst();
        while(!curs.isAfterLast())
        {
            c=cursorToMenu(curs);
            con.add(c);
            curs.moveToNext();
        }

        curs.close();
        return con;

    }

    public MenuInfo getParticularData(String id)
    {
        MenuInfo c1=null;
        Cursor curs=db.rawQuery("select * from " + MySqliteHelper.TBName + " where " + MySqliteHelper.ColId + " =" + id + ";", null);
        curs.moveToFirst();
        if(curs==null)
        {

            String s="1";
        }

        c1.setItemId(curs.getString(0));
        c1.setTitle(curs.getString(1));
        c1.setDetail(curs.getString(2));

        curs.close();
        return c1;

    }

    public MenuInfo cursorToMenu(Cursor curs)
    {
        MenuInfo c=new MenuInfo();
        c.setItemId(curs.getString(0));
        c.setTitle(curs.getString(1));
        c.setDetail(curs.getString(2));
        return c;

    }
}