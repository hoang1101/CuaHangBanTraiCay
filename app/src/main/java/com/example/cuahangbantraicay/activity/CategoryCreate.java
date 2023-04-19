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

public class CategoryCreate extends AppCompatActivity {
    managerCategory managerCategory = null;
    private EditText edtName;
    TextView btnSave, btnExit;
    private Category category;

    List<String> ListCategory = new ArrayList<>();
    ProgressBar progressBar;
    static Boolean isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail);
        setControl();
        setEvent();
    }
//    private void setData() {
//        edtName.setText(category.getName());
//    }


    private void setControl() {
        edtName = findViewById(R.id.CTGR_name);
        btnSave = findViewById(R.id.save_CTGR);
        btnExit = findViewById(R.id.exit_CTGR);


    }

    private void SaveCreateCategory() {
        Category category = new Category();
        category.setName(String.valueOf(edtName.getText()));
        try {
            CategoryApi.createCategory(getApplicationContext(), BASE_URL.BASE_URL + "api/admin/create-category", category, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(CategoryCreate.this, "thanh cong", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(CategoryCreate.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
//                        progressDialog.dismiss();
//            progressBar.setVisibility(View.GONE);
//            CustomToast.makeText(ManagerProductDetail.this, "Catch Thêm Mới Sản Phẩm Không Thành Công", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

            throw new RuntimeException(e);

        }
    }

    private void setEvent() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isActive = true;
                Intent intent = new Intent(CategoryCreate.this, Admin.class);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveCreateCategory();
                isActive = true;
                Intent intent = new Intent(CategoryCreate.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}
