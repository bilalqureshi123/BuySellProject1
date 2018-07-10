package com.example.bilalqureshi.buysellproject;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.data;

public class searchResult extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    ArrayList<ModelItem> itemList;
    private DrawerLayout mDrawLayout;
    private ActionBarDrawerToggle mToggle;
    DataItemHelper db;
String newString;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        db=new DataItemHelper(this);
        recyclerView= (RecyclerView) findViewById(R.id.rv2);
        db.insertdefault();
        itemList=new ArrayList<>();

        String nuser;

        if(savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){

                nuser=null;

            }
            else{

                nuser=extras.getString("itemname");
                newString=extras.getString("user");


            }
        }
        else {

            nuser=(String)savedInstanceState.getSerializable("itemname");
            newString=(String)savedInstanceState.getSerializable("user");

        }




        SQLiteDatabase ds=db.getReadableDatabase();
        nuser="%"+nuser+"%";
        Cursor c=ds.rawQuery("SELECT *FROM items where name like ?",new String[]{nuser});
        if(c.getCount()==0){
            Toast.makeText(getApplicationContext(),"No search results found",Toast.LENGTH_SHORT);
        }
        else {
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    String bd = c.getString(c.getColumnIndex("price")) + " RS";
                    if (bd.equals(" RS")) {

                    } else {
                        itemList.add(new ModelItem(R.drawable.stvg, c.getString(c.getColumnIndex("name")), c.getString(c.getColumnIndex("dis")), bd))
                        ;
                    }
                    c.moveToNext();
                }
            }
        }




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        RecyclerView.LayoutManager rvLiLayoutManager = layoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        ItemAdapter adapter;
        adapter = new ItemAdapter(this, itemList, new CustomItemClickListener() {
            TextView item_name, item_desc, item_price;


            public void onItemClick(View v, int position) {

                item_name = (TextView) v.findViewById(R.id.item_name);
                item_desc = (TextView) v.findViewById(R.id.item_place);
                item_price = (TextView) v.findViewById(R.id.item_price);


                Intent intent = new Intent(getApplicationContext(), infoActivity.class);

                intent.putExtra("itemname", item_name.getText().toString());
                intent.putExtra("itemdesc", item_desc.getText().toString());
                intent.putExtra("itemprice", item_price.getText().toString());
                startActivity(intent);

                //Toast.makeText(getApplicationContext(),item_name.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
        mDrawLayout = (DrawerLayout) findViewById(R.id.drawer6);
        mToggle = new ActionBarDrawerToggle(this, mDrawLayout, R.string.open, R.string.close);
        mDrawLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav6);

        navigationView.setNavigationItemSelectedListener(this);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.hom){
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("user",newString);
            startActivity(intent);


        }
        if(id==R.id.search){
            Intent intent=new Intent(this,searchActivity.class);
            intent.putExtra("user",newString);
            startActivity(intent);

        }

        if(id==R.id.sellitem){
            //Intent intento= new Intent(this,addActivity.class);
            //startActivity(intento);

        }

        if(id==R.id.yourads){
            Intent intentoo=new Intent(this,Main24Activity.class);
            intentoo.putExtra("user",newString);
            startActivity(intentoo);

        }
        if(id==R.id.logout){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to Logout ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intents= new Intent(getApplicationContext(),Main2Activity.class);
                            startActivity(intents);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer6);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
