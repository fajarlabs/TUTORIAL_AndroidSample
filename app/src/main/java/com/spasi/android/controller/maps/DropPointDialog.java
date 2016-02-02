package com.spasi.android.controller.maps;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.spasi.android.controller.maps.GMapsActivity;
import com.spasi.android.R;

/**
 * Created by Spasi on 1/15/2016.
 */

// Callback fragment ondismiss http://stackoverflow.com/questions/9853430/refresh-fragment-when-dialogfragment-is-dismissed
public class DropPointDialog extends DialogFragment {
    // LatLng Result
    private LatLng _latlng;
    private String _address;
    private GMapsActivity _GMapsActivity;
    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    public DropPointDialog() {
        // Empty constructor required for DialogFragment
    }

    // Set external latlng
    public void setLatLng(LatLng latLng) {
        _latlng = latLng;
    }

    // Set external GMapsActivity
    public void setGMapsActivity(GMapsActivity gMapsActivity) {
        _GMapsActivity = gMapsActivity;
    }

    // Set external address
    public void setAddress(String address) {
        _address = address;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setCanceledOnTouchOutside(false);
        // Use inflate for dialog
        View view = inflater.inflate(R.layout.drop_point_dialog_fragment, container);
        // Get component from layout drop_point_dialog_fragment
        EditText editTextLat = (EditText) view.findViewById(R.id.txt_lat_dialog);
        editTextLat.setText(String.valueOf(_latlng.latitude));
        EditText editTextLon = (EditText) view.findViewById(R.id.txt_lon_dialog);
        editTextLon.setText(String.valueOf(_latlng.longitude));
        EditText editTextAddress = (EditText) view.findViewById(R.id.txt_address_dialog);
        editTextAddress.setText(_address);
        Button btnSaveDialog = (Button) view.findViewById(R.id.btnSaveDialog);
        btnSaveDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set other activity component if component is set public static
                getDialog().dismiss();
                _GMapsActivity.onBackPressed();
            }
        });

        Button btnCancelDialog = (Button) view.findViewById(R.id.btnCancelDialog);
        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setTitle("Drop Point");

        return view;
    }
}
