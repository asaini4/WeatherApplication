package com.hcl.androidweatherapp.app;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


public class GetRequest extends StringRequest {

    public GetRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }
}
