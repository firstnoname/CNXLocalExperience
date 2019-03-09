package com.example.ifirst.cnxlocalexperience;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.Adapter.DashboardRecyclerAdapter;
import com.example.ifirst.cnxlocalexperience.Adapter.DashboardSlideRecyclerAdapter;
import com.example.ifirst.cnxlocalexperience.Adapter.ImagePagerAdapter;
import com.example.ifirst.cnxlocalexperience.Adapter.PromotionRecyclerAdapter;
import com.example.ifirst.cnxlocalexperience.FavoriteFoodActivity.FavoriteFoodActivity;
import com.example.ifirst.cnxlocalexperience.GuestHouseActivity.GuestHouseActivity;
import com.example.ifirst.cnxlocalexperience.LocalExperienceActivity.LocalExperience;
import com.example.ifirst.cnxlocalexperience.LocalSouvenirActivity.LocalSouvenirActivity;
import com.example.ifirst.cnxlocalexperience.Model.NewPromotion;
import com.example.ifirst.cnxlocalexperience.Model.Recreation;
import com.example.ifirst.cnxlocalexperience.Promotion.UsingPromotion;
import com.example.ifirst.cnxlocalexperience.RecreationActivity.RecreationActivity;
import com.example.ifirst.cnxlocalexperience.TraditionalActivity.TraditionalActivity;
import com.example.ifirst.cnxlocalexperience.UserManager.LoginActivity;
import com.google.android.gms.analytics.ecommerce.Promotion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String[] onGoingUrls;
    private static final String TAG = "MainActivity";

    private ArrayList<String> mTxtSlide = new ArrayList<>();
    private ArrayList<String> mImgSlideUrls = new ArrayList<>();

    private final String JSON_URL = "http://whiskered-navy.hostingerapp.com/response/select_local_experience_oldtown.php";
    private final String JSON_URL_CH = "http://whiskered-navy.hostingerapp.com/response/select_local_experience_oldtown_ch.php";
    private final String JSON_URL_SELECT_PROMOTION = "http://whiskered-navy.hostingerapp.com/response/select_promotion.php";
    private JsonArrayRequest request, requestPromo;
    private RequestQueue requestQueue, requestQueuePromo;
    private List<com.example.ifirst.cnxlocalexperience.Model.LocalExperience> listTopSuggest;
    private List<NewPromotion> listPromotion;

    SharedPreferences shared;
    private static final String user_status = "user_info";

    TextView tvUserInfo;
    TextView tvLogout;

    TextView tvOwner;

    Context context;

    String language;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //        Set language.
        loadLocale();

        setContentView(R.layout.activity_main);

//        my_child_toolbar is defined in the layout file
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
//        Enable the up button
        ab.setDisplayHomeAsUpEnabled(false);


        //Set OnGoing adapter.
        /*onGoingUrls = new String[] {
                "https://thaizer.smugmug.com/Thailand/Chiang-Mai/i-J5Q2qpj/0/L/SilverTemple-6-L.jpg",
                "http://www.chiangmailocator.com/design/photosbiz/chiang-mai-old-medicine-hospital-735.jpg",
                "https://scontent.fbkk5-5.fna.fbcdn.net/v/t1.0-9/47354927_1985067381793167_6440814634474668032_n.jpg?_nc_cat=104&_nc_ht=scontent.fbkk5-5.fna&oh=7db83e1c5d59e337682accbc266adca8&oe=5C930024",
                "http://3.bp.blogspot.com/-_9cg0BCi7iY/VIsTU96El-I/AAAAAAAAAIA/mRNRmyvx41Y/s1600/DSCN7195.jpg",
                "https://scontent.xx.fbcdn.net/v/t1.0-0/p480x480/38507177_1232890476911463_8347275369342042112_n.jpg?_nc_cat=101&_nc_ht=scontent.xx&oh=131366cd509015b4742c1732b25eabbe&oe=5C987183",
                "https://scontent.xx.fbcdn.net/v/t1.0-0/p480x480/38507177_1232890476911463_8347275369342042112_n.jpg?_nc_cat=101&_nc_ht=scontent.xx&oh=131366cd509015b4742c1732b25eabbe&oe=5C987183",
                "https://media-cdn.tripadvisor.com/media/photo-s/02/40/ba/36/warorot-market.jpg",
                "https://www.justgola.com/media/a/00/03/15958_og_1.jpeg"
        };*/

