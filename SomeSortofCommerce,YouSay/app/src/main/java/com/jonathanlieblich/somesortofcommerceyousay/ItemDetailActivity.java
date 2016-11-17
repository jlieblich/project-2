package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {
    private ImageView productImage = (ImageView)findViewById(R.id.item_picture);
    private TextView itemName = (TextView)findViewById(R.id.item_name);
    private TextView itemDesc = (TextView)findViewById(R.id.item_description);
    private TextView itemPrice = (TextView)findViewById(R.id.item_price);
    private final EditText itemQuantity = (EditText)findViewById(R.id.item_quantity);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ImageView productImage = (ImageView)findViewById(R.id.item_picture);

        Button addItem = (Button)findViewById(R.id.add_to_cart);
        Button back = (Button)findViewById(R.id.back_btn);

        Intent intent = getIntent();
        int pic = intent.getIntExtra("PIC", -1304854);

        if(pic != -1304854) {
            productImage.setImageResource(pic);
        }

        final Product selectedItem = ProductStorageHelper
                .getInstance(getApplicationContext()).productById(intent.getIntExtra("ID", -1));

        AsyncItemDetail task = new AsyncItemDetail();
        task.execute(intent.getIntExtra("ID",-1));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemQuantity.getText().length()==0) {
                    itemQuantity.setError("Quantity cannot be blank");
                } else {
                    AsyncItemToCart addTask = new AsyncItemToCart();
                    addTask.execute(selectedItem.getId());
                }
            }
        });
    }
    
    private class AsyncItemToCart extends AsyncTask<Integer, Void, String> {
        private int quantity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            quantity = Integer.parseInt(itemQuantity.getText().toString());
        }

        @Override
        protected String doInBackground(Integer... integers) {
            if(quantity > 0)
                ProductStorageHelper.getInstance(getApplicationContext()).addToCart(integers[0], quantity);
            else
                return null;
            return ProductStorageHelper.getInstance(getApplicationContext()).productById(integers[0]).getName();
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            if(string != null)
            Toast.makeText(ItemDetailActivity.this, quantity+" "+string+" added to cart!", Toast.LENGTH_SHORT).show();
        }
    }

    private class AsyncItemDetail extends AsyncTask<Integer, Void, Product> {

        @Override
        protected Product doInBackground(Integer... integers) {
            Product product = ProductStorageHelper.getInstance(getApplicationContext()).productById(integers[0]);
            return product;
        }

        @Override
        protected void onPostExecute(Product product) {
            super.onPostExecute(product);
            itemName.setText(product.getName());
            itemDesc.setText(product.getDescription());
            itemPrice.setText(product.getPrice());
        }
    }
}
