package com.jonathanlieblich.somesortofcommerceyousay;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class ProductStorageHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "commerce";
    public static final int DB_VERSION = 1;

    public static final String PRODUCT_TABLE = "products";

    public static final String COL_NAME = "productName";
    public static final String COL_DESCRIPTION = "productDescription";
    public static final String COL_PRICE = "productPrice";
    public static final String COL_CATEGORY = "productCategory";

    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE "
            +PRODUCT_TABLE+" VALUES ("
            +COL_NAME+" TEXT,"
            +COL_DESCRIPTION+" TEXT,"
            +COL_PRICE+" TEXT,"
            +COL_CATEGORY+" TEXT)";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS "+PRODUCT_TABLE);
        onCreate(sqLiteDatabase);
    }

    //Query database for products matching a specific name
    public void searchByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PRODUCT_TABLE,
                null,
                COL_NAME,
                new String[]{name},
                null,
                null,
                null);
    }
}
