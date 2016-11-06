package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        ShoppingCartViewAdapter adapter = new ShoppingCartViewAdapter(
                ProductStorageHelper.getInstance(getApplicationContext()).cartItems());

    }
}
