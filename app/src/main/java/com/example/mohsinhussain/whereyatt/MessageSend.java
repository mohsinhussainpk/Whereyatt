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
    public String lit = "its Lit";
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

        List<Whereyatt> contactspolice = db1.getPoliceContacts(product);

        ListAdapter mohsinsAdapter3 = new CustomAdapter3(this, contactspolice);
        messageListView.setAdapter(mohsinsAdapter3);
        SharedPreferences sharedpref1 = getSharedPreferences("permission", Context.MODE_PRIVATE);


        final boolean boopolice = sharedpref1.getBoolean("policesms",false);
        final boolean boolonely = sharedpref1.getBoolean("lonelysms",false);
        final boolean boolit = sharedpref1.getBoolean("litsms",false);
        final boolean booscared = sharedpref1.getBoolean("scaredsms",false);
        final boolean boocustom = sharedpref1.getBoolean("customsms",false);

            messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView textView = (TextView) view.findViewById(R.id.contact_number);
                    String text = textView.getText().toString();
                    //                  System.out.println("Choosen Country = : " + text);

                  if (police.equals(product)) {

                        if (boopolice) {


                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address", text);
                            // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                            smsIntent.putExtra("sms_body", "i am in a police encounter at police ");
                            startActivity(smsIntent);
                        } else {

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                            builder2.setMessage("map is not enabled");
                            builder2.setCancelable(true);
                            AlertDialog alertuser = builder2.create();
                            alertuser.show();
                        }


                  }
                    if (lonely.equals(product)) {
                        if (boolonely) {


                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address", text);
                            // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                            smsIntent.putExtra("sms_body", "i am lonely ");
                            startActivity(smsIntent);
                        } else {

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                            builder2.setMessage("map is not enabled");
                            builder2.setCancelable(true);
                            AlertDialog alertuser = builder2.create();
                            alertuser.show();
                        }
                    }
                    if (lit.equals(product)) {

                        if (boolit) {


                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address", text);
                            // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                            smsIntent.putExtra("sms_body", "i am lonely ");
                            startActivity(smsIntent);
                        } else {

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                            builder2.setMessage("map is not enabled");
                            builder2.setCancelable(true);
                            AlertDialog alertuser = builder2.create();
                            alertuser.show();
                        }
                    }
                    if (scared.equals(product)) {

                        if (booscared) {


                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address", text);
                            // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                            smsIntent.putExtra("sms_body", "i am lonely ");
                            startActivity(smsIntent);
                        } else {

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                            builder2.setMessage("map is not enabled");
                            builder2.setCancelable(true);
                            AlertDialog alertuser = builder2.create();
                            alertuser.show();
                        }
                    }
                    if (custom.equals(product)) {

                        if (boocustom) {


                            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                            smsIntent.setType("vnd.android-dir/mms-sms");
                            smsIntent.putExtra("address", text);
                            // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                            smsIntent.putExtra("sms_body", "i am lonely ");
                            startActivity(smsIntent);
                        } else {

                            AlertDialog.Builder builder2 = new AlertDialog.Builder(MessageSend.this);
                            builder2.setMessage("map is not enabled");
                            builder2.setCancelable(true);
                            AlertDialog alertuser = builder2.create();
                            alertuser.show();
                        }
                    }
                }
            });


    }
}
