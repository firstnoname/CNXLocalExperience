package com.example.ifirst.cnxlocalexperience.FavoriteFoodActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.Adapter.FavoriteFoodAdapter;
import com.example.ifirst.cnxlocalexperience.Model.FavoriteFood;
import com.example.ifirst.cnxlocalexperience.Model.Recreation;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FavoriteFoodActivity extends AppCompatActivity {

    private final String JSON_URL = "http://whiskered-navy.hostingerapp.com/response/select_favorite_food.php";
    private final String JSON_URL_CH = "http://whiskered-navy.hostingerapp.com/response/select_favorite_food_ch.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<FavoriteFood> listFavoriteFood;
    private RecyclerView recyclerView;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_favorite_food);

        listFavoriteFood = new ArrayList<>();
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
                        FavoriteFood favoriteFood = new FavoriteFood();
                        favoriteFood.setFf_name_eng(jsonObject.getString("ff_name_eng"));
                        favoriteFood.setFf_name_ch(jsonObject.getString("ff_name_ch"));
                        favoriteFood.setImg_1(jsonObject.getString("img1"));
                        favoriteFood.setImg_2(jsonObject.getString("img2"));
                        favoriteFood.setImg_3(jsonObject.getString("img3"));
                        favoriteFood.setImg_4(jsonObject.getString("img4"));
                        favoriteFood.setImg_5(jsonObject.getString("img5"));
                        favoriteFood.setFf_type(jsonObject.getString("ff_type"));
                        favoriteFood.setFf_activity(jsonObject.getString("ff_activity"));
                        favoriteFood.setFf_time(jsonObject.getString("ff_time"));
                        favoriteFood.setFf_tel(jsonObject.getString("ff_tel"));
                        favoriteFood.setFf_car_park(jsonObject.getString("ff_car_park"));
                        favoriteFood.setFf_address(jsonObject.getString("ff_address"));
                        favoriteFood.setFf_latitude(jsonObject.getString("ff_latitude"));
                        favoriteFood.setFf_longitude(jsonObject.getString("ff_longitude"));
                        favoriteFood.setFf_website(jsonObject.getString("ff_website"));
                        favoriteFood.setFf_price(jsonObject.getString("ff_price"));

                        listFavoriteFood.add(favoriteFood);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listFavoriteFood);

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
                        FavoriteFood favoriteFood = new FavoriteFood();
                        favoriteFood.setFf_name_eng(jsonObject.getString("ff_name_eng"));
                        favoriteFood.setFf_name_ch(jsonObject.getString("ff_name_ch"));
                        favoriteFood.setImg_1(jsonObject.getString("img1"));
                        favoriteFood.setImg_2(jsonObject.getString("img2"));
                        favoriteFood.setImg_3(jsonObject.getString("img3"));
                        favoriteFood.setImg_4(jsonObject.getString("img4"));
                        favoriteFood.setImg_5(jsonObject.getString("img5"));
                        favoriteFood.setFf_type(jsonObject.getString("ff_type"));
                        favoriteFood.setFf_activity(jsonObject.getString("ff_activity"));
                        favoriteFood.setFf_time(jsonObject.getString("ff_time"));
                        favoriteFood.setFf_tel(jsonObject.getString("ff_tel"));
                        favoriteFood.setFf_car_park(jsonObject.getString("ff_car_park"));
                        favoriteFood.setFf_address(jsonObject.getString("ff_address"));
                        favoriteFood.setFf_latitude(jsonObject.getString("ff_latitude"));
                        favoriteFood.setFf_longitude(jsonObject.getString("ff_longitude"));
                        favoriteFood.setFf_website(jsonObject.getString("ff_website"));
                        favoriteFood.setFf_price(jsonObject.getString("ff_price"));

                        listFavoriteFood.add(favoriteFood);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setUpRecyclerView(listFavoriteFood);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<FavoriteFood> listFavoriteFood) {
        FavoriteFoodAdapter myAdapter = new FavoriteFoodAdapter(this, listFavoriteFood);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
