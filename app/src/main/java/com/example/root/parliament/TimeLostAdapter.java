package com.example.root.parliament;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeLostAdapter extends ArrayAdapter<TimeLost> {

    public TimeLostAdapter(Activity context, ArrayList<TimeLost> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.timelost_list_item, parent, false);
        }


        TimeLost currentAndroidFlavor = getItem(position);
        TextView tv1 = (TextView) listItemView.findViewById(R.id.session);
        TextView tv2 = (TextView) listItemView.findViewById(R.id.hours);
        TextView tv3 = (TextView) listItemView.findViewById(R.id.lost);
        TextView tv4 = (TextView) listItemView.findViewById(R.id.issues);

        tv1.setText(currentAndroidFlavor.getSession());
        tv2.setText(currentAndroidFlavor.getSitting_hour());
        tv3.setText(currentAndroidFlavor.getDisruption());
        tv4.setText(currentAndroidFlavor.getRaised());

        return listItemView;
    }
}


