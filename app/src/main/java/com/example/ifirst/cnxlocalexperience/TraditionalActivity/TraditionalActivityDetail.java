package com.example.ifirst.cnxlocalexperience.TraditionalActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ifirst.cnxlocalexperience.Adapter.ImagePagerAdapter;
import com.example.ifirst.cnxlocalexperience.R;

public class TraditionalActivityDetail extends AppCompatActivity {

    private String[] imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional_detail);

//        Receive data.
        String actName = getIntent().getExtras().getString("name_eng");
        String img1 = getIntent().getExtras().getString("img1");
        String img2 = getIntent().getExtras().getString("img2");
        String img3 = getIntent().getExtras().getString("img3");
        String img4 = getIntent().getExtras().getString("img4");
        String img5 = getIntent().getExtras().getString("img5");
        String actDesc = getIntent().getExtras().getString("activity");
        String actDuration = getIntent().getExtras().getString("time");

        TextView tvActName = findViewById(R.id.tvActivityName);
        TextView tvActDesc = findViewById(R.id.tvActDesc);
        TextView tvDuration = findViewById(R.id.tvDuration);

        tvActDesc.setText(actDesc);
        tvActName.setText(actName);
        tvDuration.setText(actDuration);
        ViewPager viewPager = findViewById(R.id.imgView);

        imageUrls = new String[]{
                img1,
                img2,
                img3,
                img4,
                img5
        };
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);
    }
}
