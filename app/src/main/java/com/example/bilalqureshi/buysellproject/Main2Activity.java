package com.example.bilalqureshi.buysellproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    DatabaseHelper dbo;
   public void buttonSign(View view){

        Intent intent = new Intent(getApplicationContext(),SignUp.class);

        startActivity(intent);
    }

   EditText editText;
    EditText editText1;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
          editText =(EditText) findViewById(R.id.usern);
          editText1=(EditText) findViewById(R.id.passw);

        actionBar.hide();
        button =(Button) findViewById(R.id.button);
        dbo=new DatabaseHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String usern=  editText.getText().toString();
               String passw= editText1.getText().toString();


               boolean x=dbo.confUserPass(usern,passw);
              if(x==true){
                    Toast.makeText(getApplicationContext(),"You have successfully logged in!",Toast.LENGTH_SHORT).show();

                    Intent intento = new Intent(getApplicationContext(),MainActivity.class);
                  intento.putExtra("user",usern);
                    startActivity(intento);





                }
                else  {
                    Toast.makeText(getApplicationContext(),"Invalid username or password try again",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }


}
