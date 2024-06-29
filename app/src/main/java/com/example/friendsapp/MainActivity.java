package com.example.friendsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2,t3,t4;
    AppCompatButton b1;
     String apiUrl="https://friendsapi-re5a.onrender.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        t1=(EditText) findViewById(R.id.name);
        t2=(EditText) findViewById(R.id.fname);
        t3=(EditText) findViewById(R.id.fnname);
        t4=(EditText) findViewById(R.id.desname);
        b1=(AppCompatButton) findViewById(R.id.subbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName=t1.getText().toString();
                String getfName=t2.getText().toString();
                String getfnName=t3.getText().toString();
                String getDesName=t4.getText().toString();
                JSONObject friend= new JSONObject();
                try {
                    friend.put("name",getName);
                    friend.put("friendName",getfName);
                    friend.put("friendNickName",getfnName);
                    friend.put("DescribeYourFriend",getDesName);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //json object creation
                JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(
                        Request.Method.POST, apiUrl, friend,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                            }
                        }

                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}