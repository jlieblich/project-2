package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import java.util.List;

/**
 * Created by jonlieblich on 11/1/16.
 */

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<Product> mProductList;

    public ProductViewAdapter(List<Product> products) {
        mProductList = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.mName.setText(mProductList.get(position).getName());
        holder.mPrice.setText(Double.toString(mProductList.get(position).getPrice()));

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.product_thumbnail:
                        //View picture or just show the same page as three below
                        break;
                    case R.id.product_name:
                    case R.id.product_price:
                    case R.id.single_product_item:
                        //Open detail fragment
                        break;
                    default:
                        break;
                }
            }
        };

        holder.mThumbnail.setOnClickListener(onClick);
        holder.mName.setOnClickListener(onClick);
        holder.mPrice.setOnClickListener(onClick);
        holder.mProductItem.setOnClickListener(onClick);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
