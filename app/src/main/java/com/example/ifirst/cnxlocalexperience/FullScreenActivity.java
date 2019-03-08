package com.example.ifirst.cnxlocalexperience;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        ImageView fullScreenImageView = findViewById(R.id.fullScreenImageView);
        Intent callingActivityIntent = getIntent();
        if (callingActivityIntent != null) {
            String imageUri = callingActivityIntent.getExtras().getString("imageUrls");
            if (imageUri != null & fullScreenImageView != null) {
                Glide.with(this)
                        .load(imageUri)
                        .into(fullScreenImageView);
            }
        }

        fullScreenImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
