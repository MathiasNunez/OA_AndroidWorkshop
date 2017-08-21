package com.overactiveinc.oarestapi.network;

import android.content.Context;
import android.os.Build;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.overactiveinc.oarestapi.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by slaport on 11/22/16.
 */

public class RestApiHandler {

    private static final int DEFAULT_MAX_RETRIES = 0;
    private Context mContext;
    public RequestQueue mRequestQueue;
    private Map<String, String> mHeaders;

    public RestApiHandler() {
        super();
    }

    public RestApiHandler(Context context) {
        super();
        mContext = context;
    }

    /**
     * Add custom header to hashmap.
     *
     * @param key
     * @param value
     * @Precondition: initilize mHeaders class atribute.
     */
    public void putHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    /**
     * Use thismethod to create a request with custom headers
     *
     * @param requestMethod
     * @param url
     * @param body
     * @param responseListener
     * @param respoErrorListener
     * @return
     */
    public JsonObjectRequestAppIdHeader createRequestWithHeader(int requestMethod, String url, JSONObject body, final Response.Listener responseListener, final Response.ErrorListener respoErrorListener) {
        JsonObjectRequestAppIdHeader jsonObjectRequest = new JsonObjectRequestAppIdHeader(requestMethod, url, body, new Response.Listener<JSONObject>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(JSONObject response) {
                responseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onErrorResponse(VolleyError error) {
                respoErrorListener.onErrorResponse(error);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return jsonObjectRequest;
    }

    /**
     * Class needed to override JsonObjectRequest headers as needed
     */
    public class JsonObjectRequestAppIdHeader extends JsonObjectRequest {

        public JsonObjectRequestAppIdHeader(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return mHeaders;
        }
    }

    /**
     * Get Queue Instance
     */
    public void getQueueInstance() {
        if (mRequestQueue == null) {
            mRequestQueue = this.setSecurityProtocol(mContext);
        }
    }

    /**
     * This method check for Android version and enables TLSv1.2 which disabled by default
     * is some versions
     *
     * @param context
     */
    private RequestQueue setSecurityProtocol(Context context) {
        RequestQueue requestQueue = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            HttpStack stack = new HurlStack(null, new RestApiTLSSocketFactory());
            requestQueue = Volley.newRequestQueue(context, stack);
        } else {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    /**
     * This method purpose is to create every request and avoid boilerplate code.
     * WARNING! -> A request created by this method is NOT cancelable.
     *
     * @param requestMethod
     * @param url
     * @param body
     * @param responseListener
     * @param respoErrorListener
     * @return
     */
    public JsonObjectRequest createRequest(int requestMethod, String url, JSONObject body, final Response.Listener responseListener, final Response.ErrorListener respoErrorListener) {
        return this.createRequestWithTag(null, requestMethod, url, body, responseListener, respoErrorListener);
    }

    public JsonArrayRequest createRequest(String url, final Response.Listener<JSONArray> responseListener, final Response.ErrorListener respoErrorListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                responseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                respoErrorListener.onErrorResponse(error);
            }
        });
        return jsonArrayRequest;
    }

    /**
     * This method purpose is to create every request and avoid boilerplate code.
     * Also, by creating the Request with a Tag it makes it cancelable.
     *
     * @param tag
     * @param requestMethod
     * @param url
     * @param body
     * @param responseListener
     * @param respoErrorListener
     * @return
     */
    private JsonObjectRequest createRequestWithTag(String tag, int requestMethod, String url, JSONObject body, final Response.Listener responseListener, final Response.ErrorListener respoErrorListener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url, body, new Response.Listener<JSONObject>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(JSONObject response) {
                responseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onErrorResponse(VolleyError error) {
                respoErrorListener.onErrorResponse(error);
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(45000,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (StringUtils.isNotEmpty(tag)) {
            jsonObjectRequest.setTag(tag);
        }
        return jsonObjectRequest;
    }


}
