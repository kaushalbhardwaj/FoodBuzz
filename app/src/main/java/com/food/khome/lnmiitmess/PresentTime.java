package com.food.khome.lnmiitmess;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.food.khome.lnmiitmess.Database.DatabaseFunction;
import com.food.khome.lnmiitmess.Database.MenuInfo;
import com.food.khome.lnmiitmess.Tools.MealInfo;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceFav;
import com.food.khome.lnmiitmess.Tools.SharedPreferencePing;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceWeek;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PresentTime extends Fragment  {
    ImageView img_heart,img_book,img_dis,img_present;
    LinearLayout head_layout,llpresent;
    RelativeLayout body_layout;
    Snackbar snackbar;
    int s5=0;
    int c5;
    int f=0;
    String week;
    String msg;
    int date=0;
    int var;
    ArrayList<String> dping;
    String ids;
    CardView cv1;
    List<MenuInfo> li;
    int z=0;
    FloatingActionButton fab;
    TextView title,menudetails,label2;
    TextView tv;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_present_time,container,false);
        img_present=(ImageView)v.findViewById(R.id.icon_present);
        head_layout=(LinearLayout)v.findViewById(R.id.head_layout);
        llpresent=(LinearLayout)v.findViewById(R.id.llpresent);
        body_layout=(RelativeLayout)v.findViewById(R.id.body_layout);
        img_heart=(ImageView)v.findViewById(R.id.ic_favorite_day);
        img_book=(ImageView)v.findViewById(R.id.ic_bookmark_day);
        img_dis=(ImageView)v.findViewById(R.id.ic_thumb_down_day);


        fab=(FloatingActionButton)v.findViewById(R.id.fab_present);
        cv1=(CardView)v.findViewById(R.id.card_view_present1);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                z++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        z = 0;
                    }
                };

                if (z == 1) {
                    //Single click
                    //Toast.makeText(getActivity().getApplicationContext(), "single click", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(r, 250);
                } else if (z == 2) {
                    //Double click

                    String icon1, icon2, icon3;

                    icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "f" + ids);
                    icon2 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "b" + ids);
                    icon3 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "d" + ids);


                    if (icon1.charAt(1)=='0') {
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "f" + ids, "01");
                        img_heart.setImageResource(R.drawable.ic_favorite_grey);
                    } else {
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "f" + ids, "00");
                        img_heart.setImageResource(R.drawable.ic_favorite_border_grey);

                    }

                    z = 0;
                    //ShowDailog();
                }







            }
        });

        tv=(TextView)v.findViewById(R.id.label);
        label2=(TextView)v.findViewById(R.id.label2);
        title=(TextView)v.findViewById(R.id.title);
        menudetails=(TextView)v.findViewById(R.id.menudetails);
      //  Bundle bundle = this.getArguments();
        //String activity_name = bundle.getString("activity_name");
        Activity act=getActivity();
        if(act instanceof MainPage)
        {
            int mealid=1;
            String mealI= MealInfo.getMealInfo();
            if(mealI.equals("breakfast"))
            {
                mealid=1;
                tv.setText("Breakfast");
                img_present.setImageResource(R.drawable.breakfast);
                body_layout.setBackgroundResource(R.drawable.aa8);
            }
            else if(mealI.equals("lunch"))
            {
                mealid=2;
                tv.setText("Lunch");
                body_layout.setBackgroundResource(R.drawable.aa11);
                img_present.setImageResource(R.drawable.lunch);
            }
            else if(mealI.equals("snacks"))
            {
                mealid=3;
                body_layout.setBackgroundResource(R.drawable.aa10);
                tv.setText("Snacks");
                img_present.setImageResource(R.drawable.snacks);

            }
            else if(mealI.equals("dinner"))
            {
                mealid=4;
                tv.setText("Dinner");
                body_layout.setBackgroundResource(R.drawable.aa12);
                img_present.setImageResource(R.drawable.dinner);

            }
            else if(mealI.equals("breakfastn"))
            {
                mealid=1;
                tv.setText("Breakfast");
                img_present.setImageResource(R.drawable.breakfast);
                body_layout.setBackgroundResource(R.drawable.aa8);
            }
            else
            {
                tv.setText("error");
                body_layout.setBackgroundResource(R.drawable.aa12);
                img_present.setImageResource(R.drawable.monday);
            }

            int a= MealInfo.getWeek();
            switch (a)
            {
                case Calendar.MONDAY:
                    week="1";
                    label2.setText("Monday");
                    break;
                case Calendar.TUESDAY:
                    week="2";
                    label2.setText("Tuesday");
                    break;
                case Calendar.WEDNESDAY:
                    week="3";
                    label2.setText("Wednesday");
                    break;
                case Calendar.THURSDAY:
                    label2.setText("Thursday");
                    week="4";
                    break;
                case Calendar.FRIDAY:
                    week="5";
                    label2.setText("Friday");
                    break;
                case Calendar.SATURDAY:
                    week="6";
                    label2.setText("Saturday");
                    break;
                case Calendar.SUNDAY:
                    week="7";
                    label2.setText("Sunday");
                    break;
            }

            if(mealI.equals("breakfastn"))
            {
                int a2= MealInfo.getWeek();
                switch (a2)
                {
                    case Calendar.MONDAY:
                        label2.setText("Tuesday");
                        break;
                    case Calendar.TUESDAY:
                        label2.setText("Wednesday");
                        break;
                    case Calendar.WEDNESDAY:
                        label2.setText("Thursday");
                        break;
                    case Calendar.THURSDAY:
                        label2.setText("Friday");
                        break;
                    case Calendar.FRIDAY:
                        label2.setText("Saturday");
                        break;
                    case Calendar.SATURDAY:
                        label2.setText("Sunday");
                        break;
                    case Calendar.SUNDAY:
                        label2.setText("Monday");
                        break;
                }


            }


            //String b1="1";


            String b1=SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
            String b2=week;
            String b3=mealid+"";
            if(mealI.equals("breakfastn"))
            {
                if(week.equals("1"))
                {
                    b2="2";

                }
                else if(week.equals("2"))
                {
                    b2="3";

                }
                else if(week.equals("3"))
                {
                    b2="4";

                }
                else if(week.equals("4"))
                {
                    b2="5";

                }
                else if(week.equals("5"))
                {
                    b2="6";

                }
                else if(week.equals("6"))
                {
                    b2="7";

                }
                else if(week.equals("7"))
                {
                    b2="1";

                }



            }

            ids="m"+b1+b2+b3;
            li=DatabaseFunction.getMenuItem(getActivity().getApplicationContext());

            String icon1,icon2,icon3;

            icon1=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"f"+ids);
            icon2=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"b"+ids);
            icon3=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"d"+ids);
            char c=icon1.charAt(1);
            if(c=='0')
                img_heart.setImageResource(R.drawable.ic_favorite_border_grey);
            else
                img_heart.setImageResource(R.drawable.ic_favorite_grey);

            if(icon2.charAt(1)=='0')
                img_book.setImageResource(R.drawable.ic_bookmark_border_grey);
            else
                img_book.setImageResource(R.drawable.ic_bookmark_grey);

            if(icon3.charAt(1)=='0')
                img_dis.setImageResource(R.drawable.ic_thumb_down_border_grey);
            else
                img_dis.setImageResource(R.drawable.ic_thumb_down_grey);



            if(li!=null)
            {

                for(int i=0;i<li.size();i++)
                {
                    MenuInfo c1=new MenuInfo();
                    String s5=li.get(i).getItemId();

                    //c1.setItemId(li.get());
                    if(s5.equals(ids))
                    {
                        title.setText(li.get(i).getTitle());
                        menudetails.setText(li.get(i).getDetail());
                        break;
                    }

                }


            }



            String s= MealInfo.getMealInfo();
            int wt=0;
            if(s.equals("breakfast"))
                wt=1;
            else if(s.equals("lunch"))
                wt=2;

            else if(s.equals("snacks"))
                wt=3;

            else if(s.equals("dinner"))
                wt=4;

            else
                wt=1;

            String s2=SharedPreferencePing.getSharedPreferInfo(getActivity().getApplicationContext(), "pm" + wt);
            if(s2.charAt(0)=='1')
            {
                fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);

            }
            else
            {
                fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);

            }




        }
        else if(act instanceof Day_after)
        {

            String pos=((Day_after)getActivity()).getValue();
            //Toast.makeText(getActivity().getApplicationContext(), "f"+pos, Toast.LENGTH_SHORT).show();
            if(pos.equals("1"))
            {
                tv.setText("Breakfast");
                img_present.setImageResource(R.drawable.breakfast);
                body_layout.setBackgroundResource(R.drawable.aa8);

            }
            else if(pos.equals("2"))
            {
                tv.setText("Lunch");
                body_layout.setBackgroundResource(R.drawable.aa11);

                img_present.setImageResource(R.drawable.lunch);

            }
            else if(pos.equals("3"))
            {
                tv.setText("Snacks");
                img_present.setImageResource(R.drawable.snacks);
                body_layout.setBackgroundResource(R.drawable.aa10);

            }
            else if(pos.equals("4"))
            {
                tv.setText("Dinner");
                img_present.setImageResource(R.drawable.dinner);
                body_layout.setBackgroundResource(R.drawable.aa12);

            }



            MenuInfo mi;

            String a1=SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
            String a2=((Day_after)getActivity()).week;
            String a3=pos;

            ids="m"+a1+a2+a3;

            if(a2.equals("1"))
                label2.setText("Monday");
            else if(a2.equals("2"))
                label2.setText("Tuesday");
            else if(a2.equals("3"))
                label2.setText("Wednesday");
            else if(a2.equals("4"))
                label2.setText("Thursday");
            else if(a2.equals("5"))
                label2.setText("Friday");
            else if(a2.equals("6"))
                label2.setText("Saturday");
            else if(a2.equals("7"))
                label2.setText("Sunday");

            //MenuInfo li= DatabaseFunction.getParData(getActivity().getApplicationContext(),idd);

            li=DatabaseFunction.getMenuItem(getActivity().getApplicationContext());

            String icon1,icon2,icon3;

            icon1=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"f"+ids);
            icon2=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"b"+ids);
            icon3=SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"d"+ids);

            if(icon1.charAt(1)=='0')
                img_heart.setImageResource(R.drawable.ic_favorite_border_grey);
            else
                img_heart.setImageResource(R.drawable.ic_favorite_grey);

            if(icon2.charAt(1)=='0')
                img_book.setImageResource(R.drawable.ic_bookmark_border_grey);
            else
                img_book.setImageResource(R.drawable.ic_bookmark_grey);

            if(icon3.charAt(1)=='0')
                img_dis.setImageResource(R.drawable.ic_thumb_down_border_grey);
            else
                img_dis.setImageResource(R.drawable.ic_thumb_down_grey);


            Calendar c=Calendar.getInstance();
            int d1=c.get(Calendar.DAY_OF_MONTH);

            String s6= SharedPreferencePing.getSharedPreferInfo(getActivity().getApplicationContext(), "pm" + pos);

            if(s6.substring(1).equals(d1+""))
            {
                if(s6.charAt(0)=='1')
                {
                    fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                }
                else
                {
                    fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);


                }

            }
            else {
                fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);
            }


