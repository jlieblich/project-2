package com.jonathanlieblich.somesortofcommerceyousay;

import android.app.Activity;
import android.content.Intent;
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
    private List<Product> mProductList;

    public ProductViewAdapter(List<Product> products) {
        mProductList = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        Product product = mProductList.get(position);

        holder.mName.setText(mProductList.get(position).getName());
        String simplplePrice = mProductList.get(position).getPrice()
                .substring(1, mProductList.get(position).getPrice().indexOf('D'))+"D";
        holder.mPrice.setText(simplplePrice);

        int sourceImageS = product.getName().indexOf(' ');
        int sourceImageC = product.getName().indexOf(',');
        if(sourceImageC < 0) {
            sourceImageC = sourceImageS+1;
        }
        String sourceImage = sourceImageS > sourceImageC ? product.getName()
                .substring(0,sourceImageC)+"_main" : product.getName().substring(0, sourceImageS)+"_main";
        sourceImage = sourceImage.toLowerCase();

        final int thumbnail = holder.mThumbnail.getContext().getResources()
                .getIdentifier(sourceImage, "drawable", holder.mThumbnail.getContext().getPackageName());

        holder.mThumbnail.setImageResource(thumbnail);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.product_thumbnail:
                    case R.id.product_name:
                    case R.id.product_price:
                    case R.id.single_product_item:
                        Intent intent = new Intent(view.getContext(), ItemDetailActivity.class);
                        intent.putExtra("ID", position+1);
                        intent.putExtra("PIC", thumbnail);
                        holder.mProductItem.getContext().startActivity(intent);
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
