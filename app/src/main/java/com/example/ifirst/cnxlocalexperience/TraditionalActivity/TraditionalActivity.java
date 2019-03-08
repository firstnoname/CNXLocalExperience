package com.example.ifirst.cnxlocalexperience.TraditionalActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ifirst.cnxlocalexperience.Model.TraditionalAct;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.ArrayList;
import java.util.List;

public class TraditionalActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvJan, cvFeb, cvMarch, cvApril, cvMay, cvJune, cvJuly, cvAugust, cvSep, cvOct, cvNov, cvDec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional);

        bindWidget();
    }

    private void bindWidget() {
        cvJan = findViewById(R.id.cvJan);
        cvFeb = findViewById(R.id.cvFeb);
        cvMarch = findViewById(R.id.cvMarch);
        cvApril = findViewById(R.id.cvApril);
        cvMay = findViewById(R.id.cvMay);
        cvJune = findViewById(R.id.cvJune);
        cvJuly = findViewById(R.id.cvJuly);
        cvAugust = findViewById(R.id.cvAugust);
        cvSep = findViewById(R.id.cvSep);
        cvOct = findViewById(R.id.cvOct);
        cvNov = findViewById(R.id.cvNov);
        cvDec = findViewById(R.id.cvDec);
        cvJan.setOnClickListener(this);
        cvFeb.setOnClickListener(this);
        cvMarch.setOnClickListener(this);
        cvApril.setOnClickListener(this);
        cvMay.setOnClickListener(this);
        cvJune.setOnClickListener(this);
        cvJuly.setOnClickListener(this);
        cvAugust.setOnClickListener(this);
        cvSep.setOnClickListener(this);
        cvOct.setOnClickListener(this);
        cvNov.setOnClickListener(this);
        cvDec.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String where = "";
        switch (view.getId()) {
            case R.id.cvJan:
//                Toast.makeText(this, "January", Toast.LENGTH_SHORT).show();
                where = "January";
                break;
            case R.id.cvFeb:
//                Toast.makeText(this, "February", Toast.LENGTH_SHORT).show();
                where = "February";
                break;
            case R.id.cvMarch:
//                Toast.makeText(this, "March", Toast.LENGTH_SHORT).show();
                where = "March";
                break;
            case R.id.cvApril:
//                Toast.makeText(this, "April", Toast.LENGTH_SHORT).show();
                where = "April";
                break;
            case R.id.cvMay:
//                Toast.makeText(this, "May", Toast.LENGTH_SHORT).show();
                where = "May";
                break;
            case R.id.cvJune:
//                Toast.makeText(this, "June", Toast.LENGTH_SHORT).show();
                where = "June";
                break;
            case R.id.cvJuly:
//                Toast.makeText(this, "July", Toast.LENGTH_SHORT).show();
                where = "July";
                break;
            case R.id.cvAugust:
//                Toast.makeText(this, "August", Toast.LENGTH_SHORT).show();
                where = "August";
                break;
            case R.id.cvSep:
//                Toast.makeText(this, "September", Toast.LENGTH_SHORT).show();
                where = "September";
                break;
            case R.id.cvOct:
//                Toast.makeText(this, "October", Toast.LENGTH_SHORT).show();
                where = "October";
                break;
            case R.id.cvNov:
//                Toast.makeText(this, "November", Toast.LENGTH_SHORT).show();
                where = "November";
                break;
            case R.id.cvDec:
//                Toast.makeText(this, "December", Toast.LENGTH_SHORT).show();
                where = "December";
                break;

        }
        Intent i = new Intent(this, TraditionalActivityList.class);
        i.putExtra("where", where);
        startActivity(i);
    }
}
