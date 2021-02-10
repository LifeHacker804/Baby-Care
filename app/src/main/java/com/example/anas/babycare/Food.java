package com.example.anas.babycare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent i=getIntent();
        final String childname=i.getStringExtra("childname");
        final String parentname=i.getStringExtra("parentname");


        TextView t=(TextView)findViewById(R.id.childname);
        t.setText(childname);



                Button b7=(Button)findViewById(R.id.back);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),ChildHome.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });



        UserDB userDb = new UserDB(getApplicationContext());
        final SQLiteDatabase sqldb = userDb.getWritableDatabase();

        final Button b2=(Button)findViewById(R.id.add);
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText foodname = (EditText) findViewById(R.id.foodname);
                EditText foodtime = (EditText) findViewById(R.id.foodtime);
                EditText foodtime2 = (EditText) findViewById(R.id.foodtime2);
                EditText fooddate = (EditText) findViewById(R.id.fooddate);
                EditText fooddate2 = (EditText) findViewById(R.id.fooddate2);
                EditText fooddate3 = (EditText) findViewById(R.id.fooddate3);
                EditText foodquantity = (EditText) findViewById(R.id.foodquantity);

                // Add in database


                String time=foodtime.getText()+":"+foodtime2.getText();
                String date=fooddate.getText()+"/"+fooddate2.getText()+"/"+fooddate3.getText();
                ContentValues values = new ContentValues();
                values.put("fname",foodname.getText().toString());
                values.put("time", time.toString());
                values.put("date",date.toString());
                values.put("quantity", foodquantity.getText()+" ml".toString());
                values.put("cname", childname);



                // Inserting Row
                long row2=sqldb.insert("food", null, values);

                Toast.makeText(Food.this,"Added"+row2,Toast.LENGTH_SHORT).show();


                Intent i=new Intent(getApplicationContext(),ChildHome.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }

        });


    }
}
