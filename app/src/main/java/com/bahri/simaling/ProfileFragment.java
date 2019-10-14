package com.bahri.simaling;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import com.bumptech.glide.Glide;
import com.bahri.simaling.Utils.Userparselable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
private TextView nama,ktp,alamat,rt,rw,kel,kab,negara;
private CircleImageView gambar;
    private int ident;
    private Userparselable user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nama=(TextView) view.findViewById(R.id.nama_profile);
        ktp=(TextView) view.findViewById(R.id.nik_profile);
        alamat=(TextView) view.findViewById(R.id.alamat_profile);
        rt=(TextView) view.findViewById(R.id.rt_profile);
        rw=(TextView) view.findViewById(R.id.rw_profile);
        kel=(TextView) view.findViewById(R.id.kelurahan_profile);
        kab=(TextView) view.findViewById(R.id.kota_profile);
        negara=(TextView) view.findViewById(R.id.kewarganegaraan_profile);
        gambar=(CircleImageView) view.findViewById(R.id.photo_profile);


        try{
            Bundle bundle = getActivity().getIntent().getExtras();
            user = bundle.getParcelable("datauser");
            if(bundle!=null){
                ident = user.getId();
                nama.setText(user.getNama());
                ktp.setText(user.getNik());
                alamat.setText(user.getAlamat());
                rt.setText(user.getRt());
                rw.setText(user.getRw());
                kel.setText(user.getKel());
                kab.setText(user.getKab());
                negara.setText(user.getNegara());

                if(!user.getImage().equals("Gambar Kosong")){
                    String url_image = "http://192.168.1.5/Lingkungan/"+user.getImage();
                    url_image = url_image.replace(" ","%20");
                    try {
                       Log.i("Memuat Gambar: ",""+url_image);
                       Glide.with(getActivity()).load(url_image).into(gambar);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

}
