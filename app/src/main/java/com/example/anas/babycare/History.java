package com.example.anas.babycare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent i=getIntent();
        final String childname=i.getStringExtra("childname");
        final String parentname=i.getStringExtra("parentname");


        TextView t=(TextView)findViewById(R.id.childname);
        t.setText(childname);
        final ListView lv=(ListView)findViewById(R.id.listviewhistory);
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


                //Food
        final UserDB userDb = new UserDB(this);
        final SQLiteDatabase sqldb = userDb.getReadableDatabase();
        final String columns[]={"fname","time","date","quantity"};
        final String[] childnamearray={childname};

        Cursor c=sqldb.query("food",columns, "cname=?",childnamearray,null,null,null);
        String[] s=new String[1000];
        Arrays.fill(s," ");

        for(int j=0;j<c.getCount();j++) {
            c.moveToPosition(j);
            s[j] = "Food Name: "+c.getString(0) + "\nTime: " + c.getString(1)+"\nDate: "+c.getString(2) + "\nQuantity: " + c.getString(3);
        }
        final String[] values=s;

        final ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values);
        Button b6=(Button)findViewById(R.id.food2);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ListView lv=(ListView)findViewById(R.id.listviewhistory);
                lv.setAdapter(ad);
                lv.setVisibility(View.VISIBLE);
            }
        });


        //Height Weight
        final String columns2[]={"height","weight"};

        Cursor c2=sqldb.query("heightweight",columns2, "cname=?",childnamearray,null,null,null);
        String[] s2=new String[1000];
        Arrays.fill(s2," ");

        for(int j2=0;j2<c2.getCount();j2++) {
            c2.moveToPosition(j2);
            s2[j2] = "Height= "+c2.getString(0) + "\nWeight= " + c2.getString(1);
        }
        final String[] values2=s2;

        final ArrayAdapter ad2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values2);
        Button b5=(Button)findViewById(R.id.hw2);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lv.setAdapter(ad2);
                lv.setVisibility(View.VISIBLE);
            }
        });



        //Sleep
        final String columns3[]={"starttime","endtime","date"};

        Cursor c3=sqldb.query("sleep",columns3, "cname=?",childnamearray,null,null,null);
        String[] s3=new String[1000];
        Arrays.fill(s3," ");

        for(int j3=0;j3<c3.getCount();j3++) {
            c3.moveToPosition(j3);
            s3[j3] = "Start Time: "+c3.getString(0) + "\nEnd Time: " + c3.getString(1)+"\nDate: "+c3.getString(2);
        }
        final String[] values3=s3;

        final ArrayAdapter ad3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values3);
        Button b4=(Button)findViewById(R.id.sleep2);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lv.setAdapter(ad3);
                lv.setVisibility(View.VISIBLE);
            }
        });



        //Vaccination
        final String columns4[]={"vacname","date"};

        Cursor c4=sqldb.query("vaccination",columns4, "cname=?",childnamearray,null,null,null);
        String[] s4=new String[1000];
        Arrays.fill(s4," ");

        for(int j4=0;j4<c4.getCount();j4++) {
            c4.moveToPosition(j4);
            s4[j4] = "Vaccination: "+c4.getString(0) + "\nDate: " + c4.getString(1);
        }
        final String[] values4=s4;

        final ArrayAdapter ad4=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values4);
        Button b3=(Button)findViewById(R.id.vaccination2);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lv.setAdapter(ad4);
                lv.setVisibility(View.VISIBLE);
            }
        });






    }
}
