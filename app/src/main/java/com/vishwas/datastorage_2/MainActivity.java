package com.vishwas.datastorage_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity
{
    private SQLiteProductsDatabase sqtiteProductsDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocompleteProduct);

        /**creating an object of class SQLiteProductsDatabase
         * and calling the method of inserting data in it.
         */
        SQLiteProductsDatabase sqliteProductsDatabase = new SQLiteProductsDatabase(MainActivity.this);
        sqliteProductsDatabase.openDB();
        sqliteProductsDatabase.insertProduct("Laptop Asus");
        sqliteProductsDatabase.insertProduct("Laptop Acer");
        sqliteProductsDatabase.insertProduct("Laptop Dell");
        sqliteProductsDatabase.insertProduct("Laptop HP");
        sqliteProductsDatabase.insertProduct("Laptop Apple");
        sqliteProductsDatabase.insertProduct("Laptop Lenovo");
        sqliteProductsDatabase.insertProduct("Laptop MSI");
        sqliteProductsDatabase.insertProduct("Laptop Samsung");



        String[] product = sqliteProductsDatabase.getAllProduct();

       // Print out the values to the log
        for(int i = 0; i < product.length; i++)
        {
           Log.i(this.toString(), product[i]);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, product);
        textView.setAdapter(adapter);
    }

    public void onDestroy()
    {
        super.onDestroy();
        sqtiteProductsDatabase.close();
    }
}

