package com.example.ifirst.cnxlocalexperience.LocalExperienceActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.example.ifirst.cnxlocalexperience.R;

import java.util.Locale;

public class LocalExperience extends AppCompatActivity {

    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_local_experience);


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

    public void intentToSubmenus(View view) {
        switch (view.getId()) {
            case R.id.cardViewCity:
//                Toast.makeText(this, "CardView City has been click.", Toast.LENGTH_SHORT).show();
                Intent intentcity = new Intent(this, LocalExperienceCity.class);
                startActivity(intentcity);
                break;
            case R.id.cardViewOldTown:
//                Toast.makeText(this, "CardView Old Town has been click.", Toast.LENGTH_SHORT).show();
                Intent intentOldTown = new Intent(this, LocalExperienceOldTown.class);
                startActivity(intentOldTown);
                break;
            case R.id.cardViewVicinity:
//                Toast.makeText(this, "CardView Vicinity has been click", Toast.LENGTH_SHORT).show();
                Intent intentVicinity = new Intent(this, LocalExperienceVicinity.class);
                startActivity(intentVicinity);
                break;
        }
    }
}
