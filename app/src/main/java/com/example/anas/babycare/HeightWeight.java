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

public class HeightWeight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_weight);

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
                i.putExtra("parentname",parentname);
                i.putExtra("childname",childname);
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
                EditText height = (EditText) findViewById(R.id.height);
                EditText weight = (EditText) findViewById(R.id.weight);

                // Add in database


                ContentValues values = new ContentValues();
                values.put("height",height.getText()+" ft".toString());
                values.put("weight", weight.getText()+" kg".toString());
                values.put("cname", childname);



                // Inserting Row
                long row2=sqldb.insert("heightweight", null, values);

                Toast.makeText(HeightWeight.this,"Added"+row2,Toast.LENGTH_SHORT).show();


                Intent i=new Intent(getApplicationContext(),ChildHome.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();

            }

        });



    }
}
