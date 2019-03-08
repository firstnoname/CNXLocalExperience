package com.example.ifirst.cnxlocalexperience.UserManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifirst.cnxlocalexperience.MainActivity;
import com.example.ifirst.cnxlocalexperience.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=zkiGwNiSKLI

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtName, edtEmail, edtPhone;
    private Button btnRegister;
    private ProgressBar loading;
    private static final String URL_REGISTER = "http://whiskered-navy.hostingerapp.com/response/user_register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void bindWidget() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnRegister = findViewById(R.id.btnRegister);
        loading = findViewById(R.id.loading);
    }

    private void register() {
        loading.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.VISIBLE);

        final String username = this.edtUsername.getText().toString().trim();
        final String password = this.edtPassword.getText().toString().trim();
        final String name = this.edtName.getText().toString().trim();
        final String email = this.edtEmail.getText().toString().trim();
        final String phone = this.edtPhone.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Log.d("register", response);
                        Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btnRegister.setVisibility(View.VISIBLE);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Register fail! "+ e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("register", e.toString());
                    loading.setVisibility(View.GONE);
                    btnRegister.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("register", String.valueOf(error));
                Toast.makeText(RegisterActivity.this, "Register Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btnRegister.setVisibility(View.VISIBLE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("username", username);
                params.put("password", password);
                params.put("email", email);
                params.put("phone", phone);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
