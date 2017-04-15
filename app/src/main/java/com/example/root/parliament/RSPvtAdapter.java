package com.example.root.parliament;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RSPvtAdapter extends ArrayAdapter<RSPvtBill> {

    public RSPvtAdapter(Activity context, ArrayList<RSPvtBill> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pvtbill_list_item, parent, false);
        }


        RSPvtBill currentAndroidFlavor = getItem(position);
        TextView tv1 = (TextView) listItemView.findViewById(R.id.year);
        TextView tv5 = (TextView) listItemView.findViewById(R.id.name);
        TextView tv2 = (TextView) listItemView.findViewById(R.id.title);
        TextView tv3 = (TextView) listItemView.findViewById(R.id.ministry);
        TextView tv4 = (TextView) listItemView.findViewById(R.id.category);

        tv1.setText(currentAndroidFlavor.getYear());
        tv2.setText(currentAndroidFlavor.getTitle());
        tv3.setText(currentAndroidFlavor.getMinistry());
        tv4.setText(currentAndroidFlavor.getCategory());
        tv5.setText(currentAndroidFlavor.getName());

        return listItemView;
    }
}


