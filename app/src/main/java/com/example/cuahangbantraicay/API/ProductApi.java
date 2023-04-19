package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

// Api lay tat ca cac san pham
public class ProductApi {
    //    ====================================== lay tat ca san pham ===============================================
    public static void getProducts(Context context, String url, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Nam : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }
    // =============================== Api tao san pham moi=====================================


    public static void createProduct(Context context, String url, Product product, String base64Img, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();

        System.out.println("api create đc gọi");
        JSONObject postData = new JSONObject();

        postData.put("name", product.getName());
        postData.put("content", product.getContent());
        postData.put("price_in", product.getPrice_in());
        postData.put("price_sell", product.getPrice_sell());
        postData.put("quantity", product.getQuantity());
        postData.put("quantity_sold", product.getQuantity_sold());
        postData.put("discout", product.getDiscount());
        postData.put("category_id", product.getCategory_id());
        postData.put("image", "data:image/jpeg;base64," + base64Img);
//        System.out.println(product.getCategory_id());
//        postData.put("image",product.getImage());
//        System.out.println(base64Img);
//        postData.put("image", "hahaha");
//        postData.put("createdAt", formatter.format(date));


        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody.getJSONObject("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
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
                        callBack.onError(error);
                    }
                });
        requestQueue.add(request);


    }

    //// kdfkdsajfkdahjfhda
//    public static void createProduct(Context context, String url, Product product, VolleyCallback callBack) throws JSONException {
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//
////        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
////        Date date = new Date();
//
//        System.out.println("api create đc gọi");
//        JSONObject postData = new JSONObject();
//
//        postData.put("name", product.getName());
//        postData.put("content", product.getContent());
//        postData.put("price_in", product.getPrice_in());
//        postData.put("price_sell", product.getPrice_sell());
//        postData.put("quantity", product.getQuantity());
//        postData.put("quantity_sold", product.getQuantity_sold());
//        postData.put("discout", product.getDiscount());
//        postData.put("category_id", product.getCategory_id());
//        System.out.println(product.getCategory_id());
////        postData.put("image",product.getImage());
////        System.out.println(product.getImage());
//        postData.put("image", "hahaha");
////        postData.put("createdAt", formatter.format(date));
//
//
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("data", postData);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody.getJSONObject("data"),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Handle response
//                        try {
//                            callBack.onSuccess(response);
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                        System.out.println("Nam ngu : " + response);
//                    }
//                },
//                new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle error
//                        callBack.onError(error);
//                    }
//                });
//        requestQueue.add(request);
//
//
//    }

    // ====================== xoa mot product ===================================
    public static void DeleteProduct(Context context, String url, VolleyCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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


    //    =============================== chinh sau mot san pham ================================================
    public static void EditProduct(Context context, String url, Product product, String Image, VolleyCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JSONObject postData = new JSONObject();



            postData.put("name", product.getName());
            postData.put("content", product.getContent());
            postData.put("price_in", product.getPrice_in());
            postData.put("price_sell", product.getPrice_sell());
            postData.put("quantity", product.getQuantity());
            postData.put("quantity_sold", product.getQuantity_sold());
            postData.put("discout", product.getDiscount());
            postData.put("category_id", product.getCategory_id());
            postData.put("image", "data:image/jpeg;base64," + Image);

//        postData.put("image", "hahaha");
//        postData.put("createdAt", formatter.format(date));

        JSONObject requestBody = new JSONObject();
        requestBody.put("data", postData);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, requestBody.getJSONObject("data"),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                        callback.onError(error);
                    }
                });
        requestQueue.add(request);

    }


    public static void getByName(Context context, String url, VolleyCallback callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                        callback.onError(error);
                    }
                });
        requestQueue.add(request);
    }
}