package com.food.khome.lnmiitmess;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.food.khome.lnmiitmess.Database.LikeDislike;
import com.food.khome.lnmiitmess.Database.MenuInfo;
import com.food.khome.lnmiitmess.Tools.MealInfo;
import com.food.khome.lnmiitmess.Tools.NetworkTool;
import com.food.khome.lnmiitmess.Tools.SharedPreference;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceFav;
import com.food.khome.lnmiitmess.Tools.SharedPreferencePing;
import com.food.khome.lnmiitmess.Tools.SharedPreferencePingDay;
import com.food.khome.lnmiitmess.Tools.SharedPreferenceWeek;
import com.food.khome.lnmiitmess.Tools.UserInfo;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class MainPage extends AppCompatActivity {
    public static final String MyPREFERENCES = "UserInfo" ;
    SharedPreferences sharedpreferences;
    UserInfo user1;
    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private Toolbar toolbar;
    String dateOnline;
    int y=0;
    String like,dislike;
    private TabLayout tabLayout;
    ArrayList<MenuInfo> al = new ArrayList<MenuInfo>();
    ArrayList<LikeDislike> likedislike = new ArrayList<LikeDislike>();
    private ViewPager viewPager;
    private CoordinatorLayout coordinatorLayout;
    private int[] tabIcons = {

            R.drawable.ic_action_ic_query_builder_white_18dp,
            R.drawable.ic_action_ic_view_day_white,
            R.drawable.ic_action_ic_today_white
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        user1=new UserInfo();
        //showSPData();
        UserInfo user1=SharedPreference.getSharedPreferInfo(getApplicationContext());
        //`Toast.makeText(MainPage.this, "email:"+user1.getEmail()+" password:"+user1.getPassword(), Toast.LENGTH_SHORT).show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FoodBuzz");
        //getSupportActionBar().setIcon(R.drawable.logo);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        FragmentManager manager = getSupportFragmentManager();
        /*PresentTime myFragment = ()manager.findFragmentById(R.id.PresentTime);
        Bundle bundle1 = new Bundle();
        bundle1.putString("activity_name", "mainpage");
        PresentTime fragobj = new PresentTime();
        fragobj.setArguments(bundle1);
        */
       /* alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        Intent myIntent = new Intent(MainPage.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainPage.this, 0, myIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);*/

        AlarmReceiver.startAlarm(MainPage.this);

        updateMessMenu(1);
        updatePing();
        updateMessInfo();






    }
    public void updatePing()
    {

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
        Calendar c=Calendar.getInstance();
        int d=c.get(Calendar.DAY_OF_MONTH);
        String s2=SharedPreferencePing.getSharedPreferInfo(getApplicationContext(), "pm" + wt);
        String ss1=SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm"+1);
        String ss2=SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm"+2);
        String ss3=SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm"+3);
        String ss4=SharedPreferencePing.getSharedPreferInfo(getApplicationContext(),"pm"+4);
        String ss5= SharedPreferencePingDay.getSharedPreferInfo(getApplicationContext(), "pm" + 5);

        SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm1","0"+ss1.substring(1));
        SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm2","0"+ss2.substring(1));
        SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm3","0"+ss3.substring(1));
        SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm4","0"+ss4.substring(1));
        SharedPreferencePingDay.putSharedPreferInfo(getApplicationContext(),"pm5","0"+ss5.substring(1));

        if(ss1.substring(1).equals(""+d))
            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm"+1,ss1);

        if(ss2.substring(1).equals(""+d))
            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm"+2,ss2);

        if(ss3.substring(1).equals(""+d))
            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm"+3,ss3);

        if(ss4.substring(1).equals(""+d))
            SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm"+4,ss4);

        if(ss5.substring(1).equals(""+d))
            SharedPreferencePingDay.putSharedPreferInfo(getApplicationContext(),"pm"+5,ss5);


        //SharedPreferencePing.putSharedPreferInfo(getApplicationContext(),"pm"+wt,s2);


    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PresentTime(), "ONE");
        adapter.addFragment(new FullDay(), "TWO");
        adapter.addFragment(new WeekTime(), "THREE");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return null;
            return mFragmentTitleList.get(position);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.change_week:
                String s1=SharedPreferenceWeek.getSharedPreferInfo(getApplicationContext());
                if(s1.equals("1"))
                {
                    SharedPreferenceWeek.putSharedPreferInfo(getApplicationContext(),"2");
                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);

                }
                else
                {
                    SharedPreferenceWeek.putSharedPreferInfo(getApplicationContext(), "1");

                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                }

                return true;
            /*case R.id.logout:
                Intent i3=new Intent(MainPage.this,Logout.class);
                startActivity(i3);
                finish();
                return true;
            case R.id.changepass:
                Intent i=new Intent(MainPage.this,newPassword.class);
                startActivity(i);
                return true;
            case R.id.profile:
                Intent i2=new Intent(MainPage.this,Profile.class);
                startActivity(i2);
                return true;

*/
            case R.id.update_menu:
                if (NetworkTool.isConnectingToInternet(MainPage.this)) {
                    //Toast.makeText(getApplicationContext(), "connected to internet ", Toast.LENGTH_SHORT).show();
                    updateMessMenu(2);
                    if(y==2) {
                        Toast.makeText(MainPage.this, "Mess Menu is Updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection!", Snackbar.LENGTH_LONG);
                    //snackbar.setActionTextColor(Color.YELLOW);
                    snackbar.show();
                    //showDialogBox();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void updateMessMenu(int x)
    {

        Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> newUser = (Map<String, Object>) snapshot.getValue();

                dateOnline = (String) newUser.get("date");
                //System.out.println("date is =" + s);

                String s1 = SharedPreference.getDateDatabase(getApplicationContext());
                //System.out.println(" old date is =" + s1);

                if (!dateOnline.equals(s1)) {
                    System.out.println("New Database exists");
                    y = 1;
                    showDialogBox();

                } else {
                    y = 2;

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        /*final ProgressDialog ringProgressDialog = ProgressDialog.show(MainPage.this, "Please wait ...", "Updating Menu....", true);
        ringProgressDialog.setCancelable(false);
        ringProgressDialog.setCanceledOnTouchOutside(false);
        */

    }
    public void updateMessInfo() {


        new Thread(new Runnable() {
            public void run() {
                try {
                    final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/likedislike");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            System.out.println("There are " + snapshot.getChildrenCount() + "likedislike");

                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                LikeDislike post = postSnapshot.getValue(LikeDislike.class);
                                System.out.println(post.getL()  + " - " + post.getD());
                                likedislike.add(post);
                            }
                            DatabaseHandler dh;
                            dh=new DatabaseHandler(MainPage.this);
                            try {
                                dh.open();
                            }
                            catch(Exception e)
                            {
                                //Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                            }
                            int z=0;
                            for(int i=1;i<=2;i++)
                            {
                                for(int j=1;j<=7;j++)
                                {

                                    for(int k=1;k<=4;k++)
                                    {
                                        LikeDislike ld= likedislike.get(z);
                                        int l=Integer.parseInt(ld.getL());
                                        int d=Integer.parseInt(ld.getD());
                                        Firebase ref2 = new Firebase("https://lnmiitmess.firebaseio.com/likedislike");
                                        Firebase alanRef = ref2.child(i+""+ j+""+""+ k);
                                        String icon1=(SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"fm"+i+""+j+""+k));

                                        if(icon1.charAt(0)=='0') {


                                            if(icon1.charAt(1)=='1')
                                            {
                                                //int val=Integer.parseInt(icon1.charAt(1)+"");
                                                alanRef.child("l").setValue((l +1) + "");
                                                SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "fm" + i + "" + j + "" + k,"1"+icon1.charAt(1));

                                            }
                                            else {
                                                //int val = Integer.parseInt(icon1.charAt(1) + "");
                                                alanRef.child("l").setValue((l -1) + "");
                                                SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "fm" + i + "" + j + "" + k, "1" + icon1.charAt(1));
                                                //System.out.println(i+"-"+j+"-"+k+"-----"+(l+1));

                                            }
                                        }
                                        String icon2=SharedPreferenceFav.getSharedPreferInfo(getApplicationContext(),"dm"+i+""+j+""+k);
                                        if(icon2.charAt(0)=='0') {
                                            if(icon2.charAt(1)=='1')
                                            {
                                                //int val = Integer.parseInt(icon2.charAt(1) + "");
                                                alanRef.child("d").setValue((d - 1) + "");
                                                SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "dm" + i + "" + j + "" + k, "1" + icon2.charAt(1));


                                            }
                                            else {
                                                //int val = Integer.parseInt(icon2.charAt(1) + "");
                                                alanRef.child("d").setValue((d+1) + "");
                                                SharedPreferenceFav.putSharedPreferInfo(getApplicationContext(), "dm" + i + "" + j + "" + k, "1" + icon2.charAt(1));

                                            }
                                        }

                                        //MenuInfo mi= al.get(z);
                                        //mi.setItemId("m" + i + j + k);
                                        //dh.update(mi);
                                        //DatabaseFunction.updateMenuItem(getApplicationContext(),mi);
                                        z++;
                                    }

                                }



                            }
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                            System.out.println("The read failed: " + firebaseError.getMessage());
                            //Toast.makeText(MainPage.this, "Error Menu Cannot Be Updated", Toast.LENGTH_SHORT).show();
                           // ringProgressDialog.dismiss();
                        }
                    });


                    // Thread.sleep(10000);
                } catch (Exception e) {
                }
                // ringProgressDialog.dismiss();
            }
        }).start();
    }



    public void showDialogBox()
    {

        /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainPage.this);

        // Setting Dialog Title
        alertDialog.setTitle("New Mess Menu");

        // Setting Dialog Message
        alertDialog.setMessage("Update");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                final ProgressDialog ringProgressDialog = ProgressDialog.show(MainPage.this, "Please wait ...", "Updating....", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setCanceledOnTouchOutside(false);

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/messmenu");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                        MenuInfo post = postSnapshot.getValue(MenuInfo.class);
                                        System.out.println(post.getTitle() + " - " + post.getDetail());
                                        al.add(post);
                                    }
                                    DatabaseHandler dh;
                                    dh = new DatabaseHandler(MainPage.this);
                                    try {
                                        dh.open();
                                    } catch (Exception e) {
                                        //Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                                    }
                                    int z = 0;
                                    for (int i = 1; i <= 2; i++) {
                                        for (int j = 1; j <= 7; j++) {

                                            for (int k = 1; k <= 4; k++) {
                                                MenuInfo mi = al.get(z);
                                                mi.setItemId("m" + i + j + k);
                                                dh.update(mi);
                                                //DatabaseFunction.updateMenuItem(getApplicationContext(),mi);
                                                z++;
                                            }

                                        }


                                    }

                                    SharedPreference.setDateDatabase(getApplicationContext(), dateOnline);
                                    Toast.makeText(MainPage.this, "Mess Menu Updated Successfully", Toast.LENGTH_SHORT).show();
                                    ringProgressDialog.dismiss();
                                    Intent intent = getIntent();
                                    overridePendingTransition(0, 0);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    System.out.println("The read failed: " + firebaseError.getMessage());
                                    Toast.makeText(MainPage.this, "Error Menu Cannot Be Updated", Toast.LENGTH_SHORT).show();
                                    ringProgressDialog.dismiss();
                                }
                            });


                            // Thread.sleep(10000);
                        } catch (Exception e) {
                        }
                        // ringProgressDialog.dismiss();
                    }
                }).start();



                // Write your code here to invoke YES event
                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });*/

        CustomDialogClass cdd = new CustomDialogClass(MainPage.this);
        cdd.show();

       /* AlertDialog a=alertDialog.create();



        // Showing Alert Message
        alertDialog.show();

        Button bn = a.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button bp = a.getButton(DialogInterface.BUTTON_POSITIVE);
        //bn.setBackgroundColor(Color.BLUE);

        if (bn != null) {
           // bn.setBackgroundDrawable(context.getResources()
             //       .getDrawable(R.drawable.custom_background));

            bn.setTextColor(getApplicationContext().getResources()
                    .getColor(android.R.color.black));
        }
        if (bp != null) {

            positive_button.setTextColor(context.getResources()
                    .getColor(android.R.color.white));
        }*/

       /* //Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainPage.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("New Mess Menu");

        // Setting Dialog Message
        alertDialog.setMessage("Update");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed
                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                final ProgressDialog ringProgressDialog = ProgressDialog.show(MainPage.this, "Please wait ...", "Updating....", true);
                ringProgressDialog.setCancelable(false);
                ringProgressDialog.setCanceledOnTouchOutside(false);

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/messmenu");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                        MenuInfo post = postSnapshot.getValue(MenuInfo.class);
                                        System.out.println(post.getTitle() + " - " + post.getDetail());
                                        al.add(post);
                                    }
                                    DatabaseHandler dh;
                                    dh = new DatabaseHandler(MainPage.this);
                                    try {
                                        dh.open();
                                    } catch (Exception e) {
                                        //Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                                    }
                                    int z = 0;
                                    for (int i = 1; i <= 2; i++) {
                                        for (int j = 1; j <= 7; j++) {

                                            for (int k = 1; k <= 4; k++) {
                                                MenuInfo mi = al.get(z);
                                                mi.setItemId("m" + i + j + k);
                                                dh.update(mi);
                                                //DatabaseFunction.updateMenuItem(getApplicationContext(),mi);
                                                z++;
                                            }

                                        }


                                    }
                                    *//*Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();
                                    UserInfo user2=new UserInfo();
                                    String s=(String)newUser.get("rollno");
                                    user2.setRollno(s);
                                    s=(String)newUser.get("uname");
                                    user2.setUname(s);
                                    user2.setPassword(passwordlogin.getText().toString());
                                    user2.setEmail(emaillogin.getText().toString());
                                    user2.setUid(uid);

                                    System.out.println("Author: " + user2.getPassword() + user2.getRollno() + user2.getUname() );
                                    SharedPreference.putSharedPreferInfo(getApplicationContext(), user2);*//*
                                    SharedPreference.setDateDatabase(getApplicationContext(), dateOnline);
                                    Toast.makeText(MainPage.this, "Mess Menu Updated Successfully", Toast.LENGTH_SHORT).show();
                                    ringProgressDialog.dismiss();
                                    Intent intent = getIntent();
                                    overridePendingTransition(0, 0);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    System.out.println("The read failed: " + firebaseError.getMessage());
                                    Toast.makeText(MainPage.this, "Error Menu Cannot Be Updated", Toast.LENGTH_SHORT).show();
                                    ringProgressDialog.dismiss();
                                }
                            });


                            // Thread.sleep(10000);
                        } catch (Exception e) {
                        }
                        // ringProgressDialog.dismiss();
                    }
                }).start();


            }
        });

        // Showing Alert Message
        alertDialog.show();*/
    }

    public class CustomDialogClass extends Dialog {

        public Activity c;
        public Dialog d;
        public Button yes, no;

        public CustomDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_dialog);
            yes = (Button) findViewById(R.id.btn_yes);
            no = (Button) findViewById(R.id.btn_no);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final ProgressDialog ringProgressDialog = ProgressDialog.show(MainPage.this, "Please wait ...", "Updating....", true);
                    ringProgressDialog.setCancelable(false);
                    ringProgressDialog.setCanceledOnTouchOutside(false);

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                final Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com/messmenu");
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot snapshot) {
                                        System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                            MenuInfo post = postSnapshot.getValue(MenuInfo.class);
                                            System.out.println(post.getTitle() + " - " + post.getDetail());
                                            al.add(post);
                                        }
                                        DatabaseHandler dh;
                                        dh = new DatabaseHandler(MainPage.this);
                                        try {
                                            dh.open();
                                        } catch (Exception e) {
                                            //Toast.makeText(getApplicationContext(), "error sql exception", Toast.LENGTH_SHORT).show();

                                        }
                                        int z = 0;
                                        for (int i = 1; i <= 2; i++) {
                                            for (int j = 1; j <= 7; j++) {

                                                for (int k = 1; k <= 4; k++) {
                                                    MenuInfo mi = al.get(z);
                                                    mi.setItemId("m" + i + j + k);
                                                    dh.update(mi);
                                                    //DatabaseFunction.updateMenuItem(getApplicationContext(),mi);
                                                    z++;
                                                }

                                            }


                                        }
                                    /*Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();
                                    UserInfo user2=new UserInfo();
                                    String s=(String)newUser.get("rollno");
                                    user2.setRollno(s);
                                    s=(String)newUser.get("uname");
                                    user2.setUname(s);
                                    user2.setPassword(passwordlogin.getText().toString());
                                    user2.setEmail(emaillogin.getText().toString());
                                    user2.setUid(uid);

                                    System.out.println("Author: " + user2.getPassword() + user2.getRollno() + user2.getUname() );
                                    SharedPreference.putSharedPreferInfo(getApplicationContext(), user2);*/
                                        SharedPreference.setDateDatabase(getApplicationContext(), dateOnline);
                                        Toast.makeText(MainPage.this, "Mess Menu Updated Successfully", Toast.LENGTH_SHORT).show();
                                        ringProgressDialog.dismiss();
                                        Intent intent = getIntent();
                                        overridePendingTransition(0, 0);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {
                                        System.out.println("The read failed: " + firebaseError.getMessage());
                                        Toast.makeText(MainPage.this, "Error Menu Cannot Be Updated", Toast.LENGTH_SHORT).show();
                                        ringProgressDialog.dismiss();
                                    }
                                });


                                // Thread.sleep(10000);
                            } catch (Exception e) {
                            }
                            // ringProgressDialog.dismiss();
                        }
                    }).start();

                    //Toast.makeText(MainPage.this, "asdf", Toast.LENGTH_SHORT).show();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    //d.cancel();
                    //Toast.makeText(MainPage.this, "asdf", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}
