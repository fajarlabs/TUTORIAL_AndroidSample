package com.spasi.android.model;

import android.database.Cursor;

import com.spasi.android.util.SQLite;

/**
 * Created by Spasi-Ideapad on 2/3/2016.
 */
public class SQLiteModel {

    private final String DBName = "contohdb.sqlite";
    private SQLite db;

    public SQLiteModel() {
        // Instance must declared
        db = new SQLite(DBName);
        // Query create table
        String sql = "CREATE TABLE IF NOT EXISTS  contoh_table "+
                "(_id INTEGER PRIMARY KEY," +
                "contohtext TEXT )";
        // Execute
        db.Do().execSQL(sql);
        db.Do().close();
    }

    public String getData(String id) {
        // Result
        String result = "";
        // SQL select data
        String sql = "SELECT * FROM contoh_table WHERE _id = ?";
        // Cursor position data
        Cursor rs =   db.Do().rawQuery(sql, new String[]{id});
        // First data
        if(rs.moveToFirst()){
            // Get data from column
            result = rs.getString(rs.getColumnIndex("contohtext"));
        }
        // Recordset close
        rs.close();
        // Db close
        db.Do().close();
        // Return result
        return result;
    }

    public void insertData(String text) {
        // Query to insert data
        String sql = "INSERT INTO contoh_table (contohtext) "
                + " VALUES(?)";
        // Excecute
        db.Do().execSQL(sql, new String[]{text});
        // Close
        db.Do().close();
    }

    public void deleteData(String text) {
        String sql = "DELETE FROM contoh_table WHERE _id = ? ";
        // Excecute
        db.Do().execSQL(sql, new String[]{text});
        // Close
        db.Do().close();
    }
}
