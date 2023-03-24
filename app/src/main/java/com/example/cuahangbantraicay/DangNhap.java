package com.example.cuahangbantraicay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.LoginAPI;
import com.example.cuahangbantraicay.Utils.BASE_URL;

public class DangNhap extends AppCompatActivity {
    Button DangNhap;
    TextView QuenMK,DK;
    EditText UserName,Password;
    private  void setControl() {
        DangNhap=findViewById(R.id.DN);
        UserName=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        QuenMK=findViewById(R.id.quenMK);
        DK=findViewById(R.id.DK);
    }
    private void setEvent(){
//        DangNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DangNhap.this, com.example.cuahangbantraicay.DangNhap.class);
//                startActivity(intent);
//            }
//        });
        DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, com.example.cuahangbantraicay.DangKy.class);
                startActivity(intent);
            }
        });
        QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, com.example.cuahangbantraicay.QuenMK.class);
                startActivity(intent);
            }
        });
    }
    public  void login(){

        LoginAPI.getUsers(DangNhap.this, BASE_URL.BASE_URL + "getAllUsers", new LoginAPI.VolleyCallback() {

            @Override
            public void onSuccess(String result) {
                System.out.println("da v o ===========================================================");

            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage+"huasndjsa==============================");


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();

        setContentView(R.layout.dang_nhap);
        setControl();
        setEvent();

    }
}