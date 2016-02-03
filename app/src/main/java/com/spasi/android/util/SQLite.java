package com.spasi.android.util;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * Created by Spasi-Ideapad on 2/3/2016.
 */
public class SQLite {
    private String database;
    private String databasePart;
    private static SQLiteDatabase db;

    public SQLite(String database) {
        this.database = database;
    }

    /**
     * Get instance
     * @return
     */
    public SQLiteDatabase Do() {
        // Create path save db
        this.databasePart = Environment.getExternalStorageDirectory().toString() +"/"+ this.database;
        // Check if db variable is null ?
        if(null == db)
            db = SQLiteDatabase.openOrCreateDatabase(this.databasePart, null);

        // Local variable
        Boolean dbopen = false;
        try {
            dbopen = db.isOpen();
        }catch(Exception e) {
            e.printStackTrace();
        }

        // If db is closed ?
        if(!dbopen) {
            // Reset
            db = null;
            // Create db again
            db = SQLiteDatabase.openOrCreateDatabase(databasePart, null);
        }

        return db;
    }

}
