package com.example.ifirst.cnxlocalexperience.Promotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ifirst.cnxlocalexperience.R;

public class PromotionDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_detail);

        String promo_info =getIntent().getExtras().getString("promo_info");
        String promo_img = getIntent().getExtras().getString("promo_img");
        String promo_type = getIntent().getExtras().getString("promo_type");
        String promo_code = getIntent().getExtras().getString("promo_code");
        String promo_expire = getIntent().getExtras().getString("promo_expire");

        TextView txtInfoDetail = findViewById(R.id.txtInfoDetail);
        ImageView imgDetail = findViewById(R.id.imgPromoDetail);
        TextView tvPromoCode = findViewById(R.id.tvPromoCode);
        TextView tvExpireDate = findViewById(R.id.tvExpireDate);

        txtInfoDetail.setText(promo_info);
        tvPromoCode.setText(promo_code);
        tvExpireDate.setText("Expire in : " + promo_expire);
        Glide.with(this).load(promo_img).into(imgDetail);

    }
}
