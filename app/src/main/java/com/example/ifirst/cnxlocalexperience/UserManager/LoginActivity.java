package com.example.ifirst.cnxlocalexperience.UserManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView txtRegister;
    EditText edtUsername, edtPassword;
    Button btnLogin;
    static final String URL_LOGIN = "http://whiskered-navy.hostingerapp.com/response/user_login.php";
    String id = "";
    String name = "";
    String email = "";
    String username = "";
    String password = "";

    SharedPreferences shared;

    private static final String user_status = "user_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        shared = getSharedPreferences(user_status, Context.MODE_PRIVATE);

        bindWidget();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

    }

    private void checkLogin() {
        username = this.edtUsername.getText().toString().trim();
        password = this.edtPassword.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if (success.equals("1")) {
//                        Log.d("login", response);

                        //Login success.
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            id = object.getString("id").trim();
                            name = object.getString("name").trim();
                            email = object.getString("email").trim();
                            username = object.getString("username").trim();

//                            Toast.makeText(LoginActivity.this, "Login successful : Hello " + email, Toast.LENGTH_SHORT).show();

                            //Save login status.
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("id", id);
                            editor.putString("username", username);
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.commit();
//                            Log.d("shared_preferences", email);

                            //Intent to main activity.
                            Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intentToMain);
                        }

                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed : " + response, Toast.LENGTH_SHORT).show();
//                        Log.d("login", response);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Login failed " + e.toString(), Toast.LENGTH_SHORT).show();
//                    Log.d("login", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Login error! " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void bindWidget() {
        txtRegister = findViewById(R.id.txtRegister);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
