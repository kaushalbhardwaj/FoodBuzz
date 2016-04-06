package com.example.khome.lnmiitmess;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khome.lnmiitmess.Database.DatabaseFunction;
import com.example.khome.lnmiitmess.Database.MenuInfo;
import com.example.khome.lnmiitmess.Tools.SharedPreferenceWeek;

import java.util.List;

/**
 * Created by khome on 9/2/16.
 */
public class MyDialogFragment extends DialogFragment {
    CardView cv1,cv2,cv3,cv4;
    List<MenuInfo> li;
    TextView break_1,lunch_1,snacks_1,dinner_1;
    TextView tv9,tv10,tv11,tv14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);
        getDialog().setTitle("Full Day Menu");

        cv1=(CardView)rootView.findViewById(R.id.card_view1);
        cv2=(CardView)rootView.findViewById(R.id.card_view2);
        cv3=(CardView)rootView.findViewById(R.id.card_view3);
        cv4=(CardView)rootView.findViewById(R.id.card_view4);
        break_1=(TextView)rootView.findViewById(R.id.break_1);
        lunch_1=(TextView)rootView.findViewById(R.id.lunch_1);
        snacks_1=(TextView)rootView.findViewById(R.id.snacks_1);
        dinner_1=(TextView)rootView.findViewById(R.id.dinner_1);
        tv9=(TextView)rootView.findViewById(R.id.textView9);
        tv10=(TextView)rootView.findViewById(R.id.textView10);
        tv11=(TextView)rootView.findViewById(R.id.textView11);
        tv14=(TextView)rootView.findViewById(R.id.textView14);
        LinearLayout ll1=(LinearLayout)rootView.findViewById(R.id.ll1);

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        String week = getArguments().getString("week");
        String b1= SharedPreferenceWeek.getSharedPreferInfo(getActivity().getApplicationContext());
        String b2=week;

        if(week.equals("1"))
            getDialog().setTitle("Monday");
        else if(week.equals("2"))
            getDialog().setTitle("Tuesday");
        else if(week.equals("3"))
            getDialog().setTitle("Wednesday");
        else if(week.equals("4"))
            getDialog().setTitle("Thursday");
        else if(week.equals("5"))
            getDialog().setTitle("Friday");
        else if(week.equals("6"))
            getDialog().setTitle("Saturday");
        else if(week.equals("7"))
            getDialog().setTitle("Sunday");

        String ids="m"+b1+b2;
        li= DatabaseFunction.getMenuItem(getActivity().getApplicationContext());

        if(li!=null)
        {

            for(int i=0;i<li.size();i++)
            {
                MenuInfo c1=new MenuInfo();
                String s5=li.get(i).getItemId();

                //c1.setItemId(li.get());
                if(s5.equals(ids+"1"))
                {
                    break_1.setText(li.get(i).getTitle());
                    tv9.setText(li.get(i).getDetail());


                }
                if(s5.equals(ids+"2"))
                {
                    lunch_1.setText(li.get(i).getTitle());
                    tv10.setText(li.get(i).getDetail());

                }
                if(s5.equals(ids+"3"))
                {
                    snacks_1.setText(li.get(i).getTitle());
                    tv11.setText(li.get(i).getDetail());

                }
                if(s5.equals(ids+"4"))
                {
                    dinner_1.setText(li.get(i).getTitle());
                    tv14.setText(li.get(i).getDetail());


                }

            }


        }

        return rootView;
    }

}