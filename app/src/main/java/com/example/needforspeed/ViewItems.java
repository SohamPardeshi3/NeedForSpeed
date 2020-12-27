package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class ViewItems extends AppCompatActivity {

    TextView fromItemsTextView, typeItemsTextView, quantityItemsTextView, rateItemsTextView;
    EditText editTextNumber;
    String value, checkvalue;
    int val1,val2;

    Set<String> set = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

       // SharedPreferences hashSetValue = getSharedPreferences("hashSet_value", 0);
       // set = hashSetValue.getStringSet("Final_List", null);



        fromItemsTextView = findViewById(R.id.fromItemsTextView);
        typeItemsTextView = findViewById(R.id.typeItemsTextView);
        quantityItemsTextView = findViewById(R.id.quantityItemsTextView);
        rateItemsTextView = findViewById(R.id.rateItemsTextView);
        editTextNumber = findViewById(R.id.editTextNumber);

        value = getIntent().getStringExtra("quantity");
        val1 = Integer.valueOf(value);

        fromItemsTextView.setText(getIntent().getStringExtra("from"));
        typeItemsTextView.setText(getIntent().getStringExtra("type"));

        quantityItemsTextView.setText(getIntent().getStringExtra("quantity"));
        rateItemsTextView.setText(getIntent().getStringExtra("rate"));





    }

    public void addToCart(View view){

        SharedPreferences hashSetValue = getSharedPreferences("hashSet_value", 0);
        Set<String>  ItemsSet = hashSetValue.getStringSet("Final_List", null);

        Log.i("Item Set Value", String.valueOf(ItemsSet));

        checkvalue = editTextNumber.getText().toString();

        Log.i("Value of val1", String.valueOf(val1));
        Log.i("Value of val2", String.valueOf(val2));

        val2 = Integer.valueOf(checkvalue);

        if (checkvalue.isEmpty()){
            Toast.makeText(this, "Please enter quantity!", Toast.LENGTH_SHORT).show();
        }else {

            if (val2 <= val1) {

                set.addAll(ItemsSet);

                String finalItem;

                finalItem = "Item: " + getIntent().getStringExtra("type") + "  Quantity: " + editTextNumber.getText().toString() + "  Rs: " + getIntent().getStringExtra("rate");

                Log.i("Type of XYZ", String.valueOf(finalItem));

                set.add(finalItem);

                Log.i("HashSet Value", String.valueOf(set));

                SharedPreferences hashSetValue2 = getSharedPreferences("hashSet_value", 0);
                SharedPreferences.Editor editor12 = hashSetValue2.edit();
                editor12.putStringSet("Final_List", set);
                editor12.apply();


                Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Enter less quantity!", Toast.LENGTH_SHORT).show();

            }

        }

        }












    public void DoneRef(View view){


        Intent intent = new Intent(this, wholesaler_buy_items.class);
        startActivity(intent);

    }

}