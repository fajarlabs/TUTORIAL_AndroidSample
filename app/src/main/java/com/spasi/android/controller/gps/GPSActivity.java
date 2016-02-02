package com.spasi.android.controller.gps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.spasi.android.R;

public class GPSActivity extends AppCompatActivity implements LocationListener {

    private LocationManager _LocationManager;
    private String _provider;
    private Location _location;
    private boolean _isGPSEnabled;
    private boolean _isNetworkEnabled;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private static final String TAG = "GPSActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
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
        getSupportActionBar().setTitle("Spasi Android | GPS");

        // Construct component layout
        editTextLatitude = (EditText)findViewById(R.id.editTextLatitude);
        editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);

        // Checking package
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // code show permission
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Permission OK", Toast.LENGTH_LONG).show();

            // GPS Test
            _LocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            _isGPSEnabled = _LocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            _isNetworkEnabled = _LocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!_isGPSEnabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

            if(!_isNetworkEnabled) {
                // Code
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                startActivity(intent);
            }

            try {
                _LocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (_LocationManager != null) {
                    _location = _LocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                }
            }catch(Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        editTextLatitude.setText(String.valueOf(location.getLatitude()));
        editTextLongitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
