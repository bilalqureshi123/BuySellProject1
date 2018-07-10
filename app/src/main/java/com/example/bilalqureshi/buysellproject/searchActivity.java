package com.example.bilalqureshi.buysellproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class searchActivity extends AppCompatActivity {

Button button;
    EditText m;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        button=(Button) findViewById(R.id.button12);
        m=(EditText) findViewById(R.id.item12);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ff=m.getText().toString();

                Intent intent=new Intent(getApplicationContext(),searchResult.class);
                intent.putExtra("user",newString);
                intent.putExtra("itemname",ff);
                startActivity(intent);

            }
        });

    }
}
