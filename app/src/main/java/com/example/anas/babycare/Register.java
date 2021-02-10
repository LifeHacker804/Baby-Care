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

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button b1=(Button)findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });


        UserDB userDb = new UserDB(getApplicationContext());
        final SQLiteDatabase sqldb = userDb.getWritableDatabase();

        Button b2=(Button)findViewById(R.id.register);
        b2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText lName = (EditText) findViewById(R.id.lname);
                EditText fName = (EditText) findViewById(R.id.fname);
                EditText phone = (EditText) findViewById(R.id.phone);
                EditText email = (EditText) findViewById(R.id.email);
                EditText pass = (EditText) findViewById(R.id.pass);
                EditText username = (EditText) findViewById(R.id.username);


                TextView phoneerror = (TextView) findViewById(R.id.phoneerror);
                phoneerror.setVisibility(View.GONE);
                TextView usernameerror = (TextView) findViewById(R.id.usernameerror);
                usernameerror.setVisibility(View.GONE);
                TextView fnameerror = (TextView) findViewById(R.id.fnameerror);
                fnameerror.setVisibility(View.GONE);
                TextView lnameerror = (TextView) findViewById(R.id.lnameerror);
                lnameerror.setVisibility(View.GONE);
                TextView emailerror = (TextView) findViewById(R.id.emailerror);
                emailerror.setVisibility(View.GONE);
                TextView passerror = (TextView) findViewById(R.id.passerror);
                passerror.setVisibility(View.GONE);
                TextView error = (TextView) findViewById(R.id.error);
                error.setVisibility(View.GONE);

                if (fName.getText().toString().equals("")) {
                    fnameerror.setVisibility(View.VISIBLE);
                }
                else if(lName.getText().toString().equals("")) {
                    lnameerror.setVisibility(View.VISIBLE);

                }

                else if(username.getText().toString().equals("")) {
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
                    values.put("name", fName.getText() + " " + lName.getText().toString());
                    values.put("phone", phone.getText().toString());
                    values.put("email", email.getText().toString());
                    values.put("pass", pass.getText().toString());
                    values.put("username", username.getText().toString());


                    // Inserting Row
                    long row = sqldb.insert("user", null, values);

                    Toast.makeText(Register.this, "Added" + row, Toast.LENGTH_SHORT).show();

                    if(row==-1)
                    {
                        error.setVisibility(View.VISIBLE);

                    }
                    else {
                        TextView success = (TextView) findViewById(R.id.success);
                        success.setVisibility(View.VISIBLE);
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                        finish();
                    }

                }
            }

        });

    }
}
