package com.example.watermellonorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText address;
    EditText watermellon;
    EditText phoneNumber;
    EditText delivery;
    Button order;
    String a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        address = (EditText) findViewById(R.id.address);
        watermellon = (EditText) findViewById(R.id.watermellon);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        delivery = (EditText) findViewById(R.id.deliveryDate);
        order = (Button) findViewById(R.id.order);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = address.getText().toString();
                b = phoneNumber.getText().toString();
                c = delivery.getText().toString();
                String url = "http://wmapi.com/watermellons/" + watermellon.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MainActivity.this, response.trim(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("address", a);
                        params.put("phone_number", b);
                        params.put("delivery_date", c);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}