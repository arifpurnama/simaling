package com.bahri.simaling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class daftar extends AppCompatActivity {
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        login=(TextView)findViewById(R.id.l_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent baliklogin=new Intent(daftar.this, Login.class);
                startActivity(baliklogin);
            }
        });
    }
}
