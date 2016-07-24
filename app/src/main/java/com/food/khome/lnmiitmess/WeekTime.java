package com.food.khome.lnmiitmess;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class WeekTime extends Fragment {
    ListView lv;
    String week;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_week_time,container,false);
        lv=(ListView)v.findViewById(R.id.listview_week);
        String[] values = new String[] { "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday" };
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(getActivity().getApplicationContext(), values);
        lv.setAdapter(adapter);

        return v;

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                week=""+(position+1);
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment ();
                Bundle args = new Bundle();
                args.putString("week", week);
                dialogFragment.setArguments(args);
                dialogFragment.show(fm, "Sample Fragment");

                //Toast.makeText(getActivity().getApplicationContext(), "item "+position, Toast.LENGTH_SHORT).show();
            }
        });


    }

}
