package com.example.mohsinhussain.whereyatt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mohsinhussain on 21/05/2017.
 */
 class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context, String[] foods) {
        super(context,R.layout.custom_row ,foods);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mohsinsInflater = LayoutInflater.from(getContext());
        View customView = mohsinsInflater.inflate(R.layout.custom_row, parent, false);

        String singleItem = (String) getItem(position);
        ImageView arrowImage = (ImageView) customView.findViewById(R.id.next_Arrow);
        TextView title = (TextView) customView.findViewById(R.id.tx_title);

        title.setText(singleItem);
        arrowImage.setImageResource(R.drawable.next_arrow);
        return customView;
    }
}
