package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.Fragment.managerCategory;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetail extends AppCompatActivity {
    managerCategory managerCategory = null;
    private EditText edtName;
    TextView btnSave, btnThoat;
    private Category category;

    List<String> ListCategory = new ArrayList<>();
    ProgressBar progressBar;
    static Boolean isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail);
        setControl();
        category = (Category) getIntent().getSerializableExtra("category");
        setData();
        setEvent();
    }

    private void setData() {
        edtName.setText(category.getName());
    }


    private void setControl() {
        edtName = findViewById(R.id.CTGR_name);
        btnSave = findViewById(R.id.save_CTGR);
        btnThoat = findViewById(R.id.exit_CTGR);


    }

    private void SaveCategory() {
        category.setId(category.getId());

        category.setName(String.valueOf(edtName.getText()));
        System.out.println(category.getId());

        try {
            CategoryApi.CategoryUpdate(getApplicationContext(), BASE_URL.BASE_URL + "api/admin/edit-category/"+category.getId() ,category, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(CategoryDetail.this, "thanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(CategoryDetail.this, Admin.class);
                    startActivity(intent);
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(CategoryDetail.this, "Thatbai", Toast.LENGTH_SHORT).show();

                }


            });
        } catch (JSONException e) {
            throw new RuntimeException(e);

        }
    }

    private void setEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isActive = true;
                Intent intent = new Intent(CategoryDetail.this, Admin.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveCategory();

            }
        });
    }
}
