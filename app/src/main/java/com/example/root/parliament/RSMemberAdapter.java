package com.example.root.parliament;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RSMemberAdapter extends ArrayAdapter<RSMember> {

    public RSMemberAdapter(Activity context, ArrayList<RSMember> MemeList) {
        //Constructor to initialise the adapter with the context and List of memes
        super(context, 0, MemeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.lsmem_list_item, parent, false);
        }


        RSMember currentAndroidFlavor = getItem(position);
        TextView tv1=(TextView) listItemView.findViewById(R.id.name);
        TextView tv2=(TextView) listItemView.findViewById(R.id.party);
        TextView tv3=(TextView) listItemView.findViewById(R.id.state);
        TextView tv4=(TextView) listItemView.findViewById(R.id.consti);
        TextView tv5=(TextView) listItemView.findViewById(R.id.consti1);

        tv1.setText(currentAndroidFlavor.getName());
        tv2.setText(currentAndroidFlavor.getParty());
        tv3.setText(currentAndroidFlavor.getState());
        tv4.setText("");
        tv5.setText("");
        return listItemView;
    }


}