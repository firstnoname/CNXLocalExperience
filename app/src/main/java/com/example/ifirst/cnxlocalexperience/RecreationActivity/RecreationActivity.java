package com.example.ifirst.cnxlocalexperience.RecreationActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.Adapter.RecreationAdapter;
import com.example.ifirst.cnxlocalexperience.Model.Recreation;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecreationActivity extends AppCompatActivity {

    private final String JSON_URL = "http://whiskered-navy.hostingerapp.com/response/select_recreation.php";
    private final String JSON_URL_CH = "http://whiskered-navy.hostingerapp.com/response/select_recreation_ch.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Recreation> listRecreation;
    private RecyclerView recyclerView;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_recreation);

        listRecreation = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewID);



        if (language == "zh") {
            jsonRequestCH();
        } else {
            jsonRequest();
        }
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        language = prefs.getString("My_lang", "");
//        Log.d("language", language);
        setLocale(language);
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }

    private void jsonRequestCH() {
        request = new JsonArrayRequest(JSON_URL_CH, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("Response", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Recreation recreation = new Recreation();
                        recreation.setRe_name_eng(jsonObject.getString("re_name_eng"));
                        recreation.setRe_name_ch(jsonObject.getString("re_name_ch"));
                        recreation.setImg_1(jsonObject.getString("img1"));
                        recreation.setImg_2(jsonObject.getString("img2"));
                        recreation.setImg_3(jsonObject.getString("img3"));
                        recreation.setImg_4(jsonObject.getString("img4"));
                        recreation.setImg_5(jsonObject.getString("img5"));
                        recreation.setRe_type(jsonObject.getString("re_type"));
                        recreation.setRe_activity(jsonObject.getString("re_activity"));
                        recreation.setRe_time(jsonObject.getString("re_time"));
                        recreation.setRe_tel(jsonObject.getString("re_tel"));
                        recreation.setRe_car_park(jsonObject.getString("re_car_park"));
                        recreation.setRe_address(jsonObject.getString("re_address"));
                        recreation.setRe_latitude(jsonObject.getString("re_latitude"));
                        recreation.setRe_longitude(jsonObject.getString("re_longitude"));
                        recreation.setRe_website(jsonObject.getString("re_website"));
                        recreation.setRe_price(jsonObject.getString("re_price"));

                        listRecreation.add(recreation);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listRecreation);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("Response", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Recreation recreation = new Recreation();
                        recreation.setRe_name_eng(jsonObject.getString("re_name_eng"));
                        recreation.setRe_name_ch(jsonObject.getString("re_name_ch"));
                        recreation.setImg_1(jsonObject.getString("img1"));
                        recreation.setImg_2(jsonObject.getString("img2"));
                        recreation.setImg_3(jsonObject.getString("img3"));
                        recreation.setImg_4(jsonObject.getString("img4"));
                        recreation.setImg_5(jsonObject.getString("img5"));
                        recreation.setRe_type(jsonObject.getString("re_type"));
                        recreation.setRe_activity(jsonObject.getString("re_activity"));
                        recreation.setRe_time(jsonObject.getString("re_time"));
                        recreation.setRe_tel(jsonObject.getString("re_tel"));
                        recreation.setRe_car_park(jsonObject.getString("re_car_park"));
                        recreation.setRe_address(jsonObject.getString("re_address"));
                        recreation.setRe_latitude(jsonObject.getString("re_latitude"));
                        recreation.setRe_longitude(jsonObject.getString("re_longitude"));
                        recreation.setRe_website(jsonObject.getString("re_website"));
                        recreation.setRe_price(jsonObject.getString("re_price"));

                        listRecreation.add(recreation);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listRecreation);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<Recreation> listRecreation) {
        RecreationAdapter myAdapter = new RecreationAdapter(this, listRecreation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
