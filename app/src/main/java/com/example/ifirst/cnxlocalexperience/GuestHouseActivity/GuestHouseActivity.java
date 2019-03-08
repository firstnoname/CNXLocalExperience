package com.example.ifirst.cnxlocalexperience.GuestHouseActivity;

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
import com.example.ifirst.cnxlocalexperience.Adapter.GuesthouseAdapter;
import com.example.ifirst.cnxlocalexperience.Model.Guesthouse;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GuestHouseActivity extends AppCompatActivity {

    private final String URL_GUESTHOUSE = "http://whiskered-navy.hostingerapp.com/response/select_guest_house.php";
    private final String URL_GUESTHOUSE_CH = "http://whiskered-navy.hostingerapp.com/response/select_guest_house_ch.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Guesthouse> listGuesthouse;
    private RecyclerView recyclerGuesthouse;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_guest_house);

        listGuesthouse = new ArrayList<>();
        recyclerGuesthouse = findViewById(R.id.recyclerGuesthouse);



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
        request = new JsonArrayRequest(URL_GUESTHOUSE_CH, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Guesthouse guesthouse = new Guesthouse();
                        guesthouse.setGh_name_eng(jsonObject.getString("gh_name_eng"));
                        guesthouse.setGh_name_ch(jsonObject.getString("gh_name_ch"));
                        guesthouse.setImg_1(jsonObject.getString("img1"));
                        guesthouse.setImg_2(jsonObject.getString("img2"));
                        guesthouse.setImg_3(jsonObject.getString("img3"));
                        guesthouse.setImg_4(jsonObject.getString("img4"));
                        guesthouse.setImg_5(jsonObject.getString("img5"));
                        guesthouse.setGh_address(jsonObject.getString("gh_address"));
                        guesthouse.setGh_overview(jsonObject.getString("gh_overview"));
                        guesthouse.setGh_price(jsonObject.getString("gh_price"));
                        guesthouse.setGh_neighborhood(jsonObject.getString("gh_neighborhood"));
                        guesthouse.setGh_breakfast(jsonObject.getString("gh_breakfast"));
                        guesthouse.setGh_airport(jsonObject.getString("gh_airport"));
                        guesthouse.setGh_public_transportation(jsonObject.getString("gh_public_transportation"));
                        guesthouse.setGh_shopping(jsonObject.getString("gh_shopping"));
                        guesthouse.setGh_hospital(jsonObject.getString("gh_hospital"));
                        guesthouse.setGh_convenience_store(jsonObject.getString("gh_convenience_store"));
                        guesthouse.setGh_cash_withdrawal(jsonObject.getString("gh_cash_withdrawal"));
                        guesthouse.setGh_thai_cuisine(jsonObject.getString("gh_thai_cuisine"));
                        guesthouse.setGh_asian_cuisine(jsonObject.getString("gh_asian_cuisine"));
                        guesthouse.setGh_cafe(jsonObject.getString("gh_cafe"));
                        guesthouse.setGh_languages(jsonObject.getString("gh_languages"));
                        guesthouse.setGh_internet_access(jsonObject.getString("gh_internet_access"));
                        guesthouse.setGh_things_todo(jsonObject.getString("gh_things_todo"));
                        guesthouse.setGh_dining(jsonObject.getString("gh_dining"));
                        guesthouse.setGh_services(jsonObject.getString("gh_services"));
                        guesthouse.setGh_access(jsonObject.getString("gh_access"));
                        guesthouse.setGh_getting_around(jsonObject.getString("gh_getting_around"));
                        guesthouse.setGh_most_popular_landmarks(jsonObject.getString("gh_most_popular_landmarks"));
                        guesthouse.setGh_closest_landmarks(jsonObject.getString("gh_closest_landmarks"));
                        guesthouse.setGh_children_extra_bed(jsonObject.getString("gh_children_extra_bed"));
                        guesthouse.setGh_other(jsonObject.getString("gh_other"));
                        guesthouse.setGh_website(jsonObject.getString("gh_website"));
                        guesthouse.setGh_latitude(jsonObject.getString("gh_latitude"));
                        guesthouse.setGh_longitude(jsonObject.getString("gh_longitude"));

                        listGuesthouse.add(guesthouse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listGuesthouse);
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
        request = new JsonArrayRequest(URL_GUESTHOUSE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("response", String.valueOf(response));

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Guesthouse guesthouse = new Guesthouse();
                        guesthouse.setGh_name_eng(jsonObject.getString("gh_name_eng"));
                        guesthouse.setGh_name_ch(jsonObject.getString("gh_name_ch"));
                        guesthouse.setImg_1(jsonObject.getString("img1"));
                        guesthouse.setImg_2(jsonObject.getString("img2"));
                        guesthouse.setImg_3(jsonObject.getString("img3"));
                        guesthouse.setImg_4(jsonObject.getString("img4"));
                        guesthouse.setImg_5(jsonObject.getString("img5"));
                        guesthouse.setGh_address(jsonObject.getString("gh_address"));
                        guesthouse.setGh_overview(jsonObject.getString("gh_overview"));
                        guesthouse.setGh_price(jsonObject.getString("gh_price"));
                        guesthouse.setGh_neighborhood(jsonObject.getString("gh_neighborhood"));
                        guesthouse.setGh_breakfast(jsonObject.getString("gh_breakfast"));
                        guesthouse.setGh_airport(jsonObject.getString("gh_airport"));
                        guesthouse.setGh_public_transportation(jsonObject.getString("gh_public_transportation"));
                        guesthouse.setGh_shopping(jsonObject.getString("gh_shopping"));
                        guesthouse.setGh_hospital(jsonObject.getString("gh_hospital"));
                        guesthouse.setGh_convenience_store(jsonObject.getString("gh_convenience_store"));
                        guesthouse.setGh_cash_withdrawal(jsonObject.getString("gh_cash_withdrawal"));
                        guesthouse.setGh_thai_cuisine(jsonObject.getString("gh_thai_cuisine"));
                        guesthouse.setGh_asian_cuisine(jsonObject.getString("gh_asian_cuisine"));
                        guesthouse.setGh_cafe(jsonObject.getString("gh_cafe"));
                        guesthouse.setGh_languages(jsonObject.getString("gh_languages"));
                        guesthouse.setGh_internet_access(jsonObject.getString("gh_internet_access"));
                        guesthouse.setGh_things_todo(jsonObject.getString("gh_things_todo"));
                        guesthouse.setGh_dining(jsonObject.getString("gh_dining"));
                        guesthouse.setGh_services(jsonObject.getString("gh_services"));
                        guesthouse.setGh_access(jsonObject.getString("gh_access"));
                        guesthouse.setGh_getting_around(jsonObject.getString("gh_getting_around"));
                        guesthouse.setGh_most_popular_landmarks(jsonObject.getString("gh_most_popular_landmarks"));
                        guesthouse.setGh_closest_landmarks(jsonObject.getString("gh_closest_landmarks"));
                        guesthouse.setGh_children_extra_bed(jsonObject.getString("gh_children_extra_bed"));
                        guesthouse.setGh_other(jsonObject.getString("gh_other"));
                        guesthouse.setGh_website(jsonObject.getString("gh_website"));
                        guesthouse.setGh_latitude(jsonObject.getString("gh_latitude"));
                        guesthouse.setGh_longitude(jsonObject.getString("gh_longitude"));

                        listGuesthouse.add(guesthouse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listGuesthouse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<Guesthouse> listGuesthouse) {
        GuesthouseAdapter adapter = new GuesthouseAdapter(this, listGuesthouse);
        recyclerGuesthouse.setLayoutManager(new LinearLayoutManager(this));
        recyclerGuesthouse.setAdapter(adapter);
    }
}
