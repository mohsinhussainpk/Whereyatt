package com.example.mohsinhussain.whereyatt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

    private TextView addcontact;
    public static final int PICK_CONTACT = 1;
    MyDBHandler dbHandler;
    ImageView deletebutton;




    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_CONTACT);

                   }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        TextView addcontact = (TextView) findViewById(R.id.add_contact);

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



//                        printDatabase();
                      // addcontact.setText(name + " : " + cNumber);


                        Whereyatt whereyatt = new Whereyatt();

                      //  dbHandler.addContact(whereyatt);
                        whereyatt.set_contactname(name);
                        String contactname = whereyatt.get_contactname();
                        addcontact.setText(contactname);
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



       // dbHandler = new MyDBHandler(this, null, null, 1);
      //  printDatabase();
        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        //txtProduct.setText(product);




    }


    }
