package com.example.anas.babycare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Tips extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);



        Intent i=getIntent();
        final String childname=i.getStringExtra("childname");
        final String parentname=i.getStringExtra("parentname");




        final WebView wv =(WebView)findViewById(R.id.webtip);
        wv.setWebViewClient(new WebViewClient());
        WebSettings ws=wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setBuiltInZoomControls(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setSupportZoom(true);
        wv.loadUrl("https://m.youtube.com/results?q=baby%20health%20tips&sm=1");





        Button b7=(Button)findViewById(R.id.back);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),ChildHome.class);
                i.putExtra("parentname",parentname);
                i.putExtra("childname",childname);
                startActivity(i);
                finish();
                wv.loadUrl("https://www.google.com.pk");
            }
        });
    }
}
