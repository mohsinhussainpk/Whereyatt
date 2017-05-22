package com.example.mohsinhussain.whereyatt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    ImageView settingButton;
    ImageView policeButton;
    ImageView lonelyButton;
    ImageView litButton;
    ImageView scaredButton;
    TextView customButton;
    public String cityName ="city not found";
    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingButton = (ImageView) findViewById(R.id.setting);
        policeButton = (ImageView) findViewById(R.id.pl);
        lonelyButton = (ImageView) findViewById(R.id.l);
        litButton = (ImageView) findViewById(R.id.lit);
        scaredButton = (ImageView) findViewById(R.id.s);
        customButton = (TextView) findViewById(R.id.c);


        GPSTracker gpsTracker = new GPSTracker(this);
        String stringLatitude, stringLongitude;
        String nameOfLocation = "";
        if (gpsTracker.canGetLocation()) {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
            // getting city from latitude, longitude values
            double lat = Double.parseDouble(stringLatitude);
            double longi = Double.parseDouble(stringLongitude);
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(lat, longi, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String stateName = "lahore";

             cityName = addresses.get(0).getAddressLine(0);


            // String stateName = addresses.get(0).getAddressLine(1);
            // String countryName = addresses.get(0).getAddressLine(2);
            //  nameOfLocation = ConvertPointToLocation(stringLatitude,stringLongitude);


            dbHandler = new MyDBHandler(this, null, null, 1);

            settingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Setting.class));


                }
            });

            policeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //  AlertDialog.Builder alertDialog = new AlertDialog.Builder(getBaseContext());
                    Activity mActivity = null;

                    //  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                    builder1.setMessage("Do you want to?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Message",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                    builder1.setMessage("Who do you want to call?");
                                    builder1.setCancelable(true);


                                    builder1.setPositiveButton(
                                            "Message",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {


                                                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                                                    smsIntent.setType("vnd.android-dir/mms-sms");
                                                    smsIntent.putExtra("address", "090078601");
                                                   // smsIntent.putExtra("sms_body", "i am in a police encounter at" + cityName);
                                                    smsIntent.putExtra("sms_body", "i am in a police encounter at " + cityName);
                                                    startActivity(smsIntent);

                                                }
                                            });
                                    AlertDialog alert12 = builder1.create();
                                    alert12.show();
                                }
                            });

                    builder1.setNegativeButton(
                            "Call",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                    builder1.setMessage("Who do you want to call?");
                                    builder1.setCancelable(true);

                                    builder1.setPositiveButton(
                                            "Call",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "Your Phone_number"));
                                                    startActivity(intent);

                                                    Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                                                    smsIntent.setType("vnd.android-dir/mms-sms");
                                                    smsIntent.putExtra("address", "090078601");
                                                    smsIntent.putExtra("sms_body", "your desired message");
                                                    startActivity(smsIntent);

                                                }
                                            });
                                    AlertDialog alert12 = builder1.create();
                                    alert12.show();
                                }
                            });

                    builder1.setNeutralButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });
            lonelyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Setting.class));


                }
            });
            litButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Setting.class));


                }
            });
            scaredButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Setting.class));


                }
            });
            customButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, Setting.class));


                }
            });
        }
    }
}
