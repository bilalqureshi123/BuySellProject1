package com.example.bilalqureshi.buysellproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by bilalqureshi on 04/05/2018.
 */
public class DataItemHelper extends SQLiteOpenHelper {


    public DataItemHelper(Context context) {
        super(context, "Items.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table items ( ID INTEGER primary key AUTOINCREMENT, username text,type text, name text,dis text,price text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists items");
        onCreate(db);
    }
    public boolean insert(String username, String name,String dis,String price,String type){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("name",name);
        contentValues.put("dis",dis);
        contentValues.put("price",price);
        contentValues.put("type",type);
        long ii=db.insert("items",null,contentValues);
        if(ii==-1) return false;
        else return true;

    }

    public boolean delete( String name){

        SQLiteDatabase db = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        db.execSQL("DELETE FROM " + "items" + " WHERE " + "name" + "= '" + name + "'");
db.close();
        return false;

    }
    public void insertdefault(){

        SQLiteDatabase sq=this.getReadableDatabase();
        Cursor cursor=sq.rawQuery("SELECT *FROM items",null);

        if(cursor.getCount()>0){

        }
        else{
            long i;
            /*
            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues contentValues= new ContentValues();
            contentValues.put("username","bilalq123");
            contentValues.put("name","HP notebook 2020");
            contentValues.put("dis"," 2GB ram 450GB hard drive");
            contentValues.put("type","Computer Accessories");
            contentValues.put("price","54000"); //
             i= db.insert("items",null,contentValues);
            contentValues.put("username","bilalq123");
            contentValues.put("name","Macbook pro notebook 2020");
            contentValues.put("dis"," 2GB ram 450GB hard drive");
            contentValues.put("type","Computer Accessories");
            contentValues.put("price","54000"); //
            i= db.insert("items",null,contentValues);
            contentValues.put("username","bilalq123");
            contentValues.put("name","HP notebook 2020");
            contentValues.put("dis"," 2GB ram 450GB hard drive");
            contentValues.put("type","Computer Accessories");
            contentValues.put("price","54000"); //
            i= db.insert("items",null,contentValues);


            if(i==-1){

            }

*/
        }

    }

}