/*


            String s2=SharedPreferencePing.getSharedPreferInfo(getActivity().getApplicationContext(), "pm" + pos);
            if(s2.charAt(0)=='1')
            {
                fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);

            }
            else
            {
                fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);

            }

*/

            if(li==null)
            {

                Toast.makeText(getActivity().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            else {
                for(int i=0;i<li.size();i++)
                {
                    MenuInfo c1=new MenuInfo();
                    String s4=li.get(i).getItemId();

                    //c1.setItemId(li.get());
                    if(s4.equals(ids))
                    {
                        title.setText(li.get(i).getTitle());
                        menudetails.setText(li.get(i).getDetail());
                        break;
                    }

                }
                    /*mi1.setText(li.getItemId());
                    mi2.setText(li.getTitle());
                    mi3.setText(li.getDetail());*/
                //Toast.makeText(getActivity().getApplicationContext(), "okk", Toast.LENGTH_SHORT).show();

            }




        }
        else
        {
            tv.setText("Error");
            img_present.setImageResource(R.drawable.monday);

        }
            //Toast.makeText(getActivity().getApplicationContext(), "other", Toast.LENGTH_SHORT).show();
        return v;

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s= MealInfo.getMealInfo();
                int wt=0;
                int flag=0;
                if(s.equals("breakfast"))
                    wt=1;
                else if(s.equals("lunch"))
                    wt=2;

                else if(s.equals("snacks"))
                    wt=3;

                else if(s.equals("dinner"))
                    wt=4;

                else
                    wt=1;
                Calendar c=Calendar.getInstance();
                int c4=0;
                int d1=c.get(Calendar.DAY_OF_MONTH);
                Activity act=getActivity();
                if(act instanceof Day_after)
                {
                    String pos=((Day_after)getActivity()).getValue();
                    wt=Integer.parseInt(pos);


                }


                String s6= SharedPreferencePing.getSharedPreferInfo(getActivity().getApplicationContext(), "pm" + wt);
                if(s6.substring(1).equals(d1+""))
                {
                    if(s6.charAt(0)=='1')
                    {
                        c4=-1;
                        msg="Going to Mess";
                        fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);

                        SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" +wt, "0"+s6.substring(1));

                    }
                    else
                    {
                        c4=1;
                        msg="Not Going to Mess";
                        fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                        SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" +wt, "1"+s6.substring(1));


                    }
                    flag=1;

                }
                else {
                   // flag=0;

                        c4=1;
                    msg="Not Going to Mess";
                    fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                    //fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);
                        SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" +wt, "1"+d1);
                }
                var=wt;
                if(act instanceof Day_after)
                {
                    String pos=((Day_after)getActivity()).getValue();
                    var=Integer.parseInt(pos);


                }

                s5=0;
                Calendar c2=Calendar.getInstance();
                date=c2.get(Calendar.DAY_OF_MONTH);

                final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date+"/m"+var);
                c5=c4;
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                                    /*Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();

                                    dping=(String)newUser.get("m3");
                                    System.out.println("-------"+dping);
*/
                        System.out.println("There are " + snapshot.getChildrenCount() + " blog posts" + "value==" + snapshot.getValue());

                        Object o = snapshot.getValue();
                        String s4 = o.toString();
                        System.out.println(s4);
                        s5 = Integer.parseInt(s4);
                        System.out.println(s5 + "");
                        int k = s5;
                        Firebase ref2 = new Firebase("https://lnmiitmess.firebaseio.com/ping/date" + date + "/m" + var);


                        ref2.setValue((k + c5) + "");
                        //updatePingCounter(s5);
                        //ref.setValue((s5+1)+"");
                        //ref.setValue((s5+1)+"");


                        // SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" + var, "11");

                    }


                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }


                });





