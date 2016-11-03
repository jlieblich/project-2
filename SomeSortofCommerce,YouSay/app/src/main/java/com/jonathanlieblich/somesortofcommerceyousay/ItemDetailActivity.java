package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

// TODO: Add master detail flow to XML if screen width > 900dp
public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
    }
}
