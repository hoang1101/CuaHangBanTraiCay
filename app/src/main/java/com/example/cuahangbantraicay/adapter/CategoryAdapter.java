package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.Fragment.managerCategory;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.activity.Admin;
import com.example.cuahangbantraicay.activity.CategoryCreate;
import com.example.cuahangbantraicay.activity.CategoryDetail;
import com.example.cuahangbantraicay.activity.ManagerProductDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolderAdmin> {
    Context mContext;
    public static Boolean isActive = false;
    List<Category> categoryList;

    public CategoryAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_category, parent, false);
        CategoryAdapter.ViewHolderAdmin viewHolderAdmin = new CategoryAdapter.ViewHolderAdmin(view);
        return viewHolderAdmin;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdmin holder, int position) {
        Category category = categoryList.get(position);
        holder.tenLoai.setText(category.getName());


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CategoryDetail.class);
                intent.putExtra("category", category);
                mContext.startActivity(intent);
//                Toast.makeText(mContext, "hihi", Toast.LENGTH_SHORT).show();
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "em yeu anh", Toast.LENGTH_SHORT).show();
                clickDeleteItem(category.getId());
//                notifyDataSetChanged();

            }
        });

    }

    private void clickDeleteItem(int idCategory) {
        System.out.println(idCategory);
        try {
            CategoryApi.CategoryDelete(mContext.getApplicationContext(), BASE_URL.BASE_URL + "delete-category/"+ idCategory, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(mContext, "Xoathanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(mContext, Admin.class);
                    mContext.startActivity(intent);
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(mContext,  "khongthanhcong", Toast.LENGTH_SHORT).show();
                    System.out.println(errorMessage);
                }


            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolderAdmin extends RecyclerView.ViewHolder {
        private ImageView ivEdit, ivDel;
        private TextView tenLoai;


        public ViewHolderAdmin(@NonNull View itemView) {
            super(itemView);
            tenLoai = itemView.findViewById(R.id.tenLoai);
            ivEdit = itemView.findViewById(R.id.edit);
            ivDel = itemView.findViewById(R.id.delete);


        }
    }
}
