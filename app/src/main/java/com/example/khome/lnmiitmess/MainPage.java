package com.example.khome.lnmiitmess;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.khome.lnmiitmess.Database.DatabaseFunction;
import com.example.khome.lnmiitmess.Database.MenuInfo;
import com.example.khome.lnmiitmess.Tools.MealInfo;
import com.example.khome.lnmiitmess.Tools.SharedPreference;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;
import com.example.khome.lnmiitmess.Tools.UserInfo;
import com.firebase.client.AuthData;
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
    private TabLayout tabLayout;
    ArrayList<MenuInfo> al = new ArrayList<MenuInfo>();
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
        getSupportActionBar().setTitle("Mess");
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

        //AlarmReceiver.startAlarm(MainPage.this);

        updateMessMenu();





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
            case R.id.logout:
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


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void updateMessMenu()
    {


        Firebase ref = new Firebase("https://lnmiitmess.firebaseio.com");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Map<String, Object> newUser=(Map<String,Object>)snapshot.getValue();

                 dateOnline=(String)newUser.get("date");
                //System.out.println("date is =" + s);

                String s1=SharedPreference.getDateDatabase(getApplicationContext());
                //System.out.println(" old date is =" + s1);

                if(!dateOnline.equals(s1))
                {
                    System.out.println("New Database exists");
                    showDialogBox();

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
    public void showDialogBox()
    {

        //Toast.makeText(getApplicationContext(),"no connection",Toast.LENGTH_SHORT).show();
        AlertDialog alertDialog = new AlertDialog.Builder(
                MainPage.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("New Mess Menu");

        // Setting Dialog Message
        alertDialog.setMessage("Update");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
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
                                        System.out.println(post.getTitle()  + " - " + post.getDetail());
                                        al.add(post);
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
                                                MenuInfo mi= al.get(z);
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
                                    ringProgressDialog.dismiss();Intent intent = getIntent();
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
        alertDialog.show();
    }
}
