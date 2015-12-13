package com.example.ixn.login.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by dev-team-ixn on 12/11/15.
 */
public class DBAdapter{
    private static final String DATABASE_NAME = "db_engine.db";
    private static final int DATABASE_VER = 1;
    private static final String TABLE_DB = "user_tbl";
    static final String PASSWORD = "password";
    static final String USERNAME = "username";
    private static final String DB_CREATE = "create table user_tbl (username text primary key, password text not null);";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private final Context context;
    private String msg = "mylogin-DBAdapter";

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private String logmsg = "mylogin-DBAdapter";
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            Log.d(logmsg, "Open DBAdapter");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DB );
            onCreate(db);
        }
    }

    public DBAdapter(Context context){
        this.context = context;
    }
    public DBAdapter open(){
        Log.d(msg, "Open DBAdapter");
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public boolean createUsername(Username username){
        ContentValues val = new ContentValues();
        val.put(USERNAME, username.getUsername());
        val.put(PASSWORD, username.getPassword());
        long retdb = 0;
        retdb = db.insert(TABLE_DB, null, val);
        if (retdb < 0)
        {
            return false;
        }
        return true;
    }

    public boolean deleteUsername(String username){
        return db.delete(TABLE_DB, USERNAME + "=" + username, null) > 0;
    }

    public Cursor getAllUsername() {
        return db.query(TABLE_DB, new String[]{
            USERNAME, PASSWORD
        }, null,null, null,null,null);
    }

    public Cursor getSingleUsername(String username){
        Log.d(msg, "sampai getSingleUsername");
        Cursor cursor = db.query(TABLE_DB, new String[]{
                USERNAME, PASSWORD
        }, USERNAME + "=" + "\"" + username + "\"", null, null, null, null);

        Log.d(msg, "sampai sini");
        if (cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public boolean updateUsername(Username username){
        ContentValues val = new ContentValues();
        val.put(PASSWORD, username.getPassword());

        return db.update(TABLE_DB, val, USERNAME + "=" + username.getUsername(), null) > 0;
    }
}
