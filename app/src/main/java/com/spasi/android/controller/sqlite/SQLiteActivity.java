package com.spasi.android.controller.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.spasi.android.R;

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
        getSupportActionBar().setTitle("Spasi Android | SQLite");

    }

    /**
     * Cara membuat database
     */
    private void contohMembuatDatabase(){
        String kartuMemory = Environment.getExternalStorageDirectory().toString() ;
        String databasePart = kartuMemory + "/contohdb.sqlite";

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databasePart, null);
        String sql = "CREATE TABLE IF NOT EXISTS  contoh_table "+
                "(_id INTEGER PRIMARY KEY," +
                "contohtext TEXT )";
        db.execSQL(sql);
        db.close();
    }

    /**
     * Memasukkan teks kedalam database
     * @param text
     */
    private void Masukantext(String text){

        String kartuMemory = Environment.getExternalStorageDirectory().toString() ;
        String databasePart = kartuMemory + "/contohdb.sqlite";

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databasePart, null);
        String sql = "INSERT INTO contoh_table (contohtext) "
                + " VALUES(?)";

        db.execSQL(sql, new String[]{text});
        db.close();

    }

    /**
     * Mengambil Text berdasarkan id-nya
     * @param id
     * @return
     */
    private String ambilText(String id){
        String result = null;

        String kartuMemory = Environment.getExternalStorageDirectory().toString() ;
        String databasePart = kartuMemory + "/contohdb.sqlite";

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(databasePart, null);

        String sql = "SELECT * FROM contoh_table WHERE _id = ?";
        Cursor rs =   db.rawQuery(sql, new String[]{id});
        if(rs.moveToFirst()){
            result = rs.getString(rs.getColumnIndex("contohtext"));
        }

        rs.close();
        db.close();
        return result;
    }
}