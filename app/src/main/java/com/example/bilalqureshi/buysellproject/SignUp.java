package com.example.bilalqureshi.buysellproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
DatabaseHelper db;
    TextView textUser,textPass,textPassr,textPhone;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
     textUser=(TextView) findViewById(R.id.username);
        textPass=(TextView) findViewById(R.id.passwordi);
      textPassr=(TextView) findViewById(R.id.passwordir);
      textPhone=(TextView) findViewById(R.id.phone);
        registerButton = (Button) findViewById(R.id.button1);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
 db=new DatabaseHelper(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=textPass.getText().toString();
                String passr=textPassr.getText().toString();
                String user=textUser.getText().toString();
                String phone=textPhone.getText().toString();

                if(!pass.equals(passr)){
                    Toast.makeText(getApplicationContext(),"Password don't match try again",Toast.LENGTH_SHORT).show();

                }
                else if(pass==user){
                    Toast.makeText(getApplicationContext(),"Username and Password Can't be same",Toast.LENGTH_SHORT).show();
                }
              else if(pass.equals("")||passr.equals("")||phone.equals("")||user.equals("")){
                    Toast.makeText(getApplicationContext(),"You Can't Leave any text field empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean ll=db.checkUser(user);

                    if(ll==true){
                     Boolean checkUser=db.insert(user,pass,phone);
                  if(checkUser==true) {
                      Toast.makeText(getApplicationContext(),"Registration Successfull now you will login!",Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                      intent.putExtra("user",user);
                      startActivity(intent);
                  }
                  else {
                      Toast.makeText(getApplicationContext(),"Registration Not Successfull due to exceptional error",Toast.LENGTH_SHORT).show();

                  }
                    }


                    else if(ll==false){
                        Toast.makeText(getApplicationContext(),"Username already exists please try another",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }
}
