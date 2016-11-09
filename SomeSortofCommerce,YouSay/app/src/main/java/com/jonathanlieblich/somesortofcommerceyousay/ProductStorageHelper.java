package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jonathanlieblich.somesortofcommerceyousay.ProductObjects.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class ProductStorageHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "commerce";
    public static final int DB_VERSION = 1;

    public static final String PRODUCT_TABLE = "products";
    public static final String CART_TABLE = "shoppingCart";

    public static final String COL_ID = "productId";
    public static final String COL_NAME = "productName";
    public static final String COL_DESCRIPTION = "productDescription";
    public static final String COL_PRICE = "productPrice";
    public static final String COL_CATEGORY = "productCategory";
    public static final String COL_QUANTITY = "productQuantity";

    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE "
            +PRODUCT_TABLE+" ("
            +COL_ID+" INT PRIMARY KEY,"
            +COL_NAME+" TEXT,"
            +COL_DESCRIPTION+" TEXT,"
            +COL_PRICE+" TEXT,"
            +COL_CATEGORY+" TEXT)";

    public static final String CREATE_CART_TABLE = "CREATE TABLE "
            +CART_TABLE+" ("
            +COL_ID+" INT PRIMARY KEY,"
            +COL_NAME+" TEXT,"
            +COL_QUANTITY+" INT,"
            +COL_PRICE+" TEXT)";

    public static ProductStorageHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new ProductStorageHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private static ProductStorageHelper sInstance = null;

    private ProductStorageHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS "+PRODUCT_TABLE);
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS "+CART_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Return list of all products in database
    public List<Product> getProductList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
        List<Product> productList = new ArrayList<>();
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COL_PRICE)));
                product.setType(cursor.getString(cursor.getColumnIndex(COL_CATEGORY)));
                productList.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return productList;
    }

    //Return product based on ID
    public Product productById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                COL_ID+" = ?",
                new String[]{""+id},
                null,
                null,
                null);
        if(cursor.moveToFirst()) {
            Product result = new Product();
            result.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            result.setPrice(cursor.getString(cursor.getColumnIndex(COL_PRICE)));
            result.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
            result.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            cursor.close();
            return result;
        }
        cursor.close();
        return null;
    }

    //Return a list of all items in cart
    public List<Product> cartItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CART_TABLE,
                new String[]{COL_NAME, COL_ID, COL_PRICE, COL_QUANTITY},
                null,
                null,
                null,
                null,
                null);
        List<Product> cart = new ArrayList<>();

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndex(COL_QUANTITY)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COL_PRICE)));
                cart.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return cart;
    }

    public long addToCart(int itemId, int quantity) {
        Product selected = productById(itemId);
        ContentValues values = new ContentValues();
        values.put(COL_NAME, selected.getName());
        values.put(COL_QUANTITY, quantity);
        values.put(COL_PRICE, selected.getPrice());
        SQLiteDatabase db = getWritableDatabase();
        long insertion = db.insert(CART_TABLE, null, values);
        db.close();
        return insertion;
    }

    public List<Product> searchByName(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                COL_NAME+" LIKE ? OR "+COL_CATEGORY+" LIKE ?",
                new String[]{"%"+query+"%","%"+query+"%"},
                null,
                null,
                null);
        List<Product> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setPrice(cursor.getString(cursor.getColumnIndex(COL_PRICE)));
                result.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    //Remove all items from cart, return number of items "purchased"
    public int checkoutCart() {
        SQLiteDatabase db = getWritableDatabase();
        int deleted = db.delete(CART_TABLE, "1", null);
        db.close();
        return deleted;
    }

    public void removeFromCart(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(CART_TABLE, COL_NAME+" = ?", new String[]{name});
        db.close();
    }
}
