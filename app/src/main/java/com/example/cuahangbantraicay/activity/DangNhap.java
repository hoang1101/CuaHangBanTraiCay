package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuahangbantraicay.API.LoginAPI;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

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
        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(UserName.getText().length()>0&&Password.getText().length()>0){
//                    try {
//                        login();
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                Intent intent = new Intent(DangNhap.this, Admin.class);
                        startActivity(intent);

            }
        });
        DK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
        QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, com.example.cuahangbantraicay.activity.QuenMK.class);
                startActivity(intent);
            }
        });
    }
    public  void login() throws JSONException {

        LoginAPI.getUsers(DangNhap.this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    if((Boolean) result.get("success")){
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        System.out.println(result.get("mgs").toString()+"dasdsakdhashdfshglksfhgih");
                        Toast.makeText(getApplicationContext(), result.get("mgs").toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onError(JSONObject errorMessage) {

            }
        },UserName.getText().toString(),Password.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.dang_nhap);
        setControl();
        setEvent();

    }
}