package com.example.ifirst.cnxlocalexperience.TraditionalActivity;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.Adapter.TraditionalActivityAdapter;
import com.example.ifirst.cnxlocalexperience.Model.TraditionalAct;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TraditionalActivityList extends AppCompatActivity {

    private final String URL_TRADITIONAL_ACTIVITY = "http://whiskered-navy.hostingerapp.com/response/select_traditional_activity.php";
    private final String URL_TRADITIONAL_FEB = "http://whiskered-navy.hostingerapp.com/response/select_traditional_feb.php";
    private final String URL_TRADITIONAL_MARCH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_march.php";
    private final String URL_TRADITIONAL_APRIL = "http://whiskered-navy.hostingerapp.com/response/select_traditional_april.php";
    private final String URL_TRADITIONAL_MAY = "http://whiskered-navy.hostingerapp.com/response/select_traditional_may.php";
    private final String URL_TRADITIONAL_JUNE = "http://whiskered-navy.hostingerapp.com/response/select_traditional_june.php";
    private final String URL_TRADITIONAL_JULY = "http://whiskered-navy.hostingerapp.com/response/select_traditional_july.php";
    private final String URL_TRADITIONAL_AUGUST = "http://whiskered-navy.hostingerapp.com/response/select_traditional_august.php";
    private final String URL_TRADITIONAL_SEP = "http://whiskered-navy.hostingerapp.com/response/select_traditional_september.php";
    private final String URL_TRADITIONAL_OCT = "http://whiskered-navy.hostingerapp.com/response/select_traditional_october.php";
    private final String URL_TRADITIONAL_NOV = "http://whiskered-navy.hostingerapp.com/response/select_traditional_november.php";
    private final String URL_TRADITIONAL_DEC = "http://whiskered-navy.hostingerapp.com/response/select_traditional_december.php";

    private final String URL_TRADITIONAL_ACTIVITY_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_activity_ch.php";
    private final String URL_TRADITIONAL_FEB_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_feb_ch.php";
    private final String URL_TRADITIONAL_MARCH_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_march_ch.php";
    private final String URL_TRADITIONAL_APRIL_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_april_ch.php";
    private final String URL_TRADITIONAL_MAY_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_may_ch.php";
    private final String URL_TRADITIONAL_JUNE_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_june_ch.php";
    private final String URL_TRADITIONAL_JULY_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_july_ch.php";
    private final String URL_TRADITIONAL_AUGUST_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_august_ch.php";
    private final String URL_TRADITIONAL_SEP_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_september_ch.php";
    private final String URL_TRADITIONAL_OCT_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_october_ch.php";
    private final String URL_TRADITIONAL_NOV_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_november_ch.php";
    private final String URL_TRADITIONAL_DEC_CH = "http://whiskered-navy.hostingerapp.com/response/select_traditional_december_ch.php";

    String URL_SELECT_TRADITIONAL = "";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<TraditionalAct> listTraditional;
    private RecyclerView recyclerView;

    String where = "";
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_traditional_list);

        //Get where variable from TraditionalActivity.java;
        where = getIntent().getExtras().getString("where");
//        Toast.makeText(this, "Where == " + where, Toast.LENGTH_SHORT).show();
        if (where.equals("January")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_ACTIVITY_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_ACTIVITY;
            }
        }
        if (where.equals("February")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_FEB_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_FEB;
            }
        }
        if (where.equals("March")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_MARCH_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_MARCH;
            }
        }
        if (where.equals("April")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_APRIL_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_APRIL;
            }
        }
        if (where.equals("May")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_MAY_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_MAY;
            }
        }
        if (where.equals("June")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_JUNE_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_JUNE;
            }
        }
        if (where.equals("July")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_JULY_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_JULY;
            }
        }
        if (where.equals("August")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_AUGUST_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_AUGUST;
            }
        }
        if (where.equals("September")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_SEP_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_SEP;
            }
        }
        if (where.equals("October")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_OCT_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_OCT;
            }
        }
        if (where.equals("November")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_NOV_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_NOV;
            }
        }
        if (where.equals("December")) {
            if (language == "zh") {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_DEC_CH;
            } else {
                URL_SELECT_TRADITIONAL = URL_TRADITIONAL_DEC;
            }
        }

