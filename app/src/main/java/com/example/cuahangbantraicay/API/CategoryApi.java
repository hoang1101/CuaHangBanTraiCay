package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class CategoryApi {
    public static void getCategory(Context context, String url, VolleyCallback callback) throws JarException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
//                        System.out.println("Nam : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callback.onError(error);
                    }
                });
        requestQueue.add(request);
    }

    public static void createCategory(Context context, String url, Category category, VolleyCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JSONObject postData = new JSONObject();

        postData.put("name", category.getName());
        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody.getJSONObject("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Nam ngu : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callback.onError(error);
                    }
                });
        requestQueue.add(request);


    }


    public static void CategoryDelete(Context context, String url, VolleyCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        JSONObject postData = new JSONObject();
//        postData.put("id",idCategory);
//        System.out.println(postData);
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("category", idCategory);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callback.onError(error);
                    }
                });


        requestQueue.add(request);
    }

    public static void CategoryUpdate(Context context, String url, Category category, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();

        JSONObject postData = new JSONObject();
//        postData.put("id", category.getId());
        postData.put("name", category.getName());


//        postData.put("updatedAt", formatter.format(date));

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, requestBody.getJSONObject("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }

                });


        requestQueue.add(request);

    }

}
