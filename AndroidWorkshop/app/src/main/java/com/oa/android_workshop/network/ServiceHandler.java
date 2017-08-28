package com.oa.android_workshop.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.oa.android_workshop.models.Company;
import com.overactiveinc.oarestapi.network.RestApiCallback;
import com.overactiveinc.oarestapi.network.RestApiHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void doPostLogin(String username, String password, final RestApiCallback<String> callback) {
        getQueueInstance();
        JSONObject body = new JSONObject();
        try {
            body.put("username", username);
            body.put("password", password);
            mRequestQueue.add(this.createRequest(Request.Method.POST, "http://swiftclass.mebapps.com:3000/login",
                    body, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int code = response.getInt("code");
                                if(code == 1){
                                    callback.onSuccess(null);
                                }else{
                                    callback.onError(response.getString("msg"));
                                }

                            }catch (Exception e){
                                callback.onError("An error occurred - Invalid credentials");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            callback.onError("An error occurred - Invalid credentials");
                        }
                    }));

        } catch (Exception e) {
            e.printStackTrace();
            callback.onError("An error occurred");
        }

    }
}
