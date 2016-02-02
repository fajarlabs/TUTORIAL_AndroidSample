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

    /**
     * Get location info
     * @param lat
     * @param lng
     * @return
     */
    public JSONObject getLocationInfo(String lat, String lng) {

        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=true");
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
     * @param lat
     * @param lng
     * @return
     */
    public String getLocationAddress(String lat, String lng) {
        JSONObject ret = getLocationInfo(lat,lng);
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
