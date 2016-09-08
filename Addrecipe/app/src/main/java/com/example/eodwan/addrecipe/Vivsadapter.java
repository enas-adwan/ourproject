package com.example.eodwan.addrecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mac on 15‏/1‏/2016.
 */
public class Vivsadapter extends BaseAdapter {

    ArrayList<Record> list;
    Context context;
    DatabaseHelper database;

    public Vivsadapter(Context c, ArrayList<Record> input) {
        context = c;
        list = new ArrayList<Record>();
        list = input;

    }
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get single_row layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, parent, false);

        // Find Views

        TextView rowTitle = (TextView) row.findViewById(R.id.nam);
        TextView rowDescription = (TextView) row.findViewById(R.id.idd);


        // Find Data sourcesapple

        final Record temp = list.get(position);
        rowTitle.setText(temp.getName());
        rowDescription.setText(String.valueOf(temp.getNoid()));



        return row;
    }
}
