package com.bahri.simaling;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;


public class detail_news extends Fragment implements OnRefreshListener {
    NetworkImageView thumb_image;
    TextView judul, tgl, isi;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    SwipeRefreshLayout swipe;
    String id_news;

    private static final String TAG = detail_news.class.getSimpleName();

    public static final String TAG_ID       = "id";
    public static final String TAG_JUDUL    = "judul";
    public static final String TAG_TGL      = "tgl";
    public static final String TAG_ISI      = "isi";
    public static final String TAG_GAMBAR   = "gambar";

    private static final String url_detail  = Server.URL + "detail_news.php";
    String tag_json_obj = "json_obj_req";

    private detail_news instace;

    public detail_news() {
        // Required empty public constructor
        instace = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail_news, container, false);
        super.onCreate(savedInstanceState);

        thumb_image = (NetworkImageView) view.findViewById(R.id.gambar_news);
        judul       = (TextView) view.findViewById(R.id.judul_news);
        tgl         = (TextView) view.findViewById(R.id.tgl_news);
        isi         = (TextView) view.findViewById(R.id.isi_news);
        swipe       = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        id_news = getActivity().getIntent().getStringExtra(TAG_ID);

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                if (!id_news.isEmpty()) {
                    callDetailNews(id_news);
                }
            }
        });

        return view;
    }

    private void callDetailNews(final String id_news) {
        swipe.setRefreshing(true);
        StringRequest strReq = new StringRequest(Request.Method.POST, url_detail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response.toString());
                swipe.setRefreshing(false);

                try {
                    JSONObject obj = new JSONObject(response);

                    String Judul = obj.getString(TAG_JUDUL);
                    String Gambar = obj.getString(TAG_GAMBAR);
                    String Tgl = obj.getString(TAG_TGL);
                    String Isi = obj.getString(TAG_ISI);

                    judul.setText(Judul);
                    tgl.setText(Tgl);
                    isi.setText(Html.fromHtml(Isi));

                    if (obj.getString(TAG_GAMBAR) != "") {
                        thumb_image.setImageUrl(Gambar, imageLoader);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Detail News Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
    }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id_news);

                return params;
            }
    };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public void onRefresh() {
        callDetailNews(id_news);
    }

}