//        Toast.makeText(this, URL_SELECT_TRADITIONAL, Toast.LENGTH_SHORT).show();

        listTraditional = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewTraditional);

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
        request = new JsonArrayRequest(URL_SELECT_TRADITIONAL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("traditional", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        TraditionalAct traditionalAct = new TraditionalAct();
                        traditionalAct.setTa_name_eng(jsonObject.getString("ta_name_eng"));
                        traditionalAct.setTa_name_ch(jsonObject.getString("ta_name_ch"));
                        traditionalAct.setTa_img1(jsonObject.getString("ta_img1"));
                        traditionalAct.setTa_img2(jsonObject.getString("ta_img2"));
                        traditionalAct.setTa_img3(jsonObject.getString("ta_img3"));
                        traditionalAct.setTa_img4(jsonObject.getString("ta_img4"));
                        traditionalAct.setTa_img5(jsonObject.getString("ta_img5"));
                        traditionalAct.setTa_month(jsonObject.getString("ta_month"));
                        traditionalAct.setTa_type(jsonObject.getString("ta_type"));
                        traditionalAct.setTa_activity(jsonObject.getString("ta_activity"));
                        traditionalAct.setTa_time(jsonObject.getString("ta_time"));
                        traditionalAct.setTa_tel(jsonObject.getString("ta_tel"));
                        traditionalAct.setTa_car_park(jsonObject.getString("ta_car_park"));
                        traditionalAct.setTa_address(jsonObject.getString("ta_address"));
                        traditionalAct.setTa_latitude(jsonObject.getString("ta_latitude"));
                        traditionalAct.setTa_longitude(jsonObject.getString("ta_longitude"));
                        traditionalAct.setTa_website(jsonObject.getString("ta_website"));
                        traditionalAct.setTa_price(jsonObject.getString("ta_price"));

                        listTraditional.add(traditionalAct);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpRecyclerViewTraditional(listTraditional);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("traditional", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("where", where);
                Map<String, String> params = new HashMap<>();
                params.put("where", where);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void jsonRequest() {
        /*StringRequest request = new StringRequest(Request.Method.POST, URL_TRADITIONAL_ACTIVITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("TraditoinalActivity");

                    if (success.equals("1")) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            TraditionalAct traditionalAct = new TraditionalAct();
                            traditionalAct.setTa_name_eng(jsonObject.getString("ta_name_eng"));
                            traditionalAct.setTa_name_ch(jsonObject.getString("ta_name_ch"));
                            traditionalAct.setTa_img1(jsonObject.getString("ta_img1"));
                            traditionalAct.setTa_img2(jsonObject.getString("ta_img2"));
                            traditionalAct.setTa_img3(jsonObject.getString("ta_img3"));
                            traditionalAct.setTa_img4(jsonObject.getString("ta_img4"));
                            traditionalAct.setTa_img5(jsonObject.getString("ta_img5"));
                            traditionalAct.setTa_month(jsonObject.getString("ta_month"));
                            traditionalAct.setTa_type(jsonObject.getString("ta_type"));
                            traditionalAct.setTa_activity(jsonObject.getString("ta_activity"));
                            traditionalAct.setTa_time(jsonObject.getString("ta_time"));
                            traditionalAct.setTa_tel(jsonObject.getString("ta_tel"));
                            traditionalAct.setTa_car_park(jsonObject.getString("ta_car_park"));
                            traditionalAct.setTa_address(jsonObject.getString("ta_address"));
                            traditionalAct.setTa_latitude(jsonObject.getString("ta_latitude"));
                            traditionalAct.setTa_longitude(jsonObject.getString("ta_longitude"));
                            traditionalAct.setTa_website(jsonObject.getString("ta_website"));
                            traditionalAct.setTa_price(jsonObject.getString("ta_price"));

                            listTraditional.add(traditionalAct);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setUpRecyclerViewTraditional(listTraditional);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("where", where);
                return params;
            }
        };*/
        request = new JsonArrayRequest(URL_SELECT_TRADITIONAL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
//                Log.d("traditional", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        TraditionalAct traditionalAct = new TraditionalAct();
                        traditionalAct.setTa_name_eng(jsonObject.getString("ta_name_eng"));
                        traditionalAct.setTa_name_ch(jsonObject.getString("ta_name_ch"));
                        traditionalAct.setTa_img1(jsonObject.getString("ta_img1"));
                        traditionalAct.setTa_img2(jsonObject.getString("ta_img2"));
                        traditionalAct.setTa_img3(jsonObject.getString("ta_img3"));
                        traditionalAct.setTa_img4(jsonObject.getString("ta_img4"));
                        traditionalAct.setTa_img5(jsonObject.getString("ta_img5"));
                        traditionalAct.setTa_month(jsonObject.getString("ta_month"));
                        traditionalAct.setTa_type(jsonObject.getString("ta_type"));
                        traditionalAct.setTa_activity(jsonObject.getString("ta_activity"));
                        traditionalAct.setTa_time(jsonObject.getString("ta_time"));
                        traditionalAct.setTa_tel(jsonObject.getString("ta_tel"));
                        traditionalAct.setTa_car_park(jsonObject.getString("ta_car_park"));
                        traditionalAct.setTa_address(jsonObject.getString("ta_address"));
                        traditionalAct.setTa_latitude(jsonObject.getString("ta_latitude"));
                        traditionalAct.setTa_longitude(jsonObject.getString("ta_longitude"));
                        traditionalAct.setTa_website(jsonObject.getString("ta_website"));
                        traditionalAct.setTa_price(jsonObject.getString("ta_price"));

                        listTraditional.add(traditionalAct);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpRecyclerViewTraditional(listTraditional);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("traditional", String.valueOf(error));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("where", where);
                Map<String, String> params = new HashMap<>();
                params.put("where", where);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setUpRecyclerViewTraditional(List<TraditionalAct> listTraditional) {
        TraditionalActivityAdapter traditionalAdapter = new TraditionalActivityAdapter(this, listTraditional);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(traditionalAdapter);
    }

}
