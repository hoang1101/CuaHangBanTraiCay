package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DetailsProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder> {

    private Context mContext;
    private List<Product> mListFillterProduct;

    public ProductAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Product> list) {
        this.mListFillterProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list_filter_product, parent, false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHoder holder, int position) {
        Product product = mListFillterProduct.get(position);
        if (product == null) return;
        holder.textView.setText(product.getName());
        holder.imgUser.setImageResource(product.getResourceId());
        holder.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsProduct.class);
                intent.putExtra("a",product);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if(mListFillterProduct!=null)return mListFillterProduct.size();
        return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder {
        private ImageView imgUser;
        private TextView textView;

        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgSP);
            textView = itemView.findViewById(R.id.tenSP);
        }
    }
}
