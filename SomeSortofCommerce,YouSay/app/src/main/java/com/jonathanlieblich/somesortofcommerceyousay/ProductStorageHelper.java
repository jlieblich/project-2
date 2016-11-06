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
            +PRODUCT_TABLE+" VALUES ("
            +COL_ID+" INT PRIMARY KEY,"
            +COL_NAME+" TEXT,"
            +COL_DESCRIPTION+" TEXT,"
            +COL_PRICE+" TEXT,"
            +COL_CATEGORY+" TEXT)";

    public static final String CREATE_CART_TABLE = "CREATE TABLE "
            +CART_TABLE+" VALUES ("
            +COL_ID+" INT PRIMARY KEY,"
            +COL_NAME+" TEXT,"
            +COL_QUANTITY+" INT)";

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
                product.setPrice(cursor.getDouble(cursor.getColumnIndex(COL_PRICE)));
                product.setType(cursor.getString(cursor.getColumnIndex(COL_CATEGORY)));
                productList.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return productList;
    }

    //Return a list of all items in cart, each item represented by a String
    //Format - name+" "+quantity
    public List<Product> cartItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(CART_TABLE,
                new String[]{COL_ID, COL_PRICE, COL_QUANTITY},
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
                cursor.getInt(cursor.getColumnIndex(COL_QUANTITY));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return cart;
    }

    public long addToCart(int itemId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.query(PRODUCT_TABLE,
                new String[]{COL_NAME, COL_PRICE},
                COL_ID,
                new String[]{""+itemId},
                null,
                null,
                null);
        if(cursor.moveToFirst()) {
            values.put(COL_NAME, cursor.getString(cursor.getColumnIndex(COL_NAME)));
            values.put(COL_PRICE, cursor.getString(cursor.getColumnIndex(COL_PRICE)));
            values.put(COL_ID, itemId);
        }
        cursor.close();
        long returnId = db.insert(CART_TABLE, null, values);
        db.close();
        return returnId;
    }

    public List<Product> searchByName(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                COL_NAME+" LIKE ?",
                new String[]{query},
                null,
                null,
                null);
        List<Product> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COL_PRICE))));
                result.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    public List<Product> searchCategory(String query) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                COL_CATEGORY,
                new String[]{"%"+query+"%"},
                null,
                null,
                null);
        List<Product> result = new ArrayList<>();
        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                Product product = new Product();
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COL_PRICE))));
                result.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }
}
