package com.example.anas.babycare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button b1=(Button)findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
                finish();
            }
        });

        final EditText e1=(EditText)findViewById(R.id.username);
        final EditText e2=(EditText)findViewById(R.id.pass);




            UserDB userDb = new UserDB(this);
            final SQLiteDatabase sqldb = userDb.getReadableDatabase();
            Button b2 = (Button) findViewById(R.id.login);
            b2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    TextView error = (TextView) findViewById(R.id.error);
                    error.setVisibility(View.GONE);
                    TextView usernameerror = (TextView) findViewById(R.id.usernameerror);
                    usernameerror.setVisibility(View.GONE);
                    TextView passerror = (TextView) findViewById(R.id.passerror);
                    passerror.setVisibility(View.GONE);

                    if (e1.getText().toString().equals("")) {
                        usernameerror.setVisibility(View.VISIBLE);
                    }
                    else if(e2.getText().toString().equals("")) {
                        passerror.setVisibility(View.VISIBLE);
                    }
                    else {
                        String columns[] = {"username", "pass", "name"};
                        Cursor c = sqldb.query("user", columns, null, null, null, null, null);

                        if (c.moveToFirst()) {
                            do {
                                String username = String.valueOf(e1.getText());
                                String pass = String.valueOf(e2.getText());
                                if ((username.equals(c.getString(0))) && (pass.equals(c.getString(1)))) {
                                    Intent i = new Intent(getApplicationContext(), Home.class);
                                    i.putExtra("parentname", c.getString(2));
                                    startActivity(i);
                                    TextView success = (TextView) findViewById(R.id.success);
                                    success.setVisibility(View.VISIBLE);
                                    finish();
                                    break;

                                }
                            }
                            while (c.moveToNext());
                            c.moveToFirst();

                            do {
                                String username = String.valueOf(e1.getText());
                                String pass = String.valueOf(e2.getText());
                                if (!((username.equals(c.getString(0))) && (pass.equals(c.getString(1))))) {

                                    error.setVisibility(View.VISIBLE);
                                    break;


                                }
                            }
                            while (c.moveToNext());

                        }


                    }
                }

            });
        }

}
