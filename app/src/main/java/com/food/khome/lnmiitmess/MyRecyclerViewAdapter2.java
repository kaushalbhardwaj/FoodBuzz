package com.food.khome.lnmiitmess;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.food.khome.lnmiitmess.Tools.SharedPreferenceFav;

import java.util.ArrayList;

/**
 * Created by khome on 4/2/16.
 */
public class MyRecyclerViewAdapter2 extends RecyclerView
        .Adapter<MyRecyclerViewAdapter2
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter2";
    private ArrayList<String> mDataset;
    String a11,a22;
    static ImageView img_fav,img_book,img_dis;
    Context con;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView title,head_day,title2;

        RelativeLayout rl;

        Button bt;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_day);
            rl=(RelativeLayout)itemView.findViewById(R.id.back_day);
            img_fav=(ImageView)itemView.findViewById(R.id.ic_favorite_day);
            img_dis=(ImageView)itemView.findViewById(R.id.ic_thumb_down_day);
            img_book=(ImageView)itemView.findViewById(R.id.ic_bookmark_day);
            head_day=(TextView)itemView.findViewById(R.id.head_day);
            title2=(TextView)itemView.findViewById(R.id.title2);
            img_fav.setOnClickListener(this);
            img_book.setOnClickListener(this);
            img_dis.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter2(ArrayList<String> myDataset,String a1,String a2,Context c) {
        mDataset = myDataset;
        con=c;
        a11=a1;
        a22=a2;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_day, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.title.setText(mDataset.get(position));
        int i=0;

        if(mDataset.get(position)=="Breakfast")
        {

            i=1;
            holder.rl.setBackgroundResource(R.drawable.aa8);
           // holder.rl.setBackgroundColor(Color.parseColor("#009688"));
            holder.head_day.setText("B");



        }
        else if(mDataset.get(position)=="Lunch")
        {
            i=2;
            //holder.rl.setBackgroundColor(Color.parseColor("#FF9800"));
            holder.rl.setBackgroundResource(R.drawable.aa11);
            holder.head_day.setText("L");
        }
        else if(mDataset.get(position)=="Snacks")
        {
            i=3;
            //holder.rl.setBackgroundColor(Color.parseColor("#9C27B0"));
            holder.rl.setBackgroundResource(R.drawable.aa10);
            holder.head_day.setText("S");
        }
        else if(mDataset.get(position)=="Dinner")
        {
            i=4;
            //holder.rl.setBackgroundColor(Color.parseColor("#F44336"));
            holder.rl.setBackgroundResource(R.drawable.aa12);
            holder.head_day.setText("D");
        }

        String a3 = "" +i;
        String iddd = "m" + a11 + a22 + a3;

        if(a22.equals("1"))
            holder.title2.setText("Monday");
        else if(a22.equals("2"))
            holder.title2.setText("Tuesday");
        else if(a22.equals("3"))
            holder.title2.setText("Wednesday");
        else if(a22.equals("4"))
            holder.title2.setText("Thursday");
        else if(a22.equals("5"))
            holder.title2.setText("Friday");
        else if(a22.equals("6"))
            holder.title2.setText("Saturday");
        else if(a22.equals("7"))
            holder.title2.setText("Sunday");


        String icon1, icon2, icon3;

        icon1 = SharedPreferenceFav.getSharedPreferInfo(con, "f" + iddd);
        icon2 = SharedPreferenceFav.getSharedPreferInfo(con,"b"+iddd);
        icon3 = SharedPreferenceFav.getSharedPreferInfo(con,"d"+iddd);

        if(icon1.charAt(1)=='0')
            img_fav.setImageResource(R.drawable.ic_favorite_border_grey);
        else
            img_fav.setImageResource(R.drawable.ic_favorite_grey);

        if(icon2.charAt(1)=='0')
            img_book.setImageResource(R.drawable.ic_bookmark_border_grey);
        else
            img_book.setImageResource(R.drawable.ic_bookmark_grey);

        if(icon3.charAt(1)=='0')
            img_dis.setImageResource(R.drawable.ic_thumb_down_border_grey);
        else
            img_dis.setImageResource(R.drawable.ic_thumb_down_grey);


    }

    public void addItem(String dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