/*
                new Thread(new Runnable() {
                    public void run() {
                        try {


                            s5=0;
                            Calendar c=Calendar.getInstance();
                            date=c.get(Calendar.DAY_OF_MONTH);

                            final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date+"/m"+var);

                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    */
/*Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();

                                    dping=(String)newUser.get("m3");
                                    System.out.println("-------"+dping);
*//*

                                    System.out.println("There are " + snapshot.getChildrenCount() + " blog posts"+"value=="+snapshot.getValue());

                                    Object o=snapshot.getValue();
                                   String s4=o.toString();
                                    System.out.println(s4);
                                     s5= Integer.parseInt(s4);
                                    System.out.println(s5+"");
                                    //updatePingCounter(s5);
                                    //ref.setValue((s5+1)+"");
                                    //ref.setValue((s5+1)+"");

                                    int k=s5;
                                    Firebase ref2 = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date+"/m"+var);


                                   // ref2.setValue((k+1)+"");
                                   // SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" + var, "11");

                                }


                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    System.out.println("The read failed: " + firebaseError.getMessage());
                                }




                            });



                            // Thread.sleep(10000);
                        } catch (Exception e) {
                        }
                        // ringProgressDialog.dismiss();
                    }
                }).start();
*/




                snackbar = Snackbar.make(llpresent, msg, Snackbar.LENGTH_SHORT);
                snackbar.show();

            }
        });
        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String icon1;
                icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "f" + ids);
                ImageView imgv=(ImageView)v;
                if (icon1.charAt(1)=='1')
                {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "f"+ids, "00");
                    imgv.setImageResource(R.drawable.ic_favorite_border_grey);
                    //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                    // new RegisterAsyntaskNew().execute();
                }
                else {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "f"+ids, "01");
                    imgv.setImageResource(R.drawable.ic_favorite_grey);
                }

            }
        });
        img_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imgv=(ImageView)v;
                if (imgv.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.ic_bookmark_grey).getConstantState())
                {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"b"+ids,"0");
                    imgv.setImageResource(R.drawable.ic_bookmark_border_grey);
                    //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                    // new RegisterAsyntaskNew().execute();
                }
                else {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "b"+ids, "1");
                    imgv.setImageResource(R.drawable.ic_bookmark_grey);
                }
                }
        });

        img_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String icon1;
                icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "d" + ids);
                ImageView imgv = (ImageView) v;
                if (icon1.charAt(1)=='1') {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"d"+ids,"00");
                     imgv.setImageResource(R.drawable.ic_thumb_down_border_grey);
                    //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                    // new RegisterAsyntaskNew().execute();
                } else {
                    SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"d"+ids, "01");
                    imgv.setImageResource(R.drawable.ic_thumb_down_grey);
                }
            }
        });


    }

    public void updatePingCounter(int j)
    {


        Firebase ref2 = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date+"/m"+var);


        ref2.setValue((j+1)+"");



    }
}
