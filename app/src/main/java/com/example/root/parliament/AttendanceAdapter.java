package com.example.root.parliament;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AttendanceAdapter extends ArrayAdapter<Attendance> {

    public AttendanceAdapter(Activity context, ArrayList<Attendance> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.attendance_list_item, parent, false);
        }


        Attendance currentAndroidFlavor = getItem(position);
        TextView tv1=(TextView) listItemView.findViewById(R.id.seatno);
        TextView tv2=(TextView) listItemView.findViewById(R.id.name);
        TextView tv3=(TextView) listItemView.findViewById(R.id.state);
        TextView tv4=(TextView) listItemView.findViewById(R.id.consti);
        TextView tv5=(TextView) listItemView.findViewById(R.id.signed);

        tv1.setText(currentAndroidFlavor.getSeat());
        tv2.setText(currentAndroidFlavor.getName());
        tv3.setText(currentAndroidFlavor.getState());
        tv4.setText(currentAndroidFlavor.getConstituency());
        tv5.setText(currentAndroidFlavor.getSigned());

        return listItemView;
    }


}