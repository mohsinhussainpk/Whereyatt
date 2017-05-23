package com.example.mohsinhussain.whereyatt;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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


public class DetailedSetting extends AppCompatActivity {

    TextView addcontact;
    public static final int PICK_CONTACT = 1;
    MyDBHandler dbHandler;
    ImageView deletebutton;
    CheckBox smscheckbox;
    public String product;


    public void onClick(View v) {

        addcontact = (TextView) findViewById(R.id.add_contact);


        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_CONTACT);

        }

    }

    public void printDatabase() {

        String dbString = dbHandler.databaseToString();
        addcontact.setText(dbString);


    }

    public void saveInfo(View view) {


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

                        Whereyatt whereyatt = new Whereyatt(name);
                        dbHandler.addContact(whereyatt);
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
//      printDatabase();


        // dbHandler = new MyDBHandler(this, null, null, 1);
        //  printDatabase();
       // Intent i = getIntent();
        // getting attached intent data
        // String product = i.getStringExtra("product");
        // displaying selected product name
        //txtProduct.setText(product);
        final CheckBox smscheckbox = (CheckBox) findViewById(R.id.smschek);


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


        smscheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                if(smscheckbox.isChecked()&&product.equals("Police Encounter")){
                    editor.putBoolean("policesms", true);
                    editor.commit();

                    boolean boopolice = sharedpref.getBoolean("policesms",false);


                    Toast.makeText(getApplicationContext(),Boolean.toString(boopolice),Toast.LENGTH_SHORT).show();


                    System.out.println("Checked");
                }else if(smscheckbox.isChecked()==false&&product.equals("Police Encounter")){
                    System.out.println("Un-Checked");
                    editor.putBoolean("policesms", false);


                    editor.putString("string2", "this is the string");
                    editor.commit();
                   // Toast.makeText(getApplicationContext(),"not ticked",Toast.LENGTH_SHORT).show();
                    boolean boopolice1 = sharedpref.getBoolean("policesms",false);

                    Toast.makeText(getApplicationContext(),Boolean.toString(boopolice1),Toast.LENGTH_SHORT).show();



                }
                else if(smscheckbox.isChecked()&&product.equals("Lonely")){
                    System.out.println("Un-Checked");
                    Toast.makeText(getApplicationContext(),"ticked",Toast.LENGTH_SHORT).show();


                }
                else if(smscheckbox.isChecked()==false&&product.equals("Lonely")){
                    System.out.println("Un-Checked");
                    Toast.makeText(getApplicationContext(),"not ticked",Toast.LENGTH_SHORT).show();


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
