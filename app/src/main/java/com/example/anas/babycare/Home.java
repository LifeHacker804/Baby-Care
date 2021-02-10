package com.example.anas.babycare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent g=getIntent();
        final String parentname=g.getStringExtra("parentname");

        TextView tv=(TextView)findViewById(R.id.welcome);
        tv.setText(parentname);


        Button add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                Intent i2=new Intent(getApplicationContext(),AddChild.class);
                i2.putExtra("parentname",parentname);
                startActivity(i2);
                finish();
            }
        });

        Button profile=(Button)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),ParentProfile.class);
                i2.putExtra("parentname",parentname);
                startActivity(i2);
                finish();
            }
        });

        Button logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });



        final UserDB userDb = new UserDB(this);
        final SQLiteDatabase sqldb = userDb.getReadableDatabase();
        final String columns[]={"name"};
        final String parentnamearray[]={parentname};

        Cursor c=sqldb.query("child",columns, "pname=?",parentnamearray,null,null,null);
        String[] s=new String[1000];
        Arrays.fill(s,"");
        int i;
for(i=0;i<c.getCount();i++) {
    c.moveToPosition(i);
    s[i] = c.getString(0);
}
        final String[] values=s;


        final ListView lv=(ListView)findViewById(R.id.listview);

        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(!values[position].equals("")) {
                    Toast.makeText(Home.this,"u clicked "+values[position],Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), ChildHome.class);
                    i.putExtra("childname", values[position]);
                    i.putExtra("parentname", parentname);
                    startActivity(i);
                    finish();
                }
            }
        });



    }
}
