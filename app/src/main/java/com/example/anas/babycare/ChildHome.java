package com.example.anas.babycare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChildHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.childhome);

        Intent i=getIntent();
        final String childname=i.getStringExtra("childname");
        final String parentname=i.getStringExtra("parentname");

        TextView t=(TextView)findViewById(R.id.childname);
        t.setText(childname);



        Button b1=(Button) findViewById(R.id.food);
        Button b2=(Button) findViewById(R.id.hw);
        Button b3=(Button) findViewById(R.id.sleep);
        Button b4=(Button) findViewById(R.id.vaccination);
        Button b5=(Button) findViewById(R.id.notes);
        Button b6=(Button) findViewById(R.id.tips);
        Button b8=(Button) findViewById(R.id.history);
        Button b7=(Button) findViewById(R.id.back);
        Button b9=(Button) findViewById(R.id.logout2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),Food.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();


            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),HeightWeight.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),Sleep.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),Vaccination.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),Notes.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),Tips.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
//
//                Uri u=Uri.parse("https://m.youtube.com/results?q=baby%20health%20tips&sm=1");
//                Intent i2=new Intent(Intent.ACTION_VIEW,u);
//                startActivity(i2);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=getIntent();
                String name=g.getStringExtra("name");
                Intent i=new Intent( getApplicationContext(),History.class);
                i.putExtra("childname",childname);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent( getApplicationContext(),Home.class);
                i.putExtra("parentname",parentname);
                startActivity(i);
                finish();
            }
        });


        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent( getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });





    }
}
