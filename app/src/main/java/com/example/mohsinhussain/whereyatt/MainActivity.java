package com.example.mohsinhussain.whereyatt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView settingButton;
    ImageView policeButton;
    ImageView lonelyButton;
    ImageView litButton;
    ImageView scaredButton;
    TextView customButton;

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
                                                smsIntent.putExtra("address","090078601");
                                                smsIntent.putExtra("sms_body","your desired message");
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
                                                smsIntent.putExtra("address","090078601");
                                                smsIntent.putExtra("sms_body","your desired message");
                                                startActivity(smsIntent);

                                            }
                                        });
                                AlertDialog alert12 = builder1.create();
                                alert12.show();
                            }
                        });

                builder1.setNeutralButton( "Cancel",
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
