//package com.example.cuahangbantraicay.Fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.VolleyError;
//import com.example.cuahangbantraicay.API.ProductApi;
//import com.example.cuahangbantraicay.R;
//import com.example.cuahangbantraicay.Utils.BASE_URL;
//import com.example.cuahangbantraicay.Utils.VolleyCallback;
//import com.example.cuahangbantraicay.activity.Admin;
//import com.example.cuahangbantraicay.activity.ManagerProductDetail;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link managerCreateProduct#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class managerCreateProduct extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public managerCreateProduct() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment managerCreateProduct.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static managerCreateProduct newInstance(String param1, String param2) {
//        managerCreateProduct fragment = new managerCreateProduct();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    private EditText Name, GiaNhap, GiaBan, Content, Discount, SoLuong, SLCon;
//    private TextView btnSave, btnThoat;
//    private ImageView Image;
//    private Spinner Loai;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_manager_create_product, container, false);
//        setConTrol(view);
//        setEvent();
////        CallApi(view);
//
//        return view;
//    }
//
//    private void setConTrol(View view) {
//        Image = view.findViewById(R.id.PD_image_create);
//        Name = view.findViewById(R.id.PD_name_create);
//        GiaNhap = view.findViewById(R.id.PD_price_in_create);
//        GiaBan = view.findViewById(R.id.PD_price_sell_create);
//        Content = view.findViewById(R.id.PD_content_create);
//        Loai = view.findViewById(R.id.PD_category_id_create);
//        Discount = view.findViewById(R.id.PD_discout_create);
//        SoLuong = view.findViewById(R.id.PD_quantity_create);
//        SLCon = view.findViewById(R.id.PD_quantity_sold_create);
//        btnSave = view.findViewById(R.id.save_MPD_create);
//        btnThoat = view.findViewById(R.id.exit_MPD);
//    }
//
//    private void setEvent() {
//        btnThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("hihi");
//            }
//        });
//
//
//    }
//}
////    private void CallApi(View view) {
////        System.out.println("Nhan nut luu");
////        try {
////            ProductApi.createProduct(getContext(), BASE_URL.BASE_URL + "api/admin/create-product", new VolleyCallback() {
////                @Override
////                public void onSuccess(JSONObject result) throws JSONException {
////
////                }
////
////                @Override
////                public void onError(VolleyError errorMessage) {
////
////                }
////            });
////        } catch (JSONException e) {
////            throw new RuntimeException(e);
////        }
////    }
////}