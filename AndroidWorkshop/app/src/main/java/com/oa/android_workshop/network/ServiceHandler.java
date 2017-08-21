package com.oa.android_workshop.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.oa.android_workshop.models.Company;
import com.overactiveinc.oarestapi.network.RestApiCallback;
import com.overactiveinc.oarestapi.network.RestApiHandler;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;


/**
 * Created by mnunez on 8/16/17.
 */

public class ServiceHandler extends RestApiHandler {


    public ServiceHandler(Context context) {
        super(context);
    }

    public void doGetCompanies(final RestApiCallback<List<Company>> callback) {
        getQueueInstance();
        mRequestQueue.add(this.createRequest("http://swiftclass.mebapps.com:3000/companies",
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        callback.onSuccess(Arrays.asList(gson.fromJson(response.toString(), Company[].class)));
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                }));
    }
}
