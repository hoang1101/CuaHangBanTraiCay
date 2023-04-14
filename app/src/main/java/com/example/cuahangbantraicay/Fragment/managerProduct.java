package com.example.cuahangbantraicay.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.cuahangbantraicay.API.ProductApi;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.activity.DangNhap;
import com.example.cuahangbantraicay.activity.MainActivity;
import com.example.cuahangbantraicay.activity.ManagerProductCreate;
import com.example.cuahangbantraicay.activity.ManagerProductDetail;
import com.example.cuahangbantraicay.adapter.AdminProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link managerProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class managerProduct extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView btnTao;
    RecyclerView recyclerView;

    List<Product> productList = new ArrayList<>();


    public managerProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment managerProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static managerProduct newInstance(String param1, String param2) {
        managerProduct fragment = new managerProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager_product, container, false);


        try {
            CallApi(view);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return view;
    }

    private void CallApi(View view) throws JSONException {
        ProductApi.getProducts(getContext(), BASE_URL.BASE_URL + "api/admin/all-product", new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {


                JSONArray data = result.getJSONArray("data");
                JSONObject productObj = new JSONObject();
                for (int i = 0; i < data.length(); i++) {
                    productObj = (JSONObject) data.get(i);
                    Product tmpP = new Product();
                    tmpP.setId(productObj.getInt("id"));
                    tmpP.setName(productObj.getString("name"));
                    tmpP.setImage(productObj.getString("image"));
                    tmpP.setPrice_in(Float.parseFloat(productObj.getString("price_in")));
                    tmpP.setPrice_sell(Float.parseFloat(productObj.getString("price_sell")));
                    tmpP.setContent(productObj.getString("content"));
                    tmpP.setCategory_id(productObj.getInt("category_id"));
                    tmpP.setDiscount(productObj.getInt("discout"));
                    tmpP.setQuantity(productObj.getInt("quantity"));
                    tmpP.setQuantity_sold(productObj.getInt("quantity_sold"));
                    tmpP.setStatus(productObj.getBoolean("status"));
                    productList.add(tmpP);
                }


                mapping(view);
                setEvent();
            }

            @Override
            public void onError(VolleyError errorMessage) {

            }
        });
    }


    private void setEvent() {

        AdminProductAdapter adminProductAdapter = new AdminProductAdapter(getContext(), productList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adminProductAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // tao san pham moi
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ManagerProductCreate.class);
                startActivity(intent);
            }
        });


    }

    private void mapping(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recvProduct);
        btnTao = view.findViewById(R.id.taoProduct);
    }
}