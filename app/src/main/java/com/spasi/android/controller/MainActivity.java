package com.spasi.android.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.spasi.android.R;
import com.spasi.android.controller.call.CallActivity;
import com.spasi.android.controller.event.EventAddActivity;
import com.spasi.android.controller.gps.GPSActivity;
import com.spasi.android.controller.login.Splash;
import com.spasi.android.controller.maps.GMapsActivity;
import com.spasi.android.controller.maps.GeocodeActivity;
import com.spasi.android.controller.radio.RadioStreamingActivity;
import com.spasi.android.controller.rsa.RSAActivity;
import com.spasi.android.controller.sqlite.SQLiteActivity;
import com.spasi.android.controller.tombol_kotak.Activity_tombol_kotak_kotak;
import com.spasi.android.controller.video.VideoStreamingActivity;
import com.spasi.android.controller.webservice.WebServiceActivity;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity  {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Main Layout
        setContentView(R.layout.activity_main);

        // Toolbar text
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spasi Android | Home");

        // GPS
        Button btnGPS = (Button)findViewById(R.id.btnGPS);
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GPSActivity.class);
                startActivity(i);
            }
        });

        // CALL
        Button btnCall = (Button)findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),CallActivity.class);
                startActivity(i);
            }
        });

        // Button Google GMaps
        Button btnGMaps = (Button)findViewById(R.id.btnGMaps);
        btnGMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),GMapsActivity.class);
                startActivity(i);
            }
        });

        // Button Radio Streaming
        Button btnRadioStreaming = (Button)findViewById(R.id.btnRadioStreaming);
        btnRadioStreaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),RadioStreamingActivity.class);
                startActivity(i);
            }
        });

        // Button sound
        Button btnAudio = (Button)findViewById(R.id.btnSound);
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.moking_jay);
                mediaPlayer.start();
            }
        });

        // Button Radio Streaming
        Button btnGeocode = (Button)findViewById(R.id.btnGeocode);
        btnGeocode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),GeocodeActivity.class);
                startActivity(i);
            }
        });

        // Button Webservice
        Button btnWebService = (Button)findViewById(R.id.btnWebService);
        btnWebService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),WebServiceActivity.class);
                startActivity(i);
            }
        });

        // Button Add Event
        Button btnAddEvent = (Button)findViewById(R.id.btnAddEvent);
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),EventAddActivity.class);
                startActivity(i);
            }
        });

        // Button Video Streaming
        Button btnVideoStreaming = (Button)findViewById(R.id.btnVideoStreaming);
        btnVideoStreaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),VideoStreamingActivity.class);
                startActivity(i);
            }
        });

        // Button Spinner
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setCanceledOnTouchOutside(false);
        Button btnSpinner = (Button)findViewById(R.id.btnSpinner);
        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setMessage("Sedang memuat data...");
                progress.setCanceledOnTouchOutside(false);
                progress.getWindow().setGravity(Gravity.CENTER);
                progress.show();
            }
        });

        // Button Splash
        Button btnSplash = (Button)findViewById(R.id.btnSplash);
        btnSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),Splash.class);
                startActivity(i);
            }
        });

        // Button Kotak Kotak
        Button btnKotakKotak = (Button)findViewById(R.id.btnKotakKotak);
        btnKotakKotak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),Activity_tombol_kotak_kotak.class);
                startActivity(i);
            }
        });

        // Button RSA Test
        Button btnRSA = (Button)findViewById(R.id.btnRSA);
        btnRSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),RSAActivity.class);
                startActivity(i);
            }
        });

        // Button SQLiteModel
        Button btnSQLite = (Button)findViewById(R.id.btnSQLite);
        btnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),SQLiteActivity.class);
                startActivity(i);
            }
        });
    }
}
