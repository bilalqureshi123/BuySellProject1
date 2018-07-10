package com.example.bilalqureshi.buysellproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawLayout;
   private ActionBarDrawerToggle mToggle;
    String newString;
    public void onBackPressed() {
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
    public void computerClick(View view){
        Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
        intent.putExtra("user",newString);
        intent.putExtra("type","Computer");
        startActivity(intent);




    }
    public void animalClick(View view){
        Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
        intent.putExtra("user",newString);
        intent.putExtra("type","Animals");
        startActivity(intent);
    }

    public void vehicleClick(View view){
        Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
        intent.putExtra("user",newString);
        intent.putExtra("type","cars");
        startActivity(intent);
    }
   public void bookClick(View view){
       Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
       intent.putExtra("user",newString);
       intent.putExtra("type","Books");
       startActivity(intent);

   }
   public void homeClick(View view){
       Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
       intent.putExtra("user",newString);
       intent.putExtra("type","Home Accessories");
       startActivity(intent);
   }
   public void sportsClick(View view){
       Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
       intent.putExtra("user",newString);
       intent.putExtra("type","sports");
       startActivity(intent);

   }
    public void clothClick(View view){
        Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
        intent.putExtra("user",newString);
        intent.putExtra("type","clothes");
        startActivity(intent);

    }
    public void kidClick(View view){
        Intent intent=new Intent(getApplicationContext(),Main22Activity.class);
        intent.putExtra("user",newString);
        intent.putExtra("type","kids");
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawLayout=(DrawerLayout)findViewById(R.id.drawer3);



        if(savedInstanceState==null){
            Bundle extras=getIntent().getExtras();
            if(extras==null){
                newString=null;
            }
            else{
                newString=extras.getString("user");
            }
        }
        else {
            newString =(String) savedInstanceState.getSerializable("user");
        }

        mToggle= new ActionBarDrawerToggle(this,mDrawLayout,R.string.open,R.string.close);
        mDrawLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigator3);

            navigationView.setNavigationItemSelectedListener(this);





        }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.hom){

        }
        if(id==R.id.search){
           Intent intent=new Intent(this,searchActivity.class);
            intent.putExtra("user",newString);
            startActivity(intent);

        }

        if(id==R.id.sellitem){
            Intent intento= new Intent(this,addActivity.class);
            intento.putExtra("user",newString);
            startActivity(intento);


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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
