package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Product> mProductList;
    private ProductViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mProductList = ProductStorageHelper.getInstance(this).getProductList();
        mRecyclerView = (RecyclerView)findViewById(R.id.product_recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ProductViewAdapter(mProductList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
