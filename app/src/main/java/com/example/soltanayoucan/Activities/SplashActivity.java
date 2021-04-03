package com.example.soltanayoucan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Constants;
import com.example.soltanayoucan.Utils.DataPostModel;
import com.example.soltanayoucan.Utils.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        JsonArrayRequest createJsonRequest = new JsonArrayRequest(Request.Method.GET, Constants.BASE_URL_POSTS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject singlePost = response.getJSONObject(i);

                        JSONObject title = singlePost.getJSONObject(Constants.TITLE);
                        JSONObject content = singlePost.getJSONObject(Constants.CONTENT);

                        DataPostModel dataPostModel = new DataPostModel(
                                i,
                                singlePost.getString(Constants.ID),
                                singlePost.getString(Constants.DATE_GMT),
                                title.getString(Constants.RENDERED),
                                content.getString(Constants.RENDERED));

                        Variables.dataPostModels.add(dataPostModel);

                    } catch (JSONException e) {
                        Toast.makeText(SplashActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                launchActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, getString(R.string.there_is_no_internet), Toast.LENGTH_LONG).show();
                Variables.OffLineMode = true;
                launchActivity();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(createJsonRequest);
    }

    private void launchActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        Animatoo.animateSlideLeft(this);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}