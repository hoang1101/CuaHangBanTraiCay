package com.example.cuahangbantraicay.API;
import android.content.Context;

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

// Api lay tat ca cac san pham
public class ProductApi {
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
                        System.out.println( "Nam : " +response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                }) ;
        requestQueue.add(request);

    }
    // Api tao san pham

    public static void createProduct(Context context, String url, Product product , String base64Img, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();

        System.out.println("api create đc gọi");
        JSONObject postData = new JSONObject();

        System.out.println("name"+product.getName());
        System.out.println("name1"+product.getDiscount());
        System.out.println("name2"+product.getQuantity());
        System.out.println("name3"+product.getQuantity_sold());
        System.out.println("name4:"+product.getImage());



        postData.put("name", product.getName());
        postData.put("content", product.getContent());
        postData.put("price_in", product.getPrice_in());
        postData.put("price_sell", product.getPrice_sell());
        postData.put("quantity", product.getQuantity());
        postData.put("quantity_sold", product.getQuantity_sold());
        postData.put("discout", product.getDiscount());

//        postData.put("image","data:image/jpeg;base64,"+ base64Img);
        postData.put("image",product.getImage());
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
                        System.out.println( "Nam ngu : " +response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callBack.onError(error);
                    }
                }) ;
        requestQueue.add(request);


    }

}