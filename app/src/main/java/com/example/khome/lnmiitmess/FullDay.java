package com.example.khome.lnmiitmess;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Database.LikeDislike;
import com.example.khome.lnmiitmess.Database.PingInfo;
import com.example.khome.lnmiitmess.Tools.MealInfo;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceFav;
import com.example.khome.lnmiitmess.Tools.SharedPreferencePing;
import com.example.khome.lnmiitmess.Tools.SharedPreferencePingDay;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;
import com.example.khome.lnmiitmess.Tools.UserInfo;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


public class FullDay extends Fragment {
    ImageView imgv;
    ImageView img_heart,img_book,img_dis,img_present;
    RelativeLayout rlfull;
    Snackbar snackbar;
    FloatingActionButton fab;
    String ids;
    RecyclerView rv2;
    int c5=0;

    String week;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_full_day, container, false);
        rlfull=(RelativeLayout)v.findViewById(R.id.rlfull);
        img_heart=(ImageView)v.findViewById(R.id.ic_favorite_day);
        img_book=(ImageView)v.findViewById(R.id.ic_bookmark_day);
        img_dis=(ImageView)v.findViewById(R.id.ic_thumb_down_day);



        return v;

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab=(FloatingActionButton)view.findViewById(R.id.fab);
        fab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));

        Calendar c8=Calendar.getInstance();
        int d3=c8.get(Calendar.DAY_OF_MONTH);

        String s6= SharedPreferencePingDay.getSharedPreferInfo(getActivity().getApplicationContext(), "pm5");

        if(s6.substring(1).equals(d3+""))
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String msg;

                Calendar c=Calendar.getInstance();
                int c4=0;
                int d1=c.get(Calendar.DAY_OF_MONTH);


                    String s6 = SharedPreferencePingDay.getSharedPreferInfo(getActivity().getApplicationContext(), "pm5");
                    if (s6.substring(1).equals(d1 + "")) {
                        if (s6.charAt(0) == '1') {
                            c4 = -1;
                            msg = "Going to Mess";
                            fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);

                            SharedPreferencePingDay.putSharedPreferInfo(getActivity().getApplicationContext(), "pm5" , "0" + s6.substring(1));

                            //fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);
                            for(int i=0;i<5;i++)
                            {

                                SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm"+i , "0" + s6.substring(1));

                            }



                        } else {
                            c4 = 1;
                            msg = "Not Going to Mess Whole Day";
                            //fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                            fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                            SharedPreferencePingDay.putSharedPreferInfo(getActivity().getApplicationContext(), "pm5", "1" + s6.substring(1));

                            //SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm" + wt, "1" + s6.substring(1));

                            for(int i=0;i<5;i++)
                            {
                                SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm"+i , "1" + s6.substring(1));

                            }

                        }

                    } else {
                        // flag=0;

                        c4 = 1;
                        msg = "Not Going to Mess Whole Day";

                        fab.setImageResource(R.drawable.ic_wb_sunny_black_24dpdd);
                        //fab.setImageResource(R.drawable.ic_wb_sunny_white_24dp);
                        SharedPreferencePingDay.putSharedPreferInfo(getActivity().getApplicationContext(), "pm5" , "1" + d1);

                        for(int i=0;i<5;i++)
                        {
                            SharedPreferencePing.putSharedPreferInfo(getActivity().getApplicationContext(), "pm"+i , "1" + d1);

                        }
                    };
                Calendar c2=Calendar.getInstance();
                int date=c2.get(Calendar.DAY_OF_MONTH);
                final int d5=date;

                final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/ping/date"+date);
                c5=c4;
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {


                        Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();
                        PingInfo user2=new PingInfo();
                        Object o=newUser.get("m1");
                        String s=o.toString();
                        user2.setM1(s);
                        o=newUser.get("m2");
                        s=o.toString();
                        user2.setM2(s);
                        o=newUser.get("m3");
                        s=o.toString();
                        user2.setM3(s);
                        o=newUser.get("m4");
                        s=o.toString();
                        user2.setM4(s);



                        System.out.println("Author: " + user2.getM1() + user2.getM2() + user2.getM3() );



                        /*for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            PingInfo post = postSnapshot.getValue(PingInfo.class);
                            System.out.println(post.getM2() + " - " + post.getM1());

                        }*/


                        System.out.println("There are " + snapshot.getChildrenCount() + " blog posts" + "value==" + snapshot.getValue());

                        Firebase ref2 = new Firebase("https://lnmiitmess.firebaseio.com/ping/date" +d5 );

                        int k1=Integer.parseInt(user2.getM1());
                        int k2=Integer.parseInt(user2.getM2());
                        int k3=Integer.parseInt(user2.getM3());
                        int k4=Integer.parseInt(user2.getM4());

                        ref2.child("/m1").setValue((k1 + c5) + "");
                        ref2.child("/m2").setValue((k2 + c5) + "");
                        ref2.child("/m3").setValue((k3 + c5) + "");
                        ref2.child("/m4").setValue((k4 + c5) + "");
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




                snackbar = Snackbar.make(rlfull, msg, Snackbar.LENGTH_SHORT);



                snackbar.show();
            }
        });

        Activity act = getActivity();
        if (act instanceof MainPage) {
            int a = MealInfo.getWeek();
            switch (a) {
                case Calendar.MONDAY:
                    week = "1";
                    break;
                case Calendar.TUESDAY:
                    week = "2";
                    break;
                case Calendar.WEDNESDAY:
                    week = "3";
                    break;
                case Calendar.THURSDAY:
                    week = "4";
                    break;
                case Calendar.FRIDAY:
                    week = "5";
                    break;
                case Calendar.SATURDAY:
                    week = "6";
                    break;
                case Calendar.SUNDAY:
                    week = "7";
                    break;
            }
            String mealI = MealInfo.getMealInfo();

            if (mealI.equals("breakfastn")) {
                if (week.equals("1")) {
                    week = "2";

                } else if (week.equals("2")) {
                    week = "3";

                } else if (week.equals("3")) {
                    week = "4";

                } else if (week.equals("4")) {
                    week = "5";

                } else if (week.equals("5")) {
                    week = "6";

                } else if (week.equals("6")) {
                    week = "7";

                } else if (week.equals("7")) {
                    week = "1";

                }

            }


        } else {

            week = ((Week_after) getActivity()).week;
        }

        String a1,a2;
        a1= SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
        a2=week;



        rv2 = (RecyclerView) view.findViewById(R.id.recycler_view_day);
        rv2.setHasFixedSize(true);
        mLayoutManager2 = new GridLayoutManager(getActivity().getApplicationContext(), 2, LinearLayoutManager.VERTICAL, false);

        rv2.setLayoutManager(mLayoutManager2);
        mAdapter2 = new MyRecyclerViewAdapter2(getDataSet(),a1,a2,getActivity().getApplicationContext());
        rv2.setAdapter(mAdapter2);



        /*String a1 = SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
        String a2 = week;

        for (int i = 1; i <= 4; i++) {
            String a3 = "" + i;
            String iddd = "m" + a1 + a2 + a3;

            String icon1, icon2, icon3;

            icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "f"+iddd);
            icon2 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"b"+iddd);
            icon3 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(),"d"+iddd);

            if (icon1.equals("1"))
                img_heart.setImageResource(R.drawable.ic_favorite_border_grey);
            else
                img_heart.setImageResource(R.drawable.ic_favorite_grey);

            if (icon2.equals("0"))
                img_book.setImageResource(R.drawable.ic_bookmark_border_grey);
            else
                img_book.setImageResource(R.drawable.ic_bookmark_grey);

            if (icon3.equals("0"))
                img_dis.setImageResource(R.drawable.ic_thumb_down_border_grey);
            else
                img_dis.setImageResource(R.drawable.ic_thumb_down_grey);

        }
*/



    }
    public void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter2) mAdapter2).setOnItemClickListener(new MyRecyclerViewAdapter2
                .MyClickListener() {

            public void onItemClick(int position, View v) {

                String a1,a2,a3;
                a1= SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
                a2=week;
                a3=(position+1)+"";

                ids="m"+a1+a2+a3;



                if (v.getId() == R.id.ic_favorite_day) {
                    String icon1;
                    icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "f" + ids);
                    imgv=(ImageView)v;
                    if (icon1.charAt(1)=='1')
                    {
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"f"+ids,"00");
                        imgv.setImageResource(R.drawable.ic_favorite_border_grey);

                        //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                        // new RegisterAsyntaskNew().execute();
                    }
                    else {
                        imgv.setImageResource(R.drawable.ic_favorite_grey);
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "f"+ids, "01");
                    }
                    //Toast.makeText(getActivity().getApplicationContext(), "heart" + position, Toast.LENGTH_SHORT).show();
                }
                    else
                if (v.getId() == R.id.ic_bookmark_day)
                {

                    imgv=(ImageView)v;
                    if (imgv.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.ic_bookmark_grey).getConstantState())
                    {
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"b"+ids,"0");
                        imgv.setImageResource(R.drawable.ic_bookmark_border_grey);
                        //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                        // new RegisterAsyntaskNew().execute();
                    }
                    else {
                        imgv.setImageResource(R.drawable.ic_bookmark_grey);
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "b"+ids, "1");
                    }

                }

                else
                if (v.getId() == R.id.ic_thumb_down_day)
                {
                    String icon1;
                    icon1 = SharedPreferenceFav.getSharedPreferInfo(getActivity().getApplicationContext(), "d" + ids);

                    imgv=(ImageView)v;

                    if (icon1.charAt(1)=='1')
                    {
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(),"d"+ids,"00");
                        imgv.setImageResource(R.drawable.ic_thumb_down_border_grey);
                        //Toast.makeText(getActivity().getApplicationContext(), "Image is ivPic", Toast.LENGTH_LONG).show();
                        // new RegisterAsyntaskNew().execute();
                    }
                    else {
                        imgv.setImageResource(R.drawable.ic_thumb_down_grey);
                        SharedPreferenceFav.putSharedPreferInfo(getActivity().getApplicationContext(), "d"+ids, "01");
                    }
                }
                else
                {

                    Intent i=new Intent(getActivity().getApplicationContext(),Day_after.class);
                    i.putExtra("position", (position+1)+"");
                    i.putExtra("week", week+"");
                    startActivity(i);
                }


               // else
                //Toast.makeText(getActivity().getApplicationContext(), "okk" + position, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private ArrayList<String> getDataSet() {
        String week[]={"Breakfast","Lunch","Snacks","Dinner"};
        ArrayList<String> results = new ArrayList<String>();
        for (int index = 0; index < 4; index++) {

            results.add(week[index]);
        }
        return results;
    }
}
