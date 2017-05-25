package com.example.mohsinhussain.whereyatt;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mohsinhussain on 21/05/2017.
 */


class CustomAdapter3 extends ArrayAdapter {


    public CustomAdapter3(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public CustomAdapter3(Context context, List<Whereyatt> listitems) {
        super(context,R.layout.contact_row ,listitems);

    }







    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mohsinsInflater = LayoutInflater.from(getContext());
        final View customView = mohsinsInflater.inflate(R.layout.callmessagerow, parent, false);


        /*

         ImageView phoneImage = (ImageView) customView.findViewById(R.id.contact_layout);
         TextView name = (TextView) customView.findViewById(R.id.contact_name);
        TextView phonenum = (TextView) customView.findViewById(R.id.contact_num);
        nametodelete= (String) name.getText();
*/

 /*       lv.setAdapter(new ArrayAdapter<Country>(
                this,R.layout.list_black_text,R.id.list_content, values));
*/

       ;
        TextView text = (TextView) customView.findViewById(R.id.contact_name);






        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.callmessagerow, null);
        }


        Whereyatt whereyatt = new Whereyatt();
        whereyatt  = (Whereyatt) getItem(position);


        if (whereyatt != null) {
            //   TextView tt1 = (TextView) v.findViewById(R.id.contact_id);
            TextView tt2 = (TextView) v.findViewById(R.id.contact_namename);
            TextView tt3 = (TextView) v.findViewById(R.id.contact_number);

        /*    if (tt1 != null) {
                tt1.setText(whereyatt.get_id());
            }
*/


            if (tt2 != null) {
                tt2.setText(whereyatt.get_contactname());
            }

            if (tt3 != null) {
                tt3.setText(whereyatt.get_category());
            }
        }

        return v;
        //return customView;

    }
}
