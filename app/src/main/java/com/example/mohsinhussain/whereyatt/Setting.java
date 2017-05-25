package com.example.mohsinhussain.whereyatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        String [] foods = {"Police Encounter", "Lonely", "it's Lit", "Scared", "custom"};

        ListAdapter mohsinsAdapter = new CustomAdapter(this, foods);

         ListView mohsinsListView = (ListView) findViewById(R.id.listView);

        mohsinsListView.setAdapter(mohsinsAdapter);
       // String data;
        mohsinsListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                      //  String item = view.get(position).getName();

                        String product = (String)parent.getItemAtPosition(position);

                        Intent intent = new Intent(Setting.this, DetailedSetting.class);
                        intent.putExtra("product",product);
                        startActivity(intent);

                    }
                }
        );
    }
}
