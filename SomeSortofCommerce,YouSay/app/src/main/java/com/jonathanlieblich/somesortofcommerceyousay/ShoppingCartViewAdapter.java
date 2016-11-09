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

public class ShoppingCartViewAdapter extends RecyclerView.Adapter<ShoppingCartViewHolder>{
    private List<Product> mProductsInCart;
    OnCartPriceChange mListener;

    public ShoppingCartViewAdapter(List<Product> products, OnCartPriceChange priceChangeListener) {
        mProductsInCart = products;
        mListener = priceChangeListener;
    }

    @Override
    public ShoppingCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.shopping_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingCartViewHolder holder, final int position) {
        final Product product = mProductsInCart.get(holder.getAdapterPosition());

        holder.mProductQuantity.setHint(Integer.toString(product.getQuantity()));
        holder.mProductName.setText(product.getName());
        holder.mProductPrice.setText(product.getPrice());
        holder.mRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberPrice = product.getPrice();
                numberPrice = numberPrice.substring(numberPrice.indexOf('$')+1, numberPrice.indexOf(' '));
                int price = Integer.parseInt(numberPrice);
                mProductsInCart.remove(position);
                ProductStorageHelper.getInstance(view.getContext()).removeFromCart(product.getName());
                mListener.itemsAddedOrRemoved(price*product.getQuantity());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductsInCart.size();
    }
}
