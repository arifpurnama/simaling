package com.bahri.simaling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.wang.avi.AVLoadingIndicatorView;

public class MainActivity extends AppCompatActivity {
//Deklarasi variable
    private LinearLayout lv_loading;
    private AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_loading=(LinearLayout) findViewById(R.id.lv_loading);
        avi=(AVLoadingIndicatorView) findViewById(R.id.avi);

        avi.setIndicator("PacmanIndicator");

        //Delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(MainActivity.this,Login.class);
                startActivity(i);
                finish();
            }
        },3000);
    }
}
