package com.example.anas.babycare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class ParentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentprofile);

        Intent g=getIntent();
        final String parentname=g.getStringExtra("parentname");

        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.pass);
        final EditText username = (EditText) findViewById(R.id.username);
        final TextView name = (TextView) findViewById(R.id.name);

        name.setText(parentname);




        UserDB userDb = new UserDB(this);
        final SQLiteDatabase sqldb = userDb.getWritableDatabase();

                String columns[] = {"username", "pass", "phone", "email"};
                final String[] parentnamearray = {parentname};
                Cursor c = sqldb.query("user", columns, "name=?", parentnamearray, null, null, null);

                if (c.moveToFirst()) {
                    do {

                        username.setText(c.getString(0));
                        pass.setText(c.getString(1));
                        phone.setText(c.getString(2));
                        email.setText(c.getString(3));
                    }
                    while (c.moveToNext());
                }


                Button b1 = (Button) findViewById(R.id.back);
                b1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(), Home.class);
                        i.putExtra("parentname", parentname);
                        startActivity(i);
                        finish();
                    }
                });




                Button b2 = (Button) findViewById(R.id.update);
                b2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        TextView phoneerror = (TextView) findViewById(R.id.phoneerror);
                        phoneerror.setVisibility(View.GONE);
                        TextView usernameerror = (TextView) findViewById(R.id.usernameerror);
                        usernameerror.setVisibility(View.GONE);
                        TextView emailerror = (TextView) findViewById(R.id.emailerror);
                        emailerror.setVisibility(View.GONE);
                        TextView passerror = (TextView) findViewById(R.id.passerror);
                        passerror.setVisibility(View.GONE);
                        TextView error = (TextView) findViewById(R.id.updateerror);
                        error.setVisibility(View.GONE);


                        if(username.getText().toString().equals("")) {
                            usernameerror.setVisibility(View.VISIBLE);
                        }
                        else if(phone.getText().toString().equals("")) {
                            phoneerror.setVisibility(View.VISIBLE);
                        }

                        else if(email.getText().toString().equals("")) {
                            emailerror.setVisibility(View.VISIBLE);
                        }
                        else if(pass.getText().toString().equals("")) {
                            passerror.setVisibility(View.VISIBLE);
                        }
                        else {

                           // Add in database

                           ContentValues values = new ContentValues();
                           values.put("phone", phone.getText().toString());
                           values.put("email", email.getText().toString());
                           values.put("pass", pass.getText().toString());
                           values.put("username", username.getText().toString());
                           values.put("name", parentname.toString());


                           // Inserting Row

                           long row = sqldb.update("user", values, "name=?", parentnamearray);


                           Toast.makeText(ParentProfile.this, "Updated" + row, Toast.LENGTH_SHORT).show();
                           if(row==-1)
                           {
                               error.setVisibility(View.VISIBLE);
                           }
                           else {
                               TextView success = (TextView) findViewById(R.id.updatesuccess);
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
