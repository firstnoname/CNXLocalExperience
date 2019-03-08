package com.example.ifirst.cnxlocalexperience.GuestHouseActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ifirst.cnxlocalexperience.Adapter.ImagePagerAdapter;
import com.example.ifirst.cnxlocalexperience.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GuesthouseDetailActivity extends AppCompatActivity {

    private String[] imageUrls;
    private String dLat, dLng;
    private String sLat, sLng;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesthouse_detail);

        String company_name_eng = getIntent().getExtras().getString("name_eng");
        String img1 = getIntent().getExtras().getString("img1");
        String img2 = getIntent().getExtras().getString("img2");
        String img3 = getIntent().getExtras().getString("img3");
        String img4 = getIntent().getExtras().getString("img4");
        String img5 = getIntent().getExtras().getString("img5");
        String address = getIntent().getExtras().getString("gh_address");
        String overview = getIntent().getExtras().getString("gh_overview");
        String price = getIntent().getExtras().getString("gh_price");
        String neighborhood = getIntent().getExtras().getString("gh_neighborhood");
        String breakfast = getIntent().getExtras().getString("gh_breakfast");
        String airport = getIntent().getExtras().getString("gh_airport");
        String public_transportation = getIntent().getExtras().getString("gh_public_transportation");
        String shopping = getIntent().getExtras().getString("gh_shopping");
        String hospital = getIntent().getExtras().getString("gh_hospital");
        String convenience_store = getIntent().getExtras().getString("gh_convenience_store");
        String cash_withdrawal = getIntent().getExtras().getString("gh_cash_withdrawal");
        String thai_cuisine = getIntent().getExtras().getString("gh_thai_cuisine");
        String asian_cuisine = getIntent().getExtras().getString("gh_asian_cuisine");
        String cafe = getIntent().getStringExtra("gh_cafe");
        String languages = getIntent().getStringExtra("gh_languages");
        String internet_access = getIntent().getStringExtra("gh_internet_access");
        String things_todo = getIntent().getStringExtra("gh_things_todo");
        String dining = getIntent().getStringExtra("gh_dining");
        String services = getIntent().getStringExtra("gh_services");
        String access = getIntent().getStringExtra("gh_access");
        String getting_around = getIntent().getStringExtra("gh_getting_around");
        String most_popular_landmarks = getIntent().getStringExtra("gh_most_popular_landmarks");
        String closest_landmarks = getIntent().getStringExtra("gh_closest_landmarks");
        String children_extra_bed = getIntent().getStringExtra("gh_children_extra_bed");
        String other = getIntent().getStringExtra("gh_other");
        String website = getIntent().getExtras().getString("gh_website");
        dLat = getIntent().getStringExtra("gh_latitude");
        dLng = getIntent().getStringExtra("gh_longitude");

        TextView tvCompanyNameEng = findViewById(R.id.txtCompanyName);
        TextView tvAddress = findViewById(R.id.sAddress);
        TextView tvOverview = findViewById(R.id.txtDescActivity);
        TextView tvPrice = findViewById(R.id.sPrice);
        TextView tvNeighborhood = findViewById(R.id.sNeighborhood);
        TextView tvBreakfast = findViewById(R.id.sBreakfast);
        TextView tvAirport= findViewById(R.id.sAirport);
        TextView tvPublicTransportation = findViewById(R.id.sPublicTransportation);
        TextView tvShopping = findViewById(R.id.sShopping);
        TextView tvHospital = findViewById(R.id.sHospital);
        TextView tvConvenienceStore = findViewById(R.id.sConvenienceStore);
        TextView tvCashWithdrawal = findViewById(R.id.sCashWithdrawal);
        TextView tvThaiCuisine = findViewById(R.id.sThaiCuisine);
        TextView tvAsianCuisine = findViewById(R.id.sAsianCuisine);
        TextView tvCafe = findViewById(R.id.sCafe);
        TextView tvLanguages = findViewById(R.id.sLanguages);
        TextView tvInternetAccess = findViewById(R.id.sInternetAccess);
        TextView tvThingsTodo = findViewById(R.id.sThingsTodo);
        TextView tvDining = findViewById(R.id.sDining);
        TextView tvServices = findViewById(R.id.sServices);
        TextView tvAccess = findViewById(R.id.sAccess);
        TextView tvGettingAround = findViewById(R.id.sGettingAround);
        TextView tvMostPopular = findViewById(R.id.sMostPopular);
        TextView tvClosestLandmarks = findViewById(R.id.sClosestLandmarks);
        TextView tvChildrenExtra = findViewById(R.id.sChildrenExtra);
        TextView tvOther = findViewById(R.id.sOther);
        TextView tvWebsite = findViewById(R.id.sWebsite);

        ViewPager viewPager = findViewById(R.id.imgView);

        tvCompanyNameEng.setText(company_name_eng);
        tvAddress.setText(address);
        tvOverview.setText(overview);
        tvPrice.setText(price);
        tvNeighborhood.setText(neighborhood);
        tvBreakfast.setText(breakfast);
        tvAirport.setText(airport);
        tvPublicTransportation.setText(public_transportation);
        tvShopping.setText(shopping);
        tvHospital.setText(hospital);
        tvConvenienceStore.setText(convenience_store);
        tvCashWithdrawal.setText(cash_withdrawal);
        tvThaiCuisine.setText(thai_cuisine);
        tvAsianCuisine.setText(asian_cuisine);
        tvCafe.setText(cafe);
        tvLanguages.setText(languages);
        tvInternetAccess.setText(internet_access);
        tvThingsTodo.setText(things_todo);
        tvDining.setText(dining);
        tvServices.setText(services);
        tvAccess.setText(access);
        tvGettingAround.setText(getting_around);
        tvMostPopular.setText(most_popular_landmarks);
        tvClosestLandmarks.setText(closest_landmarks);
        tvChildrenExtra.setText(children_extra_bed);
        tvOther.setText(other);
        tvWebsite.setText(website);

        imageUrls = new String[]{
                img1,
                img2,
                img3,
                img4,
                img5
        };

        ImagePagerAdapter adapter = new ImagePagerAdapter(this, imageUrls);
        viewPager.setAdapter(adapter);

        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

        ImageButton imgBtnGotoMap = findViewById(R.id.imgBtnGotoMap);
        imgBtnGotoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(GuesthouseDetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GuesthouseDetailActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(GuesthouseDetailActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            sLat = String.valueOf(location.getLatitude());
                            sLng = String.valueOf(location.getLongitude());
//                            Log.d("Location", sLat + " , " + sLng);
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
