package com.example.mohsinhussain.whereyatt;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class DetailedSetting extends AppCompatActivity {

    TextView addcontact;
    public static final int PICK_CONTACT = 1;
    MyDBHandler dbHandler;
    ImageView deletebutton;
 //   CheckBox smscheckbox;
  //  CheckBox callcheckbox;


    public String product;


    public void onClick(View v) {

        addcontact = (TextView) findViewById(R.id.add_contact);


        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_CONTACT);

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        String cNumber = null;
        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null, null);
                            phones.moveToFirst();
                            cNumber = phones.getString(phones.getColumnIndex("data1"));
                            //System.out.println("number is:" + cNumber);
                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        MyDBHandler db = new MyDBHandler(this,null, null, 1);

                        db.addContact(new Whereyatt(name,cNumber));


                        //police sms checkbox





                        //  Whereyatt whereyatt = new Whereyatt(name);
                        //dbHandler.addContact(whereyatt);
                        //  printDatabase();

//                        printDatabase();
                        // addcontact.setText(name + " : " + cNumber);


                        //  dbHandler.addContact(whereyatt);

                        // Whereyatt whereyatt2 = new Whereyatt();
                        //  name1 =  whereyatt2.get_contactname();

                        //   addcontact.setText(name1);

                        // addcontact.setText(contactname);
                        // printDatabase();


                    }
                }
                break;
        }


    }


/*
    public void printDatabase(){

       String dbString = dbHandler.databaseToString();

        addcontact.setText(dbString);

    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_setting);
        TextView txtProduct = (TextView) findViewById(R.id.contact_name);

        deletebutton = (ImageView) findViewById(R.id.delete);


        dbHandler = new MyDBHandler(this, null, null, 1);

        final CheckBox smscheckbox = (CheckBox) findViewById(R.id.smschek);
        final CheckBox callcheckbox = (CheckBox) findViewById(R.id.callchek);

        MyDBHandler db1 = new MyDBHandler(this,null, null, 1);

       List<Whereyatt> contacts = db1.getAllContacts();



         ListView mohsinsListView = (ListView) findViewById(R.id.listView2);


        if (mohsinsListView != null) {
            mohsinsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                    TextView textView = (TextView) view.findViewById(R.id.contact_name);
                    String text = textView.getText().toString();
                    MyDBHandler db1 = new MyDBHandler(DetailedSetting.this ,null, null, 1);

                    db1.removeSingleContact(text);
                    System.out.println("Choosen Country = : " + text);

                }});

        }

        ListAdapter mohsinsAdapter2 = new CustomAdapter2(this, contacts);
        mohsinsListView.setAdapter(mohsinsAdapter2);
        // String data;

        Intent i = getIntent();
        // getting attached intent data
        product = i.getStringExtra("product");


        SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

        boolean checksmspolice = sharedpref3.getBoolean("policesms",false);
        if(product.equals("Police Encounter")&&checksmspolice){

           smscheckbox.setChecked(true);

        }
        else {
            smscheckbox.setChecked(false);
        }

        boolean checkcallpolice = sharedpref3.getBoolean("policecall",false);


        if(product.equals("Police Encounter")&&checkcallpolice){

            callcheckbox.setChecked(true);

        }
        else {
            callcheckbox.setChecked(false);
        }



        smscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                //police sms checkbox
                if(smscheckbox.isChecked()&&product.equals("Police Encounter")){
                    editor.putBoolean("policesms", true);
                    editor.commit();

                }else if(smscheckbox.isChecked()==false&&product.equals("Police Encounter")){
                    editor.putBoolean("policesms", false);
                    editor.commit();
                }





            }
        });

        callcheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                //police sms checkbox
                if(callcheckbox.isChecked()&&product.equals("Police Encounter")){
                    editor.putBoolean("policecall", true);
                    editor.commit();

                }else if(callcheckbox.isChecked()==false&&product.equals("Police Encounter")){
                    editor.putBoolean("policecall", false);
                    editor.commit();
                }


                else if(callcheckbox.isChecked()&&product.equals("Lonely")){
                 /*   editor.putBoolean("lonelycall", true);
                    editor.commit();
                    */

                }
                else if(callcheckbox.isChecked()==false&&product.equals("Lonely")){
                 /*   editor.putBoolean("lonelycall", false);
                    editor.commit();#
                    */

                }



            }
        });
        /*
        smscheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                       SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);


                                                       SharedPreferences.Editor editor = sharedpref.edit();

                                                       if(product=="Police Encounter") {

                                                           editor.putBoolean("sendmappolice", true);
                                                           Toast.makeText(getApplicationContext(), "ticked", Toast.LENGTH_LONG).show();
                                                           editor.apply();

                                                       }
                                                       else{
                                                           editor.putBoolean("sendmappolice", false);
                                                           Toast.makeText(getApplicationContext(),"not ticked",Toast.LENGTH_LONG).show();
                                                       }




                                                   }
                                               }
        );

*/


    }

}
