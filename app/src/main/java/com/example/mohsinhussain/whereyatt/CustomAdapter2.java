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


class CustomAdapter2 extends ArrayAdapter {


    public CustomAdapter2(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public CustomAdapter2(Context context, List<Whereyatt> listitems) {
        super(context,R.layout.contact_row ,listitems);

    }







    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mohsinsInflater = LayoutInflater.from(getContext());
        final View customView = mohsinsInflater.inflate(R.layout.contact_row, parent, false);


        /*

         ImageView phoneImage = (ImageView) customView.findViewById(R.id.contact_layout);
         TextView name = (TextView) customView.findViewById(R.id.contact_name);
        TextView phonenum = (TextView) customView.findViewById(R.id.contact_num);
        nametodelete= (String) name.getText();
*/

 /*       lv.setAdapter(new ArrayAdapter<Country>(
                this,R.layout.list_black_text,R.id.list_content, values));
*/

        final ImageView deleteImage =  (ImageView) customView.findViewById(R.id.delete);
        deleteImage.setTag(Integer.valueOf(position));
        final TextView text = (TextView) customView.findViewById(R.id.contact_name);

        deleteImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ImageView clicked for the row = "+view.getTag().toString(), Toast.LENGTH_SHORT).show();
                MyDBHandler db1 = new MyDBHandler(getContext(),null, null, 1);


                String name= (String) text.getText();
                db1.removeSingleContact(name);

            }
        });

        final Whereyatt gettingidtodeleterow = new Whereyatt();
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;

                MyDBHandler db = new MyDBHandler(getContext() ,null, null, 1);
                Whereyatt whereyatt = new Whereyatt();
              String nametodelete =  whereyatt.get_contactname();

                db.deleteContact(nametodelete);


            }
        });



View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.contact_row, null);
        }


        Whereyatt whereyatt = new Whereyatt();
         whereyatt  = (Whereyatt) getItem(position);


        if (whereyatt != null) {
         //   TextView tt1 = (TextView) v.findViewById(R.id.contact_id);
            TextView tt2 = (TextView) v.findViewById(R.id.contact_name);
            TextView tt3 = (TextView) v.findViewById(R.id.contact_num);

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
