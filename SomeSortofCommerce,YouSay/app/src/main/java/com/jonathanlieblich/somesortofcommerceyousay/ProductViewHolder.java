package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jonlieblich on 11/1/16.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView mName, mPrice;
    public ImageView mThumbnail;
    public RelativeLayout mProductItem;

    public ProductViewHolder(View itemView) {
        super(itemView);

        mName = (TextView)itemView.findViewById(R.id.product_name);
        mPrice = (TextView)itemView.findViewById(R.id.product_price);
        mThumbnail = (ImageView)itemView.findViewById(R.id.product_thumbnail);
        mProductItem = (RelativeLayout)itemView.findViewById(R.id.single_product_item);
    }
}
