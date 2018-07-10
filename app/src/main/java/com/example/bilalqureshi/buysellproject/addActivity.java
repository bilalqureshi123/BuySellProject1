package com.example.bilalqureshi.buysellproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button button;
    DataItemHelper db;
    EditText in;
            EditText dis;
    EditText pice;
    String newString;
    String type;
    Spinner spinner;
    private static final String[]paths = {"Computers", "Books", "Vehicles","Home Accessories","Kids","Clothes","Animals","Sports"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        button=(Button)findViewById(R.id.button1);
        spinner=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //dropdownmenucode ends
        db=new DataItemHelper(this);


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
        in=(EditText) findViewById(R.id.item1);
        dis=(EditText) findViewById(R.id.dis1);
        pice=(EditText) findViewById(R.id.pir1);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String itename=in.getText().toString();
                String discr=dis.getText().toString();
                String pric=pice.getText().toString();
                if(itename.equals("")||discr.equals("")||pric.equals("")){
                    Toast.makeText(getApplicationContext(),"No field can be left empty",Toast.LENGTH_SHORT).show();

                }
                else {
                   boolean x= db.insert(newString,itename,discr,pric,type);
                    if(x==true){

                        Toast.makeText(getApplicationContext(),"Ad Successfully added",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("user",newString);
                        startActivity(intent);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error Uploading Ad",Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                type="Computer";
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                type="Books";
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                type="cars";
                break;
            case 3:
                type="Home Accessories";
                break;
            case 4:
                type="kids";
                break;

            case 5:
                type="clothes";
                break;
            case 6:
                type="Animals";
                break;
            case 7:
                type="sports";
                break;


        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
