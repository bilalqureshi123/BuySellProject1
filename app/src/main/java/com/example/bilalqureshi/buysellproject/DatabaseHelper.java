package com.example.bilalqureshi.buysellproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.sql.SQLData;

/**
 * Created by bilalqureshi on 28/04/2018.
 */

      public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users ( username text primary key, password text, number text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public boolean insert(String username, String pass,String num){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",pass);
        contentValues.put("number",num);
        long ii=db.insert("users",null,contentValues);
        if(ii==-1) return false;
        else return true;

    }
    public boolean checkUser(String username){
        SQLiteDatabase sq=this.getReadableDatabase();

            Cursor cursor = sq.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
            if (cursor.getCount() > 0) {
                return false;
            } else {
                return true;
            }



    }
    public boolean confUserPass(String username, String password){
        SQLiteDatabase sb=this.getReadableDatabase();
        Cursor cursor = sb.rawQuery("SELECT * FROM users WHERE username=? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
