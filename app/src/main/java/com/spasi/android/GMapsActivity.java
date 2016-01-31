package com.spasi.android;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spasi.android.dialog.DropPointDialog;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Indonesia
        LatLng centerMap = new LatLng(-2.465527915160804, 115.38871463388206);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centerMap));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                // Clear marker etc
                mMap.clear();
                // Add marker
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("You are here, lat : " + latLng.latitude + ", lng : " + latLng.longitude)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                // Create fragment dialog
                FragmentManager fm = getFragmentManager();
                // Instantiate dialog
                DropPointDialog dropPointDialog = new DropPointDialog();
                // Set GMaps
                dropPointDialog.setGMapsActivity(GMapsActivity.this);
                // Set latlng
                dropPointDialog.setLatLng(latLng);
                // Set address from geocode
                final String address = getMyLocationAddress(latLng.latitude, latLng.longitude);
                // Set address
                dropPointDialog.setAddress(address);
                dropPointDialog.setRetainInstance(true);
                // Only cancel using button not on touch outside
                //dropPointDialog.getDialog().setCanceledOnTouchOutside(false);
                // Set listener manual "setOnDismissListener"
                dropPointDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // Contoh dibawah ini
                        // Komponent harus di inisialisasi sebagai public static TextView tvLatLng
                        // Contoh
                        // MainActivity.tvLatLng.setText(address); // Close activity but not refresh activity
                    }
                });
                dropPointDialog.show(fm, "drop_point_dialog");
            }
        });
    }

    private String getMyLocationAddress(Double latitude, Double longitude) {
        Geocoder geocoder= new Geocoder(this, Locale.getDefault());
        String address = "";

        try {
            //Place your latitude and longitude
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
            if(addresses != null) {
                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();
                for(int i=0; i<fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }
                address += strAddress.toString();
            } else
                address += "No location found..!";
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, "Could not get address..!", Toast.LENGTH_LONG).show();
        }

        return address;
    }
}
