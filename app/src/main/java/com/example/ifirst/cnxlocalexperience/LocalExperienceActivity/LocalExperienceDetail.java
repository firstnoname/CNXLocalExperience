package com.example.ifirst.cnxlocalexperience.LocalExperienceActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ifirst.cnxlocalexperience.Adapter.ImagePagerAdapter;
import com.example.ifirst.cnxlocalexperience.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocalExperienceDetail extends AppCompatActivity {

    private String[] imageUrls;
    private String dLat, dLng;
    private String sLat, sLng;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_experience_detail);

        //Receive data.
        String company_name_eng = getIntent().getExtras().getString("name_eng");
        String img1 = getIntent().getExtras().getString("img1");
        String img2 = getIntent().getExtras().getString("img2");
        String img3 = getIntent().getExtras().getString("img3");
        String img4 = getIntent().getExtras().getString("img4");
        String img5 = getIntent().getExtras().getString("img5");
        String description_activity = getIntent().getExtras().getString("activity");
        String time = getIntent().getExtras().getString("time");
        String price = getIntent().getExtras().getString("price");
        String tel = getIntent().getExtras().getString("tel");
        String car_park = getIntent().getExtras().getString("car_park");
        String address = getIntent().getExtras().getString("address");
        String website = getIntent().getExtras().getString("website");
        dLat = getIntent().getExtras().getString("latitude");
        dLng = getIntent().getExtras().getString("longitude");

        TextView tvCompanyNameEng = findViewById(R.id.txtCompanyName);
        TextView tvDescActivity = findViewById(R.id.txtDescActivity);
        TextView tvPrice = findViewById(R.id.txtPrice);
        TextView tvTel = findViewById(R.id.txtTel);
        TextView tvCarPark = findViewById(R.id.txtCarPark);
        TextView tvAddress = findViewById(R.id.txtAddress);
        TextView tvWebsite = findViewById(R.id.txtWebsite);
        TextView tvTime = findViewById(R.id.txtTime);
        ViewPager viewPager = findViewById(R.id.imgView);

        tvCompanyNameEng.setText(company_name_eng);
        tvDescActivity.setText(description_activity);
        tvPrice.setText(price);
        tvTel.setText(tel);
        tvCarPark.setText(car_park);
        tvAddress.setText(address);
        tvWebsite.setText(website);
        tvTime.setText(time);

        //Glide.with(this).load(img1).into(testImg);
        //Setup image urls to adapter.
        imageUrls = new String[]{
                img1,
                img2,
                img3,
                img4,
                img5
        };
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        //Request permission for Google map.
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        ImageButton imgBtnGotoMap = findViewById(R.id.imgBtnGotoMap);
        imgBtnGotoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(LocalExperienceDetail.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocalExperienceDetail.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(LocalExperienceDetail.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            sLat = String.valueOf(location.getLatitude());
                            sLng = String.valueOf(location.getLongitude());
                            //Log.d("Location", sLat + " , " + sLng);
                            //Call google map api.
                            String uri = "http://maps.google.com/maps?saddr="+ sLat + "," + sLng + "&daddr="+ dLat + "," + dLng;
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}
