package com.example.cuahangbantraicay.adapter;

import android.content.Context;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.API.ProductApi;
import com.example.cuahangbantraicay.Modal.Product;

import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.activity.Admin;
import com.example.cuahangbantraicay.activity.DangNhap;
import com.example.cuahangbantraicay.activity.ManagerProductCreate;
import com.example.cuahangbantraicay.activity.ManagerProductDetail;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.ViewHolderAdmin> {
    public  static Boolean isActive = false;

    Context mContext;
    List<Product> productList;

    public AdminProductAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public AdminProductAdapter.ViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_product, parent, false);
        ViewHolderAdmin viewHolderAdmin = new ViewHolderAdmin(view);
        return viewHolderAdmin;
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductAdapter.ViewHolderAdmin holder, int position) {
        Product productTmp = productList.get(position);
        Glide.with(mContext).load(productTmp.getImage()).into(holder.imgUser);
        holder.tenSP.setText(productTmp.getName());
        holder.GiaNhap.setText(String.valueOf(productTmp.getPrice_in()));
        holder.GiaBan.setText(String.valueOf(productTmp.getPrice_sell()));
        holder.ThongTin.setText(productTmp.getContent());
        holder.TrangThai.setText(String.valueOf(productTmp.isStatus()));
        holder.SoLuongTon.setText(String.valueOf(productTmp.getQuantity_sold()));
        holder.SoLuong.setText(String.valueOf(productTmp.getQuantity()));
        holder.Discount.setText(String.valueOf(productTmp.getDiscount()));
        holder.Loai.setText(String.valueOf(productTmp.getCategory_id()));


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ManagerProductDetail.class);
                intent.putExtra("pd", productTmp);
                mContext.startActivity(intent);
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "em yeu anh", Toast.LENGTH_SHORT).show();
                clickDeleteItem(productTmp.getResourceId());


            }
        });

    }

    private void clickDeleteItem(int idProduct) {
        try{
            ProductApi.DeleteProduct(mContext.getApplicationContext(), BASE_URL.BASE_URL + "api/admin/delete-product/"+ idProduct, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(mContext, "Xoathanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(mContext, Admin.class);
                    mContext.startActivity(intent);
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(mContext, "khongthanhcong", Toast.LENGTH_SHORT).show();
                    System.out.println(errorMessage);
                }
            });

            }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderAdmin extends RecyclerView.ViewHolder {
        private ImageView imgUser, ivEdit, ivDel;
        private TextView tenSP, Loai, SoLuong, SoLuongTon, GiaNhap, GiaBan, TrangThai, ThongTin, Discount;


        public ViewHolderAdmin(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgSP);
            tenSP = itemView.findViewById(R.id.tenSP);
            ivEdit = itemView.findViewById(R.id.edit);
            ivDel = itemView.findViewById(R.id.delete);
            Loai = itemView.findViewById(R.id.category);
            SoLuong = itemView.findViewById(R.id.quantity);
            SoLuongTon = itemView.findViewById(R.id.quantity_sold);
            GiaNhap = itemView.findViewById(R.id.price_in);
            GiaBan = itemView.findViewById(R.id.price_sell);
            TrangThai = itemView.findViewById(R.id.status);
            ThongTin = itemView.findViewById(R.id.content);
            Discount = itemView.findViewById(R.id.discout);



        }
    }
}
