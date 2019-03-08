package com.example.ifirst.cnxlocalexperience.LocalExperienceActivity;

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
import com.example.ifirst.cnxlocalexperience.Adapter.LocalExperienceAdapter;
import com.example.ifirst.cnxlocalexperience.Model.LocalExperience;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalExperienceVicinity extends AppCompatActivity {

    private final String JSON_URL = "http://whiskered-navy.hostingerapp.com/response/select_local_experience_vicinity.php";
    private final String JSON_URL_CH = "http://whiskered-navy.hostingerapp.com/response/select_local_experience_vicinity_ch.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<LocalExperience> listLocalExperience;
    private RecyclerView recyclerView;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_local_experience_vicinity);

        listLocalExperience = new ArrayList<>();

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

                Log.d("Response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        LocalExperience localExperience = new LocalExperience();
                        localExperience.setLe_name_eng(jsonObject.getString("le_name_eng"));
                        localExperience.setLe_name_ch(jsonObject.getString("le_name_ch"));
                        localExperience.setImg_1(jsonObject.getString("img1"));
                        localExperience.setImg_2(jsonObject.getString("img2"));
                        localExperience.setImg_3(jsonObject.getString("img3"));
                        localExperience.setImg_4(jsonObject.getString("img4"));
                        localExperience.setImg_5(jsonObject.getString("img5"));
                        localExperience.setLe_type(jsonObject.getString("le_type"));
                        localExperience.setLe_activity(jsonObject.getString("le_activity"));
                        localExperience.setLe_time(jsonObject.getString("le_time"));
                        localExperience.setLe_tel(jsonObject.getString("le_tel"));
                        localExperience.setLe_car_park(jsonObject.getString("le_car_park"));
                        localExperience.setLe_address(jsonObject.getString("le_address"));
                        localExperience.setLe_latitude(jsonObject.getString("le_latitude"));
                        localExperience.setLe_longitude(jsonObject.getString("le_longitude"));
                        localExperience.setLe_website(jsonObject.getString("le_website"));
                        localExperience.setLe_price(jsonObject.getString("le_price"));

                        listLocalExperience.add(localExperience);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listLocalExperience);
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

                Log.d("Response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        LocalExperience localExperience = new LocalExperience();
                        localExperience.setLe_name_eng(jsonObject.getString("le_name_eng"));
                        localExperience.setLe_name_ch(jsonObject.getString("le_name_ch"));
                        localExperience.setImg_1(jsonObject.getString("img1"));
                        localExperience.setImg_2(jsonObject.getString("img2"));
                        localExperience.setImg_3(jsonObject.getString("img3"));
                        localExperience.setImg_4(jsonObject.getString("img4"));
                        localExperience.setImg_5(jsonObject.getString("img5"));
                        localExperience.setLe_type(jsonObject.getString("le_type"));
                        localExperience.setLe_activity(jsonObject.getString("le_activity"));
                        localExperience.setLe_time(jsonObject.getString("le_time"));
                        localExperience.setLe_tel(jsonObject.getString("le_tel"));
                        localExperience.setLe_car_park(jsonObject.getString("le_car_park"));
                        localExperience.setLe_address(jsonObject.getString("le_address"));
                        localExperience.setLe_latitude(jsonObject.getString("le_latitude"));
                        localExperience.setLe_longitude(jsonObject.getString("le_longitude"));
                        localExperience.setLe_website(jsonObject.getString("le_website"));
                        localExperience.setLe_price(jsonObject.getString("le_price"));

                        listLocalExperience.add(localExperience);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listLocalExperience);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<LocalExperience> listLocalExperience) {
        LocalExperienceAdapter myAdapter = new LocalExperienceAdapter(this, listLocalExperience);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);
    }
}
