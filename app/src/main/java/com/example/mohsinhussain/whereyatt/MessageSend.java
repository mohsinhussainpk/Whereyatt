package com.example.mohsinhussain.whereyatt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MessageSend extends AppCompatActivity {

    public String police = "Police Encounter";
    public String lonely = "Lonely";
    public String lit = "it's Lit";
    public String scared = "Scared";
    public String custom = "custom";
    String product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);

         ListView messageListView = (ListView) findViewById(R.id.listViewMessage);

        MyDBHandler db1 = new MyDBHandler(this,null, null, 1);
        Intent i = getIntent();
        // getting attached intent data
        product = i.getStringExtra("product");

        List<Whereyatt> contactspolice = db1.getAllContacts();

        ListAdapter mohsinsAdapter3 = new CustomAdapter3(this, contactspolice);
        messageListView.setAdapter(mohsinsAdapter3);
        SharedPreferences sharedpref1 = getSharedPreferences("permission", Context.MODE_PRIVATE);


        final boolean boopolice = sharedpref1.getBoolean("policesms",false);

            messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) findViewById(R.id.contact_number);
                     String text = textView.getText().toString();
                    if(boopolice&&police.equals(product)) {


                        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", text);
                        // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                        smsIntent.putExtra("sms_body", "i am in a police encounter at police ");
                        startActivity(smsIntent);
                    }
                    else{

                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                        builder2.setMessage("map is not enabled");
                        builder2.setCancelable(true);
                        AlertDialog alertuser = builder2.create();
                        alertuser.show();
                    }

                }
            });


    }
}
