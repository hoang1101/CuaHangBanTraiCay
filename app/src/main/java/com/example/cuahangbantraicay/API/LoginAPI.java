package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAPI {


    public static void getUsers(Context context, VolleyCallback callBack, String username, String password) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_URL+ "login";
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {

        };
        requestQueue.add(request);
    }


    //    public static void getUsers(Context context, String url, VolleyCallback callBack, String id_User) throws JSONException {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//
//        JSONObject postData = new JSONObject();
//        postData.put("id", "63af70c03f562b7531d4c5db");
//
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("data", postData);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+id_User, (JSONObject) requestBody.get("data"),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Handle response
//                        callBack.onSuccess(response);
//                        System.out.println( "Nam : " +response);
//                    }
//                },
//                new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                        callBack.onError(error);
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("param1", "num1");
//                params.put("param2", "num2");
//                return params;
//            };
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJUcmFuIE5oYXQgRHV5IiwiZGF0YSI6IjY0MTgxYmFiY2QwMWViNmFjYTA5ODdkYiIsImlhdCI6MTY3OTMyNTkyMDkxMCwiZXhwIjoxNjc5NDEyMzIwOTEwfQ.M8s-rFhZ9Klm5GmuPtog-UIbH0_ADT7rh4vt93ltv98");
//                return headers;
//            }
//        };
//        requestQueue.add(request);
//
//    }

}
