package com.spasi.android.controller.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spasi.android.R;
import com.spasi.android.model.SQLiteModel;

// http://www.ttsberita.com/2014/05/menggunakan-database-sqlite-di-android.html
// http://www.tutorialspoint.com/android/android_sqlite_database.htm
public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
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
        getSupportActionBar().setTitle("Spasi Android | SQLiteModel");

        final SQLiteModel sqlmodel = new SQLiteModel();

        // Button to insert data to sqlite
        Button btnInsertText = (Button) findViewById(R.id.btnInsertText);
        final TextView editTextInsert = (TextView) findViewById(R.id.editTextInsert);
        btnInsertText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlmodel.insertData(editTextInsert.getText().toString());
            }
        });

        // Button to get data from sqlite
        Button btnAmbil = (Button) findViewById(R.id.btnAmbil);
        final TextView editTextTampil = (TextView) findViewById(R.id.editTextTampil);
        final TextView editTextID = (TextView) findViewById(R.id.editTextID);
        btnAmbil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTampil.setText(sqlmodel.getData(editTextID.getText().toString()));
            }
        });
    }
}