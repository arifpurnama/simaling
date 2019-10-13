package com.bahri.simaling;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformasiFragment extends Fragment {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;

    private InformasiFragment instace;

    public InformasiFragment() {
        // Required empty public constructor
        instace = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_informasi, container, false);

        standardQueueStringRequest(rootview);
        jsonRequest(rootview);

    return rootview;
    }

    private void standardQueueStringRequest(View rootview) {
        
    }

    private void jsonRequest(View rootview) {
    }

}
