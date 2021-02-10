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

public class Vaccination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

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
                EditText vacname = (EditText) findViewById(R.id.vacname);
                EditText vacdate = (EditText) findViewById(R.id.vacdate);
                EditText vacdate2 = (EditText) findViewById(R.id.vacdate2);
                EditText vacdate3 = (EditText) findViewById(R.id.vacdate3);

                // Add in database


                String date=vacdate.getText()+"/"+vacdate2.getText()+"/"+vacdate3.getText();
                ContentValues values = new ContentValues();
                values.put("vacname", vacname.getText().toString());
                values.put("date",date.toString());
                values.put("cname", childname);



                // Inserting Row
                long row2=sqldb.insert("vaccination", null, values);

                Toast.makeText(Vaccination.this,"Added"+row2,Toast.LENGTH_SHORT).show();


                Intent i=new Intent(getApplicationContext(),ChildHome.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();

            }

        });


    }
}
