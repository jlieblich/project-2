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

        holder.mProductQuantity.setText(Integer.toString(product.getQuantity()));
        holder.mProductName.setText(product.getName());
        String simplePriceText = product.getPrice().substring(1, product.getPrice().indexOf('D'))+"D";
        holder.mProductPrice.setText(simplePriceText);
        holder.mRemoveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberPrice = product.getPrice();
                numberPrice = numberPrice.substring(numberPrice.indexOf('$')+1, numberPrice.indexOf(' '));
                int price = Integer.parseInt(numberPrice);
                mProductsInCart.remove(position);
                ProductStorageHelper.getInstance(view.getContext()).removeFromCart(position+1);
                mListener.itemsAddedOrRemoved(price*product.getQuantity());
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsInCart.size();
    }
}
