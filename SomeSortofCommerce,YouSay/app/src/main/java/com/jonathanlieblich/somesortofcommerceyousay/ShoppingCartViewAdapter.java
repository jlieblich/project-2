package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import java.util.List;

/**
 * Created by jonlieblich on 11/6/16.
 */

public class ShoppingCartViewAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder> {
    List<Product> mProductsInCart;

    public ShoppingCartViewAdapter(List<Product> products) {
        mProductsInCart = products;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.shopping_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, int position) {
        holder.mProductQuantity.setText("GET QUANTITY");
        holder.mProductName.setText("GET NAME");
        holder.mProductPrice.setText("GET PRICE");
        holder.mRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove from cart
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsInCart.size();
    }
}