//        getImages();

        //Check user has been login.
        checkUserLogin();

        listTopSuggest = new ArrayList<>();
        listPromotion = new ArrayList<>();
//        jsonRequest();
//        jsonRequestCH();
        jsonPromoRequest();

        //language = "zh";
        if (language == "zh") {
            jsonRequestCH();
        } else {
            jsonRequest();
        } 

        tvLogout = findViewById(R.id.txtLogout);
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
                tvUserInfo.setText("Please Login or Register!");
            }
        });

        tvOwner = findViewById(R.id.txtOwner);
        tvOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UsingPromotion.class);
                startActivity(i);
            }
        });


    }

    private void checkUserLogin() {
        shared = getSharedPreferences(user_status, Context.MODE_PRIVATE);
        tvUserInfo = findViewById(R.id.txtUserInfo);
        final String userInfo = shared.getString("name", null) + " / " + shared.getString("email", null);
        if (shared.getString("name", null) != null) {
            tvUserInfo.setText(userInfo);
        }
//        Log.d("getSharedPref", shared.getString("email", "not found"));
    }

    private void jsonRequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        com.example.ifirst.cnxlocalexperience.Model.LocalExperience localExperience = new com.example.ifirst.cnxlocalexperience.Model.LocalExperience();
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

                        listTopSuggest.add(localExperience);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpRecyclerView(listTopSuggest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    //    Fetch china language data from server.
    private void jsonRequestCH() {
        request = new JsonArrayRequest(JSON_URL_CH, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObj = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObj = response.getJSONObject(i);
                        com.example.ifirst.cnxlocalexperience.Model.LocalExperience localExperience = new com.example.ifirst.cnxlocalexperience.Model.LocalExperience();
                        localExperience.setLe_name_eng(jsonObj.getString("le_name_ch"));
                        localExperience.setImg_1(jsonObj.getString("img1"));
                        localExperience.setImg_2(jsonObj.getString("img2"));
                        localExperience.setImg_3(jsonObj.getString("img3"));
                        localExperience.setImg_4(jsonObj.getString("img4"));
                        localExperience.setImg_5(jsonObj.getString("img5"));
                        localExperience.setLe_type(jsonObj.getString("le_type_ch"));
                        localExperience.setLe_activity(jsonObj.getString("le_activity_ch"));
                        localExperience.setLe_time(jsonObj.getString("le_time_ch"));
                        localExperience.setLe_tel(jsonObj.getString("le_tel"));
                        localExperience.setLe_car_park(jsonObj.getString("le_car_park_ch"));
                        localExperience.setLe_address(jsonObj.getString("le_address_ch"));
                        localExperience.setLe_latitude(jsonObj.getString("le_latitude"));
                        localExperience.setLe_longitude(jsonObj.getString("le_longitude"));
                        localExperience.setLe_website(jsonObj.getString("le_website"));
                        localExperience.setLe_price(jsonObj.getString("le_price_ch"));

                        listTopSuggest.add(localExperience);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpRecyclerView(listTopSuggest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void jsonPromoRequest() {
        requestPromo = new JsonArrayRequest(JSON_URL_SELECT_PROMOTION, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObjPromo = null;
//                Log.d("promo", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObjPromo = response.getJSONObject(i);
                        NewPromotion newPromotion = new NewPromotion();
                        newPromotion.setPromoCode(jsonObjPromo.getString("promo_code"));
                        newPromotion.setPromoDesc(jsonObjPromo.getString("promo_desc"));
                        newPromotion.setPromoExpireDate(jsonObjPromo.getString("promo_expire_date"));
                        newPromotion.setPromoType(jsonObjPromo.getString("promo_type"));
                        newPromotion.setPromoImgPercent(jsonObjPromo.getString("promo_img"));

                        listPromotion.add(newPromotion);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setUpPromotion(listPromotion);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueuePromo = Volley.newRequestQueue(this);
        requestQueuePromo.add(requestPromo);
    }

    private void setUpRecyclerView(List<com.example.ifirst.cnxlocalexperience.Model.LocalExperience> listTopSuggest) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDashboardSlide);
        recyclerView.setLayoutManager(layoutManager);
        DashboardSlideRecyclerAdapter adapter = new DashboardSlideRecyclerAdapter(this, listTopSuggest);
        recyclerView.setAdapter(adapter);
    }

    private void setUpPromotion(List<NewPromotion> listPromotion) {
        LinearLayoutManager promoLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView promoRecyclerView = findViewById(R.id.recyclerViewDashboardSlidePromo);
        promoRecyclerView.setLayoutManager(promoLayoutManager);
        PromotionRecyclerAdapter promoAdapter = new PromotionRecyclerAdapter(this, listPromotion);
        promoRecyclerView.setAdapter(promoAdapter);

    }

    public void intentTraditionalAcitivity(View view) {
        Intent intentTraditionalActivity = new Intent(this, TraditionalActivity.class);
        startActivity(intentTraditionalActivity);
    }

    public void intentLocalExperience(View view) {
        Intent intentLocalExperience = new Intent(this, com.example.ifirst.cnxlocalexperience.LocalExperienceActivity.LocalExperience.class);
        startActivity(intentLocalExperience);
    }

    public void intentFavoriteFood(View view) {
        Intent intentFavoriteFood = new Intent(this, FavoriteFoodActivity.class);
        startActivity(intentFavoriteFood);
    }

    public void intentRecreation(View view) {
        Intent intentRecreation = new Intent(this, RecreationActivity.class);
        startActivity(intentRecreation);
    }

    public void intentLocalSouvenir(View view) {
        Intent intentLocalSouvenir = new Intent(this, LocalSouvenirActivity.class);
        startActivity(intentLocalSouvenir);
    }

    public void intentGuesthouse(View view) {
        Intent intentGuesthouse = new Intent(this, GuestHouseActivity.class);
        startActivity(intentGuesthouse);
    }
    public void intentLogin(View view) {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
    }

    private void getImages(){
        mImgSlideUrls.add("http://www.chiangmailocator.com/design/photosbiz/chiang-mai-old-medicine-hospital-735.jpg");
        mTxtSlide.add("Test1");

        mImgSlideUrls.add("https://thaizer.smugmug.com/Thailand/Chiang-Mai/i-J5Q2qpj/0/L/SilverTemple-6-L.jpg");
        mTxtSlide.add("Test");

        mImgSlideUrls.add("https://scontent.fbkk5-5.fna.fbcdn.net/v/t1.0-9/47354927_1985067381793167_6440814634474668032_n.jpg?_nc_cat=104&_nc_ht=scontent.fbkk5-5.fna&oh=7db83e1c5d59e337682accbc266adca8&oe=5C930024");
        mTxtSlide.add("Test2");

        mImgSlideUrls.add("http://3.bp.blogspot.com/-_9cg0BCi7iY/VIsTU96El-I/AAAAAAAAAIA/mRNRmyvx41Y/s1600/DSCN7195.jpg");
        mTxtSlide.add("Test3");

        mImgSlideUrls.add("https://scontent.xx.fbcdn.net/v/t1.0-0/p480x480/38507177_1232890476911463_8347275369342042112_n.jpg?_nc_cat=101&_nc_ht=scontent.xx&oh=131366cd509015b4742c1732b25eabbe&oe=5C987183");
        mTxtSlide.add("Test4");

        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_default:
//                Toast.makeText(this, "Default", Toast.LENGTH_SHORT).show();
//                setLocale("en");
                changeLanguage("en");
                recreate();
                return true;

            case R.id.actoin_ch:
//                Toast.makeText(this, "Chinese", Toast.LENGTH_SHORT).show();
//                setLocale("zh");
                changeLanguage("zh");
                recreate();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
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


    private void testSetLocaleUpdate(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration conf = new Configuration();
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf,getBaseContext().getResources().getDisplayMetrics());
    }

    private void setLocaleUpdate(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        language = prefs.getString("My_lang", "");
//        Log.d("language", language);
//        setLocale(language);
        changeLanguage(language);
    }

    private void initRecyclerView(){
//        Log.d(TAG, "initRecyclerView: init recyclerview");

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDashboardSlide);
        recyclerView.setLayoutManager(layoutManager);
        DashboardSlideRecyclerAdapter adapter = new DashboardSlideRecyclerAdapter(this, mTxtSlide, mImgSlideUrls);
        recyclerView.setAdapter(adapter);*/

    }

}
