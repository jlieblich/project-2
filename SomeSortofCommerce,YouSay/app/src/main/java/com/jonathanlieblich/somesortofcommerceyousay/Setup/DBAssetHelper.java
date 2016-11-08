package com.jonathanlieblich.somesortofcommerceyousay.Setup;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by jonlieblich on 11/7/16.
 */

public class DBAssetHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "commerce";
    private static final int DB_VERSION = 1;

    public DBAssetHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
