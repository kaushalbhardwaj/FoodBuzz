package com.food.khome.lnmiitmess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.food.khome.lnmiitmess.Database.DatabaseFunction;
import com.food.khome.lnmiitmess.Database.MenuInfo;

import java.util.List;

public class Test extends AppCompatActivity {

    EditText et1;
    List<MenuInfo> li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        et1=(EditText)findViewById(R.id.databaseTest);
        li=DatabaseFunction.getMenuItem(getApplicationContext());
        et1.setText(li.get(0).getTitle()+li.get(0).getDetail());

    }
}
