package com.example.khome.lnmiitmess;

/**
 * Created by khome on 6/2/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.rowlayout_week, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout_week, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // Change the icon for Windows and iPhone
        String s = values[position];
        if(s.equals("Monday")) {
            imageView.setImageResource(R.drawable.monday);
        } else if(s.equals("Tuesday"))
        {
            imageView.setImageResource(R.drawable.tuesday);
        }
        else if(s.equals("Wednesday"))
        {
            imageView.setImageResource(R.drawable.wednesday);
        }
        else if(s.equals("Thursday"))
        {
            imageView.setImageResource(R.drawable.thrusday);
        }
        else if(s.equals("Friday"))
        {
            imageView.setImageResource(R.drawable.friday);
        }
        else if(s.equals("Saturday"))
        {
            imageView.setImageResource(R.drawable.saturday);
        }
        else if(s.equals("Sunday"))
        {
            imageView.setImageResource(R.drawable.sunday);
        }

        return rowView;
    }
}
