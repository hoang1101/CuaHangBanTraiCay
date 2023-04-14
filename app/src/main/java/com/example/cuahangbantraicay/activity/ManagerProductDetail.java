package com.example.cuahangbantraicay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;

import java.util.ArrayList;
import java.util.List;

public class ManagerProductDetail extends AppCompatActivity {

    managerProduct managerproduct = null;
    private EditText edtName, edtGiaNhap, edtGiaBan, edtContent, edtDiscount, edtSoLuong, edtSLCon;
    private Spinner edtLoai;
    private ImageView edtImage;

    TextView btnSave, btnThoat;

    private Product product;
    ArrayAdapter arrayCatergory;
    int categoryCurrent = 0;

    List<String> ListCategory = new ArrayList<>();
    ProgressBar progressBar;
    static Boolean  isActive = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        setControl();
        product = (Product) getIntent().getSerializableExtra("pd");
        System.out.println("hehehhe:"+product.getName());
        setData();
        setEvent();


    }

    private void setData() {
        Glide.with(this).load(product.getImage()).into(edtImage);
        edtName.setText(product.getName());
        edtGiaNhap.setText(String.valueOf(product.getPrice_in()));
        edtGiaBan.setText(String.valueOf(product.getPrice_sell()));
        edtContent.setText(product.getContent());
//        edtLoai. = findViewById(R.id.PD_category_id);
        edtDiscount.setText(String.valueOf(product.getDiscount()));
        edtSoLuong.setText(String.valueOf(product.getQuantity()));
        edtSLCon.setText(String.valueOf(product.getQuantity()));
    }


    private void setControl() {
        edtImage = findViewById(R.id.PD_image);
        edtName = findViewById(R.id.PD_name);
        edtGiaNhap = findViewById(R.id.PD_price_in);
        edtGiaBan = findViewById(R.id.PD_price_sell);
        edtContent = findViewById(R.id.PD_content);
        edtLoai = findViewById(R.id.PD_category_id);
        edtDiscount = findViewById(R.id.PD_discout);
        edtSoLuong = findViewById(R.id.PD_quantity);
        edtSLCon = findViewById(R.id.PD_quantity_sold);
        btnSave = findViewById(R.id.save_MPD);
        btnThoat = findViewById(R.id.exit_MPD);


    }

    private void setEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isActive= true;
                Intent intent = new Intent(ManagerProductDetail.this, Admin.class);
                startActivity(intent);
            }
        });
    }
}
