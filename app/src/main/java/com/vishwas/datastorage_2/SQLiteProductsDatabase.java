package com.vishwas.datastorage_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vishwas on 8/16/2017.
 */

public class SQLiteProductsDatabase extends SQLiteOpenHelper
    {
    // Database name
    public static final String DB_NAME="database1";
    //Database version
    public static final int DB_VERSION_NUMBER=1;

    private static final String DB_TABLE_NAME = "products";
    private static final String DB_COLUMN_1_NAME = "products_name";

    private static final String DB_CREATE_SCRIPT = "create table " + DB_TABLE_NAME +
            " (_id integer primary key autoincrement, "+DB_COLUMN_1_NAME+" text not null);)";
    private SQLiteDatabase sqliteDBInstans=null;

    /*creating a default constructor and modify the constructor..
     *public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
     *super(context, name, factory, version);}
     */

    /**
     * Modifying constructor to take only Context and provide the database name
     * and version to the superclass
     */

    public SQLiteProductsDatabase(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
    }

        /**
         * implementing methods onUpgrade method
         * @param db
         * @param oldVersion
         * @param newVersion
         */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    /**
     * implementing methods onCreate
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DB_CREATE_SCRIPT);
    }

        /**
         * Called when the database has been opened
         * @throws SQLException
         */
    public void openDB() throws SQLException
    {
        if(this.sqliteDBInstans == null)
        {
            this.sqliteDBInstans=getWritableDatabase();
        }
    }

        /**
         * Close any open database object.
         */
    public void closeDB()
    {
        if(this.sqliteDBInstans!=null)
        {
            if(this.sqliteDBInstans.isOpen())
                this.sqliteDBInstans.close();
        }
    }

        /**
         * creating a method(insertProduct) to insert values in table and
         * passing all the values which needs to be insert in the Table as parameters to the function
         * @param productName
         * @return
         */
    public long insertProduct(String productName)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_1_NAME,productName);

        return this.sqliteDBInstans.insert(DB_TABLE_NAME,null,contentValues);
    }

    public boolean removeProduct(String productName)
    {
        int result = this.sqliteDBInstans.delete(DB_TABLE_NAME, "products_name='" + productName + "'", null);

        if(result > 0)
            return true;
        else
            return false;
    }


    public long updateProduct(String oldProductName, String newProductName)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_1_NAME, newProductName);
        return this.sqliteDBInstans.update(DB_TABLE_NAME, contentValues, "product_name='" + oldProductName + "'", null);
    }

    public String[] getAllProduct()
    {
        Cursor cursor = this.sqliteDBInstans.query(DB_TABLE_NAME, new String[]
                {DB_COLUMN_1_NAME}, null, null, null, null, null);

        if(cursor.getCount() >0)
        {
            String[] str = new String[cursor.getCount()];
            int i = 0;

            while (cursor.moveToNext())
            {
                str[i] = cursor.getString(cursor.getColumnIndex(DB_COLUMN_1_NAME));
                i++;
            }
            return str;
        }
        else
        {
            return new String[] {};
        }
    }
}
