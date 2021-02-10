package com.example.anas.babycare;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Intent i=getIntent();
        final String childname=i.getStringExtra("childname");
        final String parentname=i.getStringExtra("parentname");

        TextView t=(TextView)findViewById(R.id.childname);
        t.setText(childname);



        Button b1=(Button)findViewById(R.id.back2);
        b1.setOnClickListener(new View.OnClickListener() {
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

        Button b2=(Button)findViewById(R.id.add);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                        EditText ntitle = (EditText) findViewById(R.id.ntitle);
                        EditText discription = (EditText) findViewById(R.id.discription);
                        EditText notedate = (EditText) findViewById(R.id.notedate);
                        EditText notedate2 = (EditText) findViewById(R.id.notedate2);
                        EditText notedate3 = (EditText) findViewById(R.id.notedate3);
                        String date=notedate.getText()+"/"+notedate2.getText()+"/"+notedate3.getText();


                // Add in database

                        ContentValues values = new ContentValues();
                        values.put("title",ntitle.getText().toString());
                        values.put("description", discription.getText().toString());
                values.put("date", date.toString());
                        values.put("cname", childname);

                        // Inserting Row
                        long row2=sqldb.insert("notes", null, values);

                        Toast.makeText(Notes.this,"Added"+row2,Toast.LENGTH_SHORT).show();


                Intent i=new Intent(getApplicationContext(),Notes.class);
                i.putExtra("parentname",parentname);
                i.putExtra("childname",childname);
                startActivity(i);
                finish();


            }
        });



        final SQLiteDatabase sqldb2 = userDb.getReadableDatabase();

        final String columns4[]={"title","description","date"};

        String[] childnamearray={childname};
        Cursor c4=sqldb2.query("notes",columns4, "cname=?",childnamearray,null,null,null);
        String[] s4=new String[1000];
        Arrays.fill(s4," ");

        for(int j4=0;j4<c4.getCount();j4++) {
            c4.moveToPosition(j4);
            s4[j4] =  "Date: " + c4.getString(2)+"\nTitle: "+c4.getString(0) +"\nDescription: " + c4.getString(1);
        }

        final ListView lv=(ListView)findViewById(R.id.listviewNotes);
        final String[] values4=s4;

        final ArrayAdapter ad4=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values4);
        lv.setAdapter(ad4);
        lv.setVisibility(View.VISIBLE);


    }
}
