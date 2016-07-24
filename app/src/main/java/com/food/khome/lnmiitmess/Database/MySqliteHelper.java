package com.food.khome.lnmiitmess.Database;

/**
 * Created by khome on 5/2/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

    public static Context con=null;
    public static String DBName="MessMenu13";
    public static int DBVersion=1;
    public static String TBName="Mess";
    public static String ColId="_id";
    public static String ColTitle="title";
    public static String ColDetail="mdetail";
    public static String create=
            "CREATE TABLE "+ TBName
                    +"("+ColId+" text primary key, "
                    +ColTitle+" text not null, "
                    + ColDetail+" text not null);";;

    public MySqliteHelper(Context context) {

        super(context, DBName, null, DBVersion);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);

        db.execSQL("insert into " + TBName + " values('m111','Chole Kulche','Omlette\nBread Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m112','Kadi','Aloo-Gravy\nPlain Rice\nBundi Raita');");
        db.execSQL("insert into " + TBName + " values('m113','Bread Roll','Ketchup\nRooh Afza\nTea');");
        db.execSQL("insert into " + TBName + " values('m114','Aloo Capsicum','Dal Tadka\nCoconut Barfi\nOnion Rice\nBundi Raita');");


        db.execSQL("insert into " + TBName + " values('m121','Uttapam','Sambar\nNariyal Chutney\nBread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m122','Rajma','Mix Veg\nPlain Rice\nBoondi Raita\nPapad');");
        db.execSQL("insert into " + TBName + " values('m123','Idli fry','Nariyal Chutney\nRasna\nTea');");
        db.execSQL("insert into " + TBName + " values('m124','Mix Veg Parantha','Aloo-Matar Gravy\nIce-cream');");

        db.execSQL("insert into " + TBName + " values('m131','Cutlet','Omlette\nKetch Up\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m132','Aloo Gobhi','Arhar dal\nJeera Rice\nPlain Curd');");
        db.execSQL("insert into " + TBName + " values('m133','Poha','Imli Chutney\nRooh Afza\nTea');");
        db.execSQL("insert into " + TBName + " values('m134','Gatta Masala','Green Moong Dal\nBalushai\nPlain Rice');");


        db.execSQL("insert into " + TBName + " values('m141','Vada/Idli','Coconut Chutney\nBoiled Egg\nToasted Bread\nButter Jam');");
        db.execSQL("insert into " + TBName + " values('m142','Aloo Bhujiya','Dal Tadka\nPlain Rice\nPapad');");
        db.execSQL("insert into " + TBName + " values('m143','Samosa','Ketch Up\nLemon Water\nTea');");
        db.execSQL("insert into " + TBName + " values('m144','Shahi Paneer','Arhar Dal\nBundi Ladoo\nMissi Roti');");

        db.execSQL("insert into " + TBName + " values('m151','Pav Bhaji','Omlette\nBread Butter/jam');");
        db.execSQL("insert into " + TBName + " values('m152','Chole Bhature','Roti\nOnion Rice\nPlain Curd\nFried Mirch');");
        db.execSQL("insert into " + TBName + " values('m153','Papdi Chat','Imli Chutney\nTea\nThandai');");
        db.execSQL("insert into " + TBName + " values('m154','Tinda Masala','Lobia dal\nSuzi Ka Halwa\nPlain Rice');");

        db.execSQL("insert into " + TBName + " values('m161','Poha','Meethi Chatni\nBoiled Egg\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m162','Aloo Parantha','Masala Matar\nPlain Curd\nDhaniya Chutney');");
        db.execSQL("insert into " + TBName + " values('m163','Namkeen Seviyan','Ketch Up\nLemon Water\nTea');");
        db.execSQL("insert into " + TBName + " values('m164','Dal Makhani','Lauki Kofta\nFruit Custard\nPlain Rice');");

        db.execSQL("insert into " + TBName + " values('m171','Samosa & Jalebi','Meethi Chatni\nOmlette Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m172','Lauki','Arhr dal\nJeera Rice\nPakora\nBoondi Raita');");
        db.execSQL("insert into " + TBName + " values('m173','Chowmein','Ketch Up\nJal Jeera\nTea');");
        db.execSQL("insert into " + TBName + " values('m174','Cabbage Masala','Chana Masala\nKheer');");

        db.execSQL("insert into " + TBName + " values('m211','Chole Kulche','Omlette\nBread Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m212','Kadi','Aloo-Gravy\nPlain Rice\nBundi Raita');");
        db.execSQL("insert into " + TBName + " values('m213','Bread Roll','Ketchup\nRooh Afza\nTea');");
        db.execSQL("insert into " + TBName + " values('m214','Aloo Capsicum','Dal Tadka\nCoconut Barfi\nOnion Rice\nBundi Raita');");


        db.execSQL("insert into " + TBName + " values('m221','Uttapam','Sambar\nNariyal Chutney\nBread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m222','Rajma','Mix Veg\nPlain Rice\nBoondi Raita\nPapad');");
        db.execSQL("insert into " + TBName + " values('m223','Idli fry','Nariyal Chutney\nRasna\nTea');");
        db.execSQL("insert into " + TBName + " values('m224','Mix Veg Parantha','Aloo-Matar Gravy\nIce-cream');");

        db.execSQL("insert into " + TBName + " values('m231','Cutlet','Omlette\nKetch Up\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m232','Aloo Gobhi','Arhar dal\nJeera Rice\nPlain Curd');");
        db.execSQL("insert into " + TBName + " values('m233','Poha','Imli Chutney\nRooh Afza\nTea');");
        db.execSQL("insert into " + TBName + " values('m234','Gatta Masala','Green Moong Dal\nBalushai\nPlain Rice');");


        db.execSQL("insert into " + TBName + " values('m241','Vada/Idli','Coconut Chutney\nBoiled Egg\nToasted Bread\nButter Jam');");
        db.execSQL("insert into " + TBName + " values('m242','Aloo Bhujiya','Dal Tadka\nPlain Rice\nPapad');");
        db.execSQL("insert into " + TBName + " values('m243','Samosa','Ketch Up\nLemon Water\nTea');");
        db.execSQL("insert into " + TBName + " values('m244','Shahi Paneer','Arhar Dal\nBundi Ladoo\nMissi Roti');");

        db.execSQL("insert into " + TBName + " values('m251','Pav Bhaji','Omlette\nBread Butter/jam');");
        db.execSQL("insert into " + TBName + " values('m252','Chole Bhature','Roti\nOnion Rice\nPlain Curd\nFried Mirch');");
        db.execSQL("insert into " + TBName + " values('m253','Papdi Chat','Imli Chutney\nTea\nThandai');");
        db.execSQL("insert into " + TBName + " values('m254','Tinda Masala','Lobia dal\nSuzi Ka Halwa\nPlain Rice');");

        db.execSQL("insert into " + TBName + " values('m261','Poha','Meethi Chatni\nBoiled Egg\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m262','Aloo Parantha','Masala Matar\nPlain Curd\nDhaniya Chutney');");
        db.execSQL("insert into " + TBName + " values('m263','Namkeen Seviyan','Ketch Up\nLemon Water\nTea');");
        db.execSQL("insert into " + TBName + " values('m264','Dal Makhani','Lauki Kofta\nFruit Custard\nPlain Rice');");

        db.execSQL("insert into " + TBName + " values('m271','Samosa & Jalebi','Meethi Chatni\nOmlette Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m272','Lauki','Arhr dal\nJeera Rice\nPakora\nBoondi Raita');");
        db.execSQL("insert into " + TBName + " values('m273','Chowmein','Ketch Up\nJal Jeera\nTea');");
        db.execSQL("insert into " + TBName + " values('m274','Cabbage Masala','Chana Masala\nKheer');");

        /*db.execSQL("insert into " + TBName + " values('m211','Cutlet','Omlette\nKetch Up\nBread Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m212','Baigan Bharta','Dal Tadka\nOnion Rice\nPlain Curd');");
        db.execSQL("insert into " + TBName + " values('m213','Aloo Pakoda','Ketchup\nTomato Soup\nTea');");
        db.execSQL("insert into " + TBName + " values('m214','Methi Parantha','Green Chutney\nGulab Jamun\nLemon Rice');");


        db.execSQL("insert into " + TBName + " values('m221','Upma','Nariyal Chutney\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m222','Rajma','Mix Veg\nPlain Rice\nBoondi Raita\nPapad');");
        db.execSQL("insert into " + TBName + " values('m223','Idli fry','Nariyal Chutney\nCoffee\nTea');");
        db.execSQL("insert into " + TBName + " values('m224','Aloo Capsicum','Dal Tadka\nCoconut Barfi\nOnion Rice\nBundi Raita');");

        db.execSQL("insert into " + TBName + " values('m231','Chole Kulche','Boiled Egg\nBread Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m232','Aloo Gobhi','Arhar dal\nJeera Rice\nPlain Curd');");
        db.execSQL("insert into " + TBName + " values('m233','Poha','Imli Chutney\nTomato Soup\nTea');");
        db.execSQL("insert into " + TBName + " values('m234','Pudi','Aloo Matar\nUrad Dal\nShahi Tukra\nLemon Rice');");

        db.execSQL("insert into " + TBName + " values('m241','Sambhar vada','Omlette\nCoconut Chutney\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m242','Kadi','Jeera Aloo\nPlain Rice\nPapad');");
        db.execSQL("insert into " + TBName + " values('m243','Papdi Chat','Imli Chutney\nTea\nTomato Soup');");
        db.execSQL("insert into " + TBName + " values('m244','Shahi Paneer','Arhar Dal\nBesan Ladoo\nMissi Roti');");

        db.execSQL("insert into " + TBName + " values('m251','Pav Bhaji','Boiled Egg\nBread Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m252','Chole Bhature','Roti\nPlain Rice\nBundi Raita\nFried Mirch');");
        db.execSQL("insert into " + TBName + " values('m253','Samosa','Ketch Up\nCoffee\nTea');");
        db.execSQL("insert into " + TBName + " values('m254','Palak Kofta','Lobia Dal\nSuzi Ka Halwa\nPlain Rice');");

        db.execSQL("insert into " + TBName + " values('m261','Poha','Meethi Chatni\nOmlette\nToasted Bread\nButter/Jam');");
        db.execSQL("insert into " + TBName + " values('m262','Aloo Parantha','Masala Matar\nPlain Rice\nDhaniya Chutney\nPlain Curd');");
        db.execSQL("insert into " + TBName + " values('m263','Chowmein','Ketch Up\nCoffee\nTea');");
        db.execSQL("insert into " + TBName + " values('m264','Dal Makhni','Aloo Palak\nPlain Rice\nBalushai');");

        db.execSQL("insert into " + TBName + " values('m271','Samosa & Jalebi','Meethi Chatni\nOmlette Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m272','Lauki','Arhr Dal\nJeera Rice\nBoondi Raita\nPakora');");
        db.execSQL("insert into " + TBName + " values('m273','Bread Roll','Ketch Up\nJal Jeera\nTea');");
        db.execSQL("insert into " + TBName + " values('m274','Cabbage Masala','Chana Masala\nKheer');");
*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TBName);
        onCreate(db);
    }
}



/*
package com.example.khome.lnmiitmess.Database;

*/
/**
 * Created by khome on 5/2/16.
 *//*


import android.content.Context;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;

public class MySqliteHelper extends SQLiteOpenHelper {

    public static Context con=null;
    public static String DBName="MessMenu11";
    public static int DBVersion=1;
    public static String TBName="Mess";
    public static String ColId="_id";
    public static String ColTitle="title";
    public static String ColDetail1="mdetail1";
    public static String ColDetail2="mdetail2";
    public static String ColDetail3="mdetail3";
    public static String ColDetail4="mdetail4";
    public static String create=
            "CREATE TABLE "+ TBName
                    +"("+ColId+" text primary key, "
                    +ColTitle+" text not null, "
                    +ColDetail1+" text not null, "
                    +ColDetail2+" text not null, "
                    +ColDetail3+" text not null, "
                    + ColDetail4+" text not null);";

    public MySqliteHelper(Context context) {

        super(context, DBName, null, DBVersion);
        con=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);

        db.execSQL("insert into " + TBName + " values('m111','Aloo Sandwich','Boiled egg','Bread butter/Jam','','');");
        db.execSQL("insert into " + TBName + " values('m112','Chole Bhature & Roti','Plain Rice','Bundi Raita','fried Mirch','');");
        db.execSQL("insert into " + TBName + " values('m113','Aloo Pyaz Pakoda','ketchup','Tomato Soup','Tea','');");
        db.execSQL("insert into " + TBName + " values('m114','Aloo Capsicum','Dal Tadka','Coconut Barfi','Onion Rice','Bundi Raita');");

        db.execSQL("insert into " + TBName + " values('m121','Uttapam,Sambar','Nariyal Chutney','Toasted Bread ','Butter/Jam','');");
        db.execSQL("insert into " + TBName + " values('m122','Rajma','Mix Veg','Plain Rice','Boondi Raita','Papad');");
        db.execSQL("insert into " + TBName + " values('m123','Idli fry','Nariyal Chutney','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m124','Mix Veg Parantha','Urad Dal','Gulab Jamun','Lemon Rice','');");

        db.execSQL("insert into " + TBName + " values('m131','Cutlet','Omlette''Ketch Up',,'Bread Butter/Jam','','');");
        db.execSQL("insert into " + TBName + " values('m132','Aloo Gobhi','Arhar dal','Jeera Rice','Plain Curd','');");
        db.execSQL("insert into " + TBName + " values('m133','Poha','Imli Chutney','Tomato Soup','Tea','');");
        db.execSQL("insert into " + TBName + " values('m134','Gajar Matar','Green Moong Dal','Shahi Tukra','Plain Rice','');");

        db.execSQL("insert into " + TBName + " values('m141','idli Coconut Chutney','Omlette','Toasted Bread','','');");
        db.execSQL("insert into " + TBName + " values('m142','Jeera Aloo','kadi','Plain Rice','papad','');");
        db.execSQL("insert into " + TBName + " values('m143','Chowmein','Ketch Up','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m144','Kadai Paneer','Arhar Dal','Bundi ladoo','Missi Roti','');");

        db.execSQL("insert into " + TBName + " values('m151','Pav Bhaji','Boiled egg','Bread Butter/jam','','');");
        db.execSQL("insert into " + TBName + " values('m152','Baigan Bharta','Dal Tadka','Onion Rice','Plain Curd','');");
        db.execSQL("insert into " + TBName + " values('m153','Papdi Chat','Imli Chutney','Tea','Tomato Soup','');");
        db.execSQL("insert into " + TBName + " values('m154','Palak Kofta','Lobia dal','Suzi Ka Halwa','Plain Rice','');");

        db.execSQL("insert into " + TBName + " values('m161','Poha+Meethi Chatni','Omlette','Toasted Bread','Butter/Jam','');");
        db.execSQL("insert into " + TBName + " values('m162','Aloo Parantha','Masala Matar','Plain Curd','Dhaniya Chutney','');");
        db.execSQL("insert into " + TBName + " values('m163','Samosa','Ketch Up','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m164','Dal Makhani','Aloo Palak','Balushai','Plain Rice','');");

        db.execSQL("insert into " + TBName + " values('m171','Samosa,Jalebi','Meethi Chatni','Omlette','Bread Butter/Jam','');");
        db.execSQL("insert into " + TBName + " values('m172','Lauki','Arhr dal','Jeera Rice','Pakora','Boondi Raita');");
        db.execSQL("insert into " + TBName + " values('m173','Seviyan','Ketch Up','jal Jeera','Tea','');");
        db.execSQL("insert into " + TBName + " values('m174','Cabbage masala','Chana Masala','Kheer','Pulav','');");

        db.execSQL("insert into " + TBName + " values('m211','Cutlet','Omlette''Ketch Up',,'Bread Butter/Jam','','');");
        db.execSQL("insert into " + TBName + " values('m212','Baigan Bharta','Dal Tadka','Onion Rice','Plain Curd','');");
        db.execSQL("insert into " + TBName + " values('m214','Methi Parantha','Green Chutney','Gulab Jamun','Lemon Rice','');");
        db.execSQL("insert into " + TBName + " values('m213','Aloo Pyaz Pakoda','ketchup','Tomato Soup','Tea','');");

        db.execSQL("insert into " + TBName + " values('m221','Upma Nariyal Chutney','Toasted Bread','Butter/Jam','','');");
        db.execSQL("insert into " + TBName + " values('m222','Rajma','Mix Veg','Plain Rice','Boondi Raita','Papad');");
        db.execSQL("insert into " + TBName + " values('m223','Title Snacks','menu description snacks');");
        db.execSQL("insert into " + TBName + " values('m223','Idli fry','Nariyal Chutney','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m224','Aloo Capsicum','Dal Tadka','Coconut Barfi','Onion Rice','Bundi Raita');");

        db.execSQL("insert into " + TBName + " values('m231','Chole Kulche','Boiled Egg','Bread butter/jam','','');");
        db.execSQL("insert into " + TBName + " values('m232','Aloo Gobhi','','','','');");
        db.execSQL("insert into " + TBName + " values('m132','Aloo Gobhi','Arhar dal','Jeera Rice','Plain Curd','');");
        db.execSQL("insert into " + TBName + " values('m233','Title Snacks','menu description snacks');");
        db.execSQL("insert into " + TBName + " values('m133','Poha','Imli Chutney','Tomato Soup','Tea','');");
        db.execSQL("insert into " + TBName + " values('m234','Pudi','Aloo Matar','Urad Dal','shahi Tukra','Lemon Rice');");

        db.execSQL("insert into " + TBName + " values('m241','Sambhar vada','Omlette','Coconut Chutney','Toasted Bread','Butter/Jam');");
        db.execSQL("insert into " + TBName + " values('m242','Title Lunch','menu description lunch');");
        db.execSQL("insert into " + TBName + " values('m142','Jeera Aloo','kadi','Plain Rice','papad','');");
        db.execSQL("insert into " + TBName + " values('m243','Title Snacks','menu description snacks');");
        db.execSQL("insert into " + TBName + " values('m153','Papdi Chat','Imli Chutney','Tea','Tomato Soup','');");
        db.execSQL("insert into " + TBName + " values('m244','Shahi Paneer','Arhar Dal','Besan Ladoo','Missi Roti','');");

        db.execSQL("insert into " + TBName + " values('m251','Pav Bhaji','Boiled Egg','Bread Butter/jam','','');");
        db.execSQL("insert into " + TBName + " values('m252','Title Lunch','menu description lunch');");
        db.execSQL("insert into " + TBName + " values('m112','Chole Bhature & Roti','Plain Rice','Bundi Raita','fried Mirch','');");
        db.execSQL("insert into " + TBName + " values('m253','Samosa','Ketch Up','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m154','Palak Kofta','Lobia dal','Suzi Ka Halwa','Plain Rice','');");
        db.execSQL("insert into " + TBName + " values('m254','Title Dinner','menu description dinner');");

        db.execSQL("insert into " + TBName + " values('m261','Title break','menu description break');");
        db.execSQL("insert into " + TBName + " values('m161','Poha+Meethi Chatni','Omlette','Toasted Bread','Butter/Jam','');");
        db.execSQL("insert into " + TBName + " valuegg','Bread Butter/jam','','');");
        db.execSQL("insert into " + TBName + " values('m262','Title Lunch','menu description lunch');");
        db.execSQL("insert into " + TBName + " values('m263','Aloo Parantha','Masala Matar','Dhaniya Chutney','Plain Curd','');");
        db.execSQL("insert into " + TBName + " values('m143','Chowmein','Ketch Up','Coffee','Tea','');");
        db.execSQL("insert into " + TBName + " values('m264','Dal Makhni','Aloo Palak','Plain Rice','balushai','');");

        db.execSQL("insert into " + TBName + " values('m271','','','','','');");
        db.execSQL("insert into " + TBName + " values('m171','Samosa,Jalebi','Meethi Chatni','Omlette','Bread Butter/Jam','');");
        db.execSQL("insert into " + TBName + " values('m272','Lauki','Arhr Dal','Jeera Rice','Boondi Raita','Pakora');");
        db.execSQL("insert into " + TBName + " values('m273','Bread Roll','Ketch Up','Jal Jeera','Tea','');");
        db.execSQL("insert into " + TBName + " values('m274','Cabbage Masala','Chana Masala','kheer','Pulav','');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TBName);
        onCreate(db);
    }
}*/
