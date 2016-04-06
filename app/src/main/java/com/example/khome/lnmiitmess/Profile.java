package com.example.khome.lnmiitmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.khome.lnmiitmess.Tools.SharedPreference;
import com.example.khome.lnmiitmess.Tools.UserInfo;

public class Profile extends AppCompatActivity {

    TextView pro_un,pro_email,pro_roll;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_pro);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pro_un=(TextView)findViewById(R.id.uname_pro);
        pro_email=(TextView)findViewById(R.id.email_pro);
        pro_roll=(TextView)findViewById(R.id.roll_pro);
        UserInfo user1= SharedPreference.getSharedPreferInfo(getApplicationContext());
        pro_un.setText(user1.getUname());
        pro_email.setText(user1.getEmail());
        pro_roll.setText(user1.getRollno());

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
