package com.example.ifirst.cnxlocalexperience.Promotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsingPromotion extends AppCompatActivity {

    String strPromoCode, strPromoUsername, strPromoOwner;
    Button btnSave;
    EditText edtPromoCode, edtPromoUsername, edtPromoOwner;

    private static final String URL_SAVE_CODE = "http://whiskered-navy.hostingerapp.com/response/save_used_promo.php";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_promotion);

        edtPromoCode = findViewById(R.id.edtPromoCode);
        edtPromoUsername = findViewById(R.id.edtUsername);
        edtPromoOwner = findViewById(R.id.edtPromoOwner);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strPromoCode = edtPromoCode.getText().toString();
                strPromoUsername = edtPromoUsername.getText().toString();
                strPromoOwner = edtPromoOwner.getText().toString();

                StringRequest request = new StringRequest(Request.Method.POST, URL_SAVE_CODE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(UsingPromotion.this, "Save promotion code successful.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(UsingPromotion.this, "Failed" + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UsingPromotion.this, "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("code", strPromoCode);
                        params.put("usr_username", strPromoUsername);
                        params.put("owner", strPromoOwner);
                        
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(request);
            }
        });

    }
}
