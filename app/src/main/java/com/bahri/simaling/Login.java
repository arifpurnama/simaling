package com.bahri.simaling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class Login extends AppCompatActivity {
     public TextView aktivasi;


    private EditText t_username, t_password;
    private Button btnlogin;
    private ParseContent parseContent;
    private final int LoginTask = 1;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parseContent = new ParseContent(this);
        preferenceHelper = new PreferenceHelper(this);

        t_username = (EditText) findViewById(R.id.username);
        t_password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        aktivasi=(TextView)findViewById(R.id.l_aktivasi);


        //Pindah ke aktivasi
        aktivasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linkaktiv =new Intent(Login.this, daftar.class);
                startActivity(linkaktiv);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent utama=new Intent(Login.this, Utama.class);
                startActivity(utama);
//                try {
//                    login();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        });
    }

    private void login() throws IOException, JSONException{
        if (!Utils.isNetworkAvailable(Login.this)) {
            Toast.makeText(Login.this, "Memerlukan koneksi Internet!", Toast.LENGTH_SHORT).show();
            return;
        }
        Utils.showSimpleProgressDialog(Login.this);
        final HashMap<String, String> map = new HashMap<>();
        map.put(Constants.Params.NOKTP, t_username.getText().toString());
        map.put(Constants.Params.PASSWORD1, t_password.getText().toString());
        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                try {
                    HttpRequest req = new HttpRequest(Constants.ServiceType.LOGIN);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("Pesan", result);
                onTaskCompleted(result,LoginTask);
            }
        }.execute();
    }

    private void onTaskCompleted(String response,int task) {
        Log.d("responsejson", response.toString());
        Utils.removeSimpleProgressDialog();
        switch (task) {
            case LoginTask:
                if (parseContent.isSuccess(response)) {
                    parseContent.saveInfo(response);
                    Toast.makeText(Login.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Utama.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    this.finish();
                }else {
                    Toast.makeText(Login.this, parseContent.getErrorMessage(response), Toast.LENGTH_SHORT).show();
                }
        }
    }
}
