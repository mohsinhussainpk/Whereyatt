package com.example.mohsinhussain.whereyatt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CallSend extends AppCompatActivity {

    public String police = "Police Encounter";
    public String lonely = "Lonely";
    public String lit = "its Lit";
    public String scared = "Scared";
    public String custom = "custom";
    String product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_send);

        ListView messageListView = (ListView) findViewById(R.id.listViewMessage1);

        MyDBHandler db1 = new MyDBHandler(this,null, null, 1);
        Intent i = getIntent();
        // getting attached intent data
        product = i.getStringExtra("product");

        List<Whereyatt> contactspolice = db1.getPoliceContacts(product);

        ListAdapter mohsinsAdapter3 = new CustomAdapter3(this, contactspolice);
        messageListView.setAdapter(mohsinsAdapter3);
        SharedPreferences sharedpref1 = getSharedPreferences("permission", Context.MODE_PRIVATE);


        final boolean boopolice = sharedpref1.getBoolean("policesms",false);
        final boolean boolonely = sharedpref1.getBoolean("lonelysms",false);

        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.contact_number);
                String text = textView.getText().toString();
                //                  System.out.println("Choosen Country = : " + text);

                if (lonely.equals(product)) {

                    if (boopolice) {


                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
                        startActivity(intent);

                        Intent callIntent = new Intent(android.content.Intent.ACTION_VIEW);

                        startActivity(callIntent);
                    }
                    else{
                        android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(CallSend.this);
                        builder2.setMessage("call is not enabled");
                        builder2.setCancelable(true);
                        android.app.AlertDialog alertuser = builder2.create();
                        alertuser.show();

                    }



                }
                if (police.equals(product)) {
                    if (boolonely) {


                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
                        startActivity(intent);

                        Intent callIntent = new Intent(android.content.Intent.ACTION_VIEW);

                        startActivity(callIntent);
                    }
                    else{
                        android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(CallSend.this);
                        builder2.setMessage("call is not enabled");
                        builder2.setCancelable(true);
                        android.app.AlertDialog alertuser = builder2.create();
                        alertuser.show();

                    }
                }
            }
        });


    }
}
