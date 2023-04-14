package com.example.cuahangbantraicay.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.activity.CategoryCreate;
import com.example.cuahangbantraicay.adapter.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link managerCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class managerCategory extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView btnCreate;
    RecyclerView recyclerViewCategory;

    List<Category> categoryList = new ArrayList<>();

    public managerCategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment managerCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static managerCategory newInstance(String param1, String param2) {
        managerCategory fragment = new managerCategory();
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
        View view = inflater.inflate(R.layout.fragment_manager_category, container, false);
        try {
            CallApi(view);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return view;
    }

    private void CallApi(View view) throws JSONException {
        try {
            CategoryApi.getCategory(getContext(), BASE_URL.BASE_URL + "api/admin/all-category", new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    JSONArray data = result.getJSONArray("data");
                    JSONObject categoryObj = new JSONObject();
                    for (int i = 0; i < data.length(); i++) {
                        categoryObj = (JSONObject) data.get(i);
                        Category tmpC = new Category();
                        tmpC.setId(categoryObj.getInt("id"));
                        tmpC.setName(categoryObj.getString("name"));
                        categoryList.add(tmpC);

                    }
                    mapping(view);
                    setEvent();
                }

                @Override
                public void onError(VolleyError errorMessage) {

                }
            });
        } catch (JarException e) {
            throw new RuntimeException(e);
        }
    }

    private void setEvent() {
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setAdapter(categoryAdapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));


        /// tao loai san pham moi
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryCreate.class);
                startActivity(intent);
            }
        });
    }

    private void mapping(View view) {
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recycviewCategory);
        btnCreate = view.findViewById(R.id.createCategory);
    }
}