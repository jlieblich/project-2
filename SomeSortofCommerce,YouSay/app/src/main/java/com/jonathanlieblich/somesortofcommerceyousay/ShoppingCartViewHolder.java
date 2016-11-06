package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jonlieblich on 11/6/16.
 */

public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
    public ImageView mProductThumbnail;
    public TextView mProductName, mProductPrice;
    public EditText mProductQuantity;
    public Button mRemoveProduct;

    public ShoppingCartViewHolder(View itemView) {
        super(itemView);

        mProductThumbnail = (ImageView)itemView.findViewById(R.id.product_in_cart_thumbnail);
        mProductName = (TextView)itemView.findViewById(R.id.product_in_cart_name);
        mProductPrice = (TextView)itemView.findViewById(R.id.product_in_cart_price);
        mProductQuantity = (EditText)itemView.findViewById(R.id.product_in_cart_quantity);
        mRemoveProduct = (Button)itemView.findViewById(R.id.remove_from_cart);
    }
}
