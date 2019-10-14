package com.bahri.simaling;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bahri.simaling.Utils.Userparselable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private EditText nama,ktp,tgl,pesan;
    private Spinner jenis;
    private Button simpan;
    private int ident;
    private Userparselable user;
    private ProgressDialog progress;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    public LaporanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_laporan, container, false);
        nama=(EditText) view.findViewById(R.id.nama);
        ktp=(EditText) view.findViewById(R.id.nik);
        tgl=(EditText) view.findViewById(R.id.tgl);
        jenis=(Spinner) view.findViewById(R.id.jenis);
        pesan=(EditText) view.findViewById(R.id.pesan);
        simpan=(Button) view.findViewById(R.id.btn_lapor);
        requestQueue = Volley.newRequestQueue(getActivity());
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_lap();
            }

            private void simpan_lap() {
                //if(!validasi()) return;
                progress = new ProgressDialog(getActivity());
                progress.setMessage("inisialisasi ... ");
                progress.show();
                String url = "http://192.168.56.1/Lingkungan/Api/laporan.php?";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Userparselable userparselable = new Userparselable();
                        Log.i("Response JSON", "" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                tgl.setText("");
                                pesan.setText("");

                                Toast.makeText(getActivity(), "Laporan terkirim", Toast.LENGTH_SHORT).show();
                                progress.dismiss();

                            } else {
                                Toast.makeText(getActivity(), "Laporan gagal disimpan", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progress.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Tidak Ada Koneksi", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String snik = ktp.getText().toString();
                        String stgl = tgl.getText().toString();
                        //String sjenis = jenis.getSelectedItem().toString();
                        String spesan=pesan.getText().toString();


                        Map<String, String> parameter = new HashMap<>();
                        parameter.put("nik", snik);
                        parameter.put("tgl", stgl);
                        //parameter.put("jenis",sjenis);
                        parameter.put("pesan",spesan);

                        return parameter;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal();
            }

            private void tanggal() {
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tgl.setText(dateFormatter.format(newDate.getTime()));
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                /**
                 * Tampilkan DatePicker dialog
                 */
                datePickerDialog.show();
            }
        });

        try{
            Bundle bundle = getActivity().getIntent().getExtras();
            user = bundle.getParcelable("datauser");
            if(bundle!=null){
                ident = user.getId();
                nama.setText(user.getNama());
                ktp.setText(user.getNik());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }



}
