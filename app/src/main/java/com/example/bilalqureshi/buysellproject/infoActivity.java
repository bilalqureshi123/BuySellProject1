package com.example.bilalqureshi.buysellproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class infoActivity extends AppCompatActivity {
TextView itemname,itemdesc,itemprice,itemowner,itemphone;
    DataItemHelper db;
    DatabaseHelper lo;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        itemname=(TextView) findViewById(R.id.item10);
        itemdesc=(TextView) findViewById(R.id.item);
        itemprice=(TextView) findViewById(R.id.item3);
        itemowner=(TextView) findViewById(R.id.item4);
        itemphone=(TextView) findViewById(R.id.item5);
        String newString,newString1,newString2;
        if(savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                newString=null;
                newString1=null;
                newString2=null;
            }
            else{
                newString=extras.getString("itemname");
                newString1=extras.getString("itemdesc");
                newString2=extras.getString("itemprice");

            }
        }
        else {

            newString =(String) savedInstanceState.getSerializable("itemname");
            newString1 =(String) savedInstanceState.getSerializable("itemdesc");
            newString2=(String) savedInstanceState.getSerializable("itemprice");
        }

        itemname.setText("Item : "+newString);
        itemdesc.setText("Discription: "+newString1);
        itemprice.setText("Price: "+newString2);
        String user=null;
        lo=new DatabaseHelper(this);
        db=new DataItemHelper(this);
        SQLiteDatabase ds=db.getReadableDatabase();
        Cursor c=ds.rawQuery("SELECT *FROM items where name=? AND dis=?",new String[]{newString, newString1});
        if(c.getCount()>0) {
            c.moveToFirst();
            user=c.getString(c.getColumnIndex("username"));
            itemowner.setText("Owner :"+user);

        }

        String pho=null;
        SQLiteDatabase dn=lo.getReadableDatabase();
        Cursor d=dn.rawQuery("SELECT *FROM users where username=?",new String[]{user});
        if(d.getCount()>0){
            d.moveToFirst();
            pho=d.getString(d.getColumnIndex("number"));
            itemphone.setText("Phone :"+pho);
        }



    }
}
