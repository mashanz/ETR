package com.adhyahan.expansetrackrecord;

import android.content.Context;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;




/**
 * Created by RnEST on 10/18/2017.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Expanse.db";
    public static final String SQL_CRETE_TABLE =
            "CREATE TABLE " + Contract.Transaction.TABLE_NAME + "( " +
                    Contract.Transaction._ID + " INTEGER PRIMARY KEY, "+
                    Contract.Transaction.COLUMN_NAME_AMOUNT + " NUMERIC, " +
                    Contract.Transaction.COLUMN_NAME_DESCRIPTION + " TEXT )";
    private static final String SQL_DROP_TABLE = "DROP TABLE IF EXIST " + Contract.Transaction.TABLE_NAME;
    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CRETE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_CRETE_TABLE);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
