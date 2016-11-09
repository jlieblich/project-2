package com.jonathanlieblich.somesortofcommerceyousay;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import org.w3c.dom.Text;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements OnCartPriceChange{
    private List<Product> itemsInCart;
    public TextView totalPrice;
    public int mPrice = 0;
    ShoppingCartViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        if(ProductStorageHelper.getInstance(getApplicationContext()).cartItems().size() == 0) {
            EmptyCartAlert alert = new EmptyCartAlert();
            alert.show(getSupportFragmentManager(), null);
        }

        totalPrice = (TextView)findViewById(R.id.product_total_price);

        itemsInCart = ProductStorageHelper.getInstance(getApplicationContext()).cartItems();

        RecyclerView cartRecycler = (RecyclerView)findViewById(R.id.cart_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        adapter = new ShoppingCartViewAdapter(itemsInCart, this);

        cartRecycler.setLayoutManager(layoutManager);
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
                    itemsInCart.clear();
                    itemsAddedOrRemoved(mPrice);
                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(ShoppingCartActivity.this, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Add price of all items in cart to display total
        //All prices are Strings in format "$##### USD" or "$########.## USD"
        for(int i=0;i<itemsInCart.size();i++) {
            Product tempProduct = itemsInCart.get(i);
            String priceString = tempProduct.getPrice();
            int temp = tempProduct.getQuantity();
            priceString = priceString.substring(priceString.indexOf('$')+1,
                    priceString.indexOf(' '));
            temp *= Integer.parseInt(priceString);
            mPrice += temp;
        }
        totalPrice.setText("$"+mPrice+" USD");
    }

    @Override
    public void itemsAddedOrRemoved(int change) {
        mPrice -= change;
        totalPrice.setText("$"+mPrice+" USD");
    }
}
