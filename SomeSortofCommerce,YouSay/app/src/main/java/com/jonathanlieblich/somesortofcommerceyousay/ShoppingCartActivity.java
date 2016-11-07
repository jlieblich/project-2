package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        RecyclerView cartRecycler = (RecyclerView)findViewById(R.id.cart_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        cartRecycler.setLayoutManager(layoutManager);

        ShoppingCartViewAdapter adapter = new ShoppingCartViewAdapter(
                ProductStorageHelper.getInstance(getApplicationContext()).cartItems());
        cartRecycler.setAdapter(adapter);

        FloatingActionButton checkout = (FloatingActionButton)findViewById(R.id.checkout_fab);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemsPurchased = ProductStorageHelper.
                        getInstance(getApplicationContext()).checkoutCart();
                if(itemsPurchased > 0) {
                    Toast.makeText(ShoppingCartActivity.this,
                            itemsPurchased+" items shipped!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShoppingCartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
