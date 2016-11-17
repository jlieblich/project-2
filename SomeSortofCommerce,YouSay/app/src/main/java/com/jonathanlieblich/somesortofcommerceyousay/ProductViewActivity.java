package com.jonathanlieblich.somesortofcommerceyousay;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;
import com.jonathanlieblich.somesortofcommerceyousay.Setup.DBAssetHelper;

import java.util.List;

//Main activity, display list of all products
public class ProductViewActivity extends AppCompatActivity {
    private RecyclerView mainRecycler;
    private ProductViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBAssetHelper dbSetup = new DBAssetHelper(ProductViewActivity.this);
        dbSetup.getReadableDatabase();

        AsyncProductViewTask task = new AsyncProductViewTask();
        task.execute();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.fab:
                        Intent intent = new Intent(ProductViewActivity.this, ShoppingCartActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        fab.setOnClickListener(onClickListener);
    }

    private class AsyncProductViewTask extends AsyncTask<Void, Integer, List<Product>> {

        @Override
        protected List<Product> doInBackground(Void... voids) {
            return ProductStorageHelper.getInstance(getApplicationContext()).getProductList();
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            mAdapter = new ProductViewAdapter(products);
            mainRecycler = (RecyclerView)findViewById(R.id.product_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            mainRecycler.setLayoutManager(layoutManager);
            mainRecycler.setAdapter(mAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_view, menu);

        SearchManager searchmanager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        ComponentName componentName = new ComponentName(this, ProductViewActivity.class);
        searchView.setSearchableInfo(searchmanager.getSearchableInfo(componentName));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            AsyncSearchTask task = new AsyncSearchTask();
            task.execute(query);
        }
    }

    private class AsyncSearchTask extends AsyncTask<String, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(String... strings) {
            return ProductStorageHelper.getInstance(getApplicationContext()).searchByName(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if(products.size() < 1) {
                Toast.makeText(ProductViewActivity.this, "No items matched your search", Toast.LENGTH_SHORT).show();
                products = ProductStorageHelper.getInstance(getApplicationContext()).getProductList();
            }
            mainRecycler.setAdapter(new ProductViewAdapter(products));
            mAdapter.notifyDataSetChanged();
        }
    }
}
