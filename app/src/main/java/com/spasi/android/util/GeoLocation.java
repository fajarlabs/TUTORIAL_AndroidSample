package com.spasi.android.util;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Spasi-Ideapad on 2/1/2016.
 */
public class GeoLocation {

    private Double lat;
    private Double lng;

    public GeoLocation(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * Get location info
     * @return
     */
    private JSONObject getLocationInfo() {

        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng="+this.lat.toString()+","+this.lng.toString()+"&sensor=true");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Get location address
     * @return
     */
    public String getLocationAddress() {
        JSONObject ret = getLocationInfo();
        JSONObject location;
        String address = null;
        try {
            location = ret.getJSONArray("results").getJSONObject(0);
            address = location.getString("formatted_address");
            Log.d("Address", "Formattted address : " + address);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return address;
    }
}
