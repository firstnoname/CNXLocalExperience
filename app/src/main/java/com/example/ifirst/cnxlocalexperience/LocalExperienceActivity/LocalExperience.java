package com.example.ifirst.cnxlocalexperience.LocalExperienceActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ifirst.cnxlocalexperience.R;

public class LocalExperience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_experience);
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
