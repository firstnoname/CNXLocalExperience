package com.example.ifirst.cnxlocalexperience.LocalSouvenirActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.Adapter.LocalSouvenirAdapter;
import com.example.ifirst.cnxlocalexperience.Model.LocalSouvenir;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalSouvenirActivity extends AppCompatActivity {

    private final String JSON_URL = "http://whiskered-navy.hostingerapp.com/response/select_local_souvenir.php";
    private final String JSON_URL_CH = "http://whiskered-navy.hostingerapp.com/response/select_local_souvenir_ch.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<LocalSouvenir> listLocalSouvenir;
    private RecyclerView recyclerView;

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_local_souvenir);

        listLocalSouvenir = new ArrayList<>();
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
//        setLocale(language);
        changeLanguage(language);
    }

    public void changeLanguage(String lang) {
//        Log.d("ChangeLang", lang);
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();

        updateBaseContextLocale(this, lang);
    }

    protected Context updateBaseContextLocale(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration conf = context.getResources().getConfiguration();
        conf.setLocale(locale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        configuration.locale = locale;
        res.updateConfiguration(configuration, dm);

        return context.createConfigurationContext(conf);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    private void jsonRequestCH() {
        request = new JsonArrayRequest(JSON_URL_CH, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("response souvenir", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        LocalSouvenir localSouvenir = new LocalSouvenir();
                        localSouvenir.setLs_name_eng(jsonObject.getString("ls_name_eng"));
                        localSouvenir.setLs_name_ch(jsonObject.getString("ls_name_ch"));
                        localSouvenir.setImg_1(jsonObject.getString("img1"));
                        localSouvenir.setImg_2(jsonObject.getString("img2"));
                        localSouvenir.setImg_3(jsonObject.getString("img3"));
                        localSouvenir.setImg_4(jsonObject.getString("img4"));
                        localSouvenir.setImg_5(jsonObject.getString("img5"));
                        localSouvenir.setLs_type(jsonObject.getString("ls_type"));
                        localSouvenir.setLs_activity(jsonObject.getString("ls_activity"));
                        localSouvenir.setLs_time(jsonObject.getString("ls_time"));
                        localSouvenir.setLs_tel(jsonObject.getString("ls_tel"));
                        localSouvenir.setLs_car_park(jsonObject.getString("ls_car_park"));
                        localSouvenir.setLs_address(jsonObject.getString("ls_address"));
                        localSouvenir.setLs_latitude(jsonObject.getString("ls_latitude"));
                        localSouvenir.setLs_longitude(jsonObject.getString("ls_longitude"));
                        localSouvenir.setLs_website(jsonObject.getString("ls_website"));
                        localSouvenir.setLs_price(jsonObject.getString("ls_price"));

                        listLocalSouvenir.add(localSouvenir);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                setUpRecyclerView(listLocalSouvenir);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", String.valueOf(error));
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
//                Log.d("response souvenir", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        LocalSouvenir localSouvenir = new LocalSouvenir();
                        localSouvenir.setLs_name_eng(jsonObject.getString("ls_name_eng"));
                        localSouvenir.setLs_name_ch(jsonObject.getString("ls_name_ch"));
                        localSouvenir.setImg_1(jsonObject.getString("img1"));
                        localSouvenir.setImg_2(jsonObject.getString("img2"));
                        localSouvenir.setImg_3(jsonObject.getString("img3"));
                        localSouvenir.setImg_4(jsonObject.getString("img4"));
                        localSouvenir.setImg_5(jsonObject.getString("img5"));
                        localSouvenir.setLs_type(jsonObject.getString("ls_type"));
                        localSouvenir.setLs_activity(jsonObject.getString("ls_activity"));
                        localSouvenir.setLs_time(jsonObject.getString("ls_time"));
                        localSouvenir.setLs_tel(jsonObject.getString("ls_tel"));
                        localSouvenir.setLs_car_park(jsonObject.getString("ls_car_park"));
                        localSouvenir.setLs_address(jsonObject.getString("ls_address"));
                        localSouvenir.setLs_latitude(jsonObject.getString("ls_latitude"));
                        localSouvenir.setLs_longitude(jsonObject.getString("ls_longitude"));
                        localSouvenir.setLs_website(jsonObject.getString("ls_website"));
                        localSouvenir.setLs_price(jsonObject.getString("ls_price"));

                        listLocalSouvenir.add(localSouvenir);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                setUpRecyclerView(listLocalSouvenir);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", String.valueOf(error));
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerView(List<LocalSouvenir> listLocalSouvenir) {
        LocalSouvenirAdapter myAdapter = new LocalSouvenirAdapter(this, listLocalSouvenir);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
