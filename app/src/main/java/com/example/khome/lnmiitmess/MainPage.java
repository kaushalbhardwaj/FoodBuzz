package com.example.khome.lnmiitmess;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
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

import com.example.khome.lnmiitmess.Tools.SharedPreference;
import com.example.khome.lnmiitmess.Tools.UserInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPage extends AppCompatActivity {
    public static final String MyPREFERENCES = "UserInfo" ;
    SharedPreferences sharedpreferences;
    UserInfo user1;
    private PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
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
        //getSupportActionBar().setTitle("");
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

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
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        Intent myIntent = new Intent(MainPage.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainPage.this, 0, myIntent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


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
            case R.id.test:
                Intent i4=new Intent(MainPage.this,Test.class);
                startActivity(i4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
