package com.bottlerocket.androiddevtest.shoprocket.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bottlerocket.androiddevtest.shoprocket.R;
import com.bottlerocket.androiddevtest.shoprocket.framework.AppConstants;
import com.bottlerocket.androiddevtest.shoprocket.framework.BackendRequestQueue;
import com.bottlerocket.androiddevtest.shoprocket.framework.Global;
import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();

    ProgressBar mProgressBar;
    Intent      mIntent;

    Global global = Global.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_store_fetch);
        mProgressBar.setVisibility(View.VISIBLE);

        mIntent = new Intent(this, MainActivity.class);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                AppConstants.BASE_URL + AppConstants.STORES_URL,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        //                            Log.d(TAG, "onResponse: "+ jsonObject.toString(4));


                        parseStoreDetails(jsonObject);

                        mProgressBar.setVisibility(View.GONE);

                        startActivity(mIntent);
                        finish();

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        BackendRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);


    }

    //==============================================================================================

    /**
     * @param jsonObject
     */
    private void parseStoreDetails(JSONObject jsonObject) {

        try {
            JSONArray jsonArrayStores = jsonObject.getJSONArray(AppConstants.JSON_KEY_STORES);


            for (int i = 0; i < jsonArrayStores.length(); i++) {

                global.getStoreList().add(new Store(jsonArrayStores.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
