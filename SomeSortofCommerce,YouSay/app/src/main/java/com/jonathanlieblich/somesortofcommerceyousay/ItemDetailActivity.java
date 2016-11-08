package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ImageView productImage = (ImageView)findViewById(R.id.item_picture);

        TextView itemName = (TextView)findViewById(R.id.item_name);
        TextView itemDesc = (TextView)findViewById(R.id.item_description);
        TextView itemPrice = (TextView)findViewById(R.id.item_price);

        Button addItem = (Button)findViewById(R.id.add_to_cart);

        final EditText itemQuantity = (EditText)findViewById(R.id.item_quantity);

        Intent intent = getIntent();

        if(ProductStorageHelper.getInstance(getApplicationContext()).productById(intent.getIntExtra("ID", -1)).getId() == -1) {
            Toast.makeText(this, "ID = -1", Toast.LENGTH_SHORT).show();
        }

        final Product selectedItem = ProductStorageHelper
                .getInstance(getApplicationContext()).productById(intent.getIntExtra("ID", -1));

        if(selectedItem == null) {
            finish();
        }

        if(selectedItem.getId() <1) {
            Toast.makeText(this, "EMPTY ID", Toast.LENGTH_SHORT).show();
        }

        itemName.setText(selectedItem.getName());
        itemDesc.setText(selectedItem.getDescription());
        itemPrice.setText(""+selectedItem.getPrice());

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemQuantity.getText().length()==0) {
                    itemQuantity.setError("Quantity cannot be blank");
                } else {
                    ProductStorageHelper.getInstance(getApplicationContext())
                            .addToCart(selectedItem.getId());
                }
            }
        });
    }
}
