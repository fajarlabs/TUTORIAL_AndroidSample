package com.spasi.android;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Spasi Android | CALL");

        Button btnCallFajar = (Button) findViewById(R.id.btnCallFajar);
        Button btnCallRizaldi = (Button) findViewById(R.id.btnCallRizaldi);
        Button btnCallFajri = (Button) findViewById(R.id.btnCallFajri);

        btnCallFajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUser("089663159652");
            }
        });

        btnCallRizaldi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUser("08990091860");
            }
        });

        btnCallFajri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callUser("089650038556");
            }
        });

    }

    final private void callUser(String phoneNumber) {
        if(phoneNumber != null) {
            try {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel: "+phoneNumber));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Permission call is denied",Toast.LENGTH_LONG).show();
                } else {
                    startActivity(callIntent);
                }
            } catch (ActivityNotFoundException activityException) {
                Log.e("Calling a Phone Number", "Call failed", activityException);
            }
        } else {
            Toast.makeText(this,"Please check your phone number",Toast.LENGTH_LONG).show();
        }
    }

}
