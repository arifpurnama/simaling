package com.bahri.simaling;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bahri.simaling.Utils.Userparselable;


/**
 * A simple {@link Fragment} subclass.
 */
public class LaporanFragment extends Fragment {
    private TextView nama,ktp;
    private int ident;
    private Userparselable user;
    public LaporanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        nama=(TextView) view.findViewById(R.id.nama_profile);
        ktp=(TextView) view.findViewById(R.id.nik_profile);

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
