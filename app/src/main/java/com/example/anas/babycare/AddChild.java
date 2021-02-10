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

public class AddChild extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addchild);

        Intent g=getIntent();
        final String parentname=g.getStringExtra("parentname");
        final Button male=(Button)findViewById(R.id.male);
        final Button female=(Button)findViewById(R.id.female);
        final TextView t=(TextView)findViewById(R.id.gender);

        Button b1=(Button)findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Home.class);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }});


        UserDB userDb = new UserDB(getApplicationContext());
        final SQLiteDatabase sqldb = userDb.getWritableDatabase();

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setVisibility(View.VISIBLE);
                female.setVisibility(View.VISIBLE);
            }
        });

        male.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            male.setVisibility(View.GONE);
            female.setVisibility(View.GONE);
            t.setText("Male");
            }
        });

        female.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                male.setVisibility(View.GONE);
                female.setVisibility(View.GONE);
                t.setText("Female");
            }
        });


        Button b2=(Button)findViewById(R.id.add);
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
public void onClick(View v) {
                EditText lName = (EditText) findViewById(R.id.lname);
                EditText fName = (EditText) findViewById(R.id.fname);
                EditText height = (EditText) findViewById(R.id.height);
                EditText weight = (EditText) findViewById(R.id.weight);
                EditText bdate = (EditText) findViewById(R.id.bdate);
                EditText bdate2 = (EditText) findViewById(R.id.bdate2);
                EditText bdate3 = (EditText) findViewById(R.id.bdate3);
                TextView gender = (TextView) findViewById(R.id.gender);

                TextView lnameerror = (TextView) findViewById(R.id.lnameerror);
                lnameerror.setVisibility(View.GONE);
                TextView fnameerror = (TextView) findViewById(R.id.fnameerror);
                fnameerror.setVisibility(View.GONE);
                TextView bdayerror = (TextView) findViewById(R.id.bdayerror);
                bdayerror.setVisibility(View.GONE);
                TextView gendererror = (TextView) findViewById(R.id.gendererror);
                gendererror.setVisibility(View.GONE);
                TextView heighterror = (TextView) findViewById(R.id.heighterror);
                heighterror.setVisibility(View.GONE);
                TextView weighterror = (TextView) findViewById(R.id.weighterror);
                weighterror.setVisibility(View.GONE);
                TextView error = (TextView) findViewById(R.id.error);
                error.setVisibility(View.GONE);

                if (fName.getText().toString().equals("")) {
                    fnameerror.setVisibility(View.VISIBLE);
                }
                else if(lName.getText().toString().equals("")) {
                    lnameerror.setVisibility(View.VISIBLE);

                }

                else if((bdate.getText().toString().equals(""))||(bdate2.getText().toString().equals(""))||(bdate3.getText().toString().equals(""))) {
                    bdayerror.setVisibility(View.VISIBLE);
                }
                else if(gender.getText().toString().equals("Gender")) {
                    gendererror.setVisibility(View.VISIBLE);
                }

                else if(height.getText().toString().equals("")) {
                    heighterror.setVisibility(View.VISIBLE);
                }
                else if(weight.getText().toString().equals("")) {
                    weighterror.setVisibility(View.VISIBLE);
                }
                else {

                    // Add in database

                    ContentValues values = new ContentValues();
                    values.put("name", fName.getText() + " " + lName.getText().toString());
                    values.put("height", height.getText().toString());
                    values.put("weight", weight.getText().toString());
                    values.put("pname", parentname);


                    // Inserting Row
                    long row2 = sqldb.insert("child", null, values);

                    Toast.makeText(AddChild.this, "Added" + row2, Toast.LENGTH_SHORT).show();

                    if(row2==-1)
                    {
                        error.setVisibility(View.VISIBLE);

                    }
                    else {


                        TextView success = (TextView) findViewById(R.id.childsuccess);
                        success.setVisibility(View.VISIBLE);

                        Intent i = new Intent(getApplicationContext(), Home.class);
                        i.putExtra("parentname", parentname);
                        startActivity(i);
                        finish();
                    }

                }
            }

        });

    }

}
