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

                        db.addContact(new Whereyatt(name,cNumber,product));


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

        Intent i = getIntent();
        // getting attached intent data
        product = i.getStringExtra("product");

       List<Whereyatt> contacts = db1.getPoliceContacts(product);



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


/*

        SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

        boolean checksmspolice = sharedpref3.getBoolean("policesms",false);

        if(product.equals("Police Encounter")&&checksmspolice){

           smscheckbox.setChecked(true);

        }
        else {
            smscheckbox.setChecked(false);
        }
        */
if(product.equals("Police Encounter")) {
    SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

    boolean checksmspolice = sharedpref3.getBoolean("policesms", false);

    if (checksmspolice) {

        smscheckbox.setChecked(true);

    } else {
        smscheckbox.setChecked(false);
    }


    boolean checkcallpolice = sharedpref3.getBoolean("policecall", false);


    if (checkcallpolice) {

        callcheckbox.setChecked(true);

    } else {
        callcheckbox.setChecked(false);
    }

    smscheckbox.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpref.edit();
            //police sms checkbox
            if(smscheckbox.isChecked()){
                editor.putBoolean("policesms", true);
                editor.commit();

            }else {
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
            if(callcheckbox.isChecked()){
                editor.putBoolean("policecall", true);
                editor.commit();

            }else {
                editor.putBoolean("policecall", false);
                editor.commit();
            }



        }
    });

}
        //for lonely
        if(product.equals("Lonely")) {
            SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

            boolean checksmslonely = sharedpref3.getBoolean("lonelysms", false);

            if (checksmslonely) {

                smscheckbox.setChecked(true);

            } else {
                smscheckbox.setChecked(false);
            }


            boolean checkcalllonely = sharedpref3.getBoolean("lonelycall", false);


            if (checkcalllonely) {

                callcheckbox.setChecked(true);

            } else {
                callcheckbox.setChecked(false);
            }

            smscheckbox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    //police sms checkbox
                    if(smscheckbox.isChecked()){
                        editor.putBoolean("lonelysms", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("lonelysms", false);
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
                    if(callcheckbox.isChecked()){
                        editor.putBoolean("lonelycall", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("lonelycall", false);
                        editor.commit();
                    }



                }
            });

        }

        //for lit
        if(product.equals("its Lit")) {
            SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

            boolean checksmslit = sharedpref3.getBoolean("litsms", false);

            if (checksmslit) {

                smscheckbox.setChecked(true);

            } else {
                smscheckbox.setChecked(false);
            }


            boolean checkcalllit = sharedpref3.getBoolean("litcall", false);


            if (checkcalllit) {

                callcheckbox.setChecked(true);

            } else {
                callcheckbox.setChecked(false);
            }

            smscheckbox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    //police sms checkbox
                    if(smscheckbox.isChecked()){
                        editor.putBoolean("litsms", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("litsms", false);
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
                    if(callcheckbox.isChecked()){
                        editor.putBoolean("litcall", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("litcall", false);
                        editor.commit();
                    }



                }
            });

        }


        //for scared
        if(product.equals("Scared")) {
            SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

            boolean checksmsscared = sharedpref3.getBoolean("scaredsms", false);

            if (checksmsscared) {

                smscheckbox.setChecked(true);

            } else {
                smscheckbox.setChecked(false);
            }


            boolean checkcallscared = sharedpref3.getBoolean("scaredcall", false);


            if (checkcallscared) {

                callcheckbox.setChecked(true);

            } else {
                callcheckbox.setChecked(false);
            }

            smscheckbox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    //police sms checkbox
                    if(smscheckbox.isChecked()){
                        editor.putBoolean("scaredsms", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("scaredsms", false);
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
                    if(callcheckbox.isChecked()){
                        editor.putBoolean("scaredcall", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("scaredcall", false);
                        editor.commit();
                    }



                }
            });

        }

        //for custom
        if(product.equals("custom")) {
            SharedPreferences sharedpref3 = getSharedPreferences("permission", Context.MODE_PRIVATE);

            boolean checksmscustom = sharedpref3.getBoolean("customsms", false);

            if (checksmscustom) {

                smscheckbox.setChecked(true);

            } else {
                smscheckbox.setChecked(false);
            }


            boolean checkcallcustom = sharedpref3.getBoolean("customcall", false);


            if (checkcallcustom) {

                callcheckbox.setChecked(true);

            } else {
                callcheckbox.setChecked(false);
            }

            smscheckbox.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    SharedPreferences sharedpref = getSharedPreferences("permission", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpref.edit();
                    //police sms checkbox
                    if(smscheckbox.isChecked()){
                        editor.putBoolean("customsms", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("customsms", false);
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
                    if(callcheckbox.isChecked()){
                        editor.putBoolean("customcall", true);
                        editor.commit();

                    }else{
                        editor.putBoolean("customcall", false);
                        editor.commit();
                    }



                }
            });

        }

    }

}
