package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class wholesaler_sell_seeds extends AppCompatActivity {

    EditText seedNameEditText;
    EditText seedQuantityEditText;
    EditText seedAmountEditText;
    EditText seedDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        seedNameEditText = findViewById(R.id.seedNameEditText);
        seedQuantityEditText = findViewById(R.id.seedQuantityEditText);
        seedAmountEditText = findViewById(R.id.seedAmountEditText);
        seedDescEditText = findViewById(R.id.seedDescEditText);
    }

    public void sellSeed(View view) {
        if(seedNameEditText.getText().toString().trim().length() == 0 && seedQuantityEditText.getText().toString().trim().length() == 0 && seedAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

        } else if(seedQuantityEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();

        } else if(seedAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_SHORT).show();

        } else if(seedDescEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter a short description about the item", Toast.LENGTH_SHORT).show();

        } else if(seedNameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter name of the item", Toast.LENGTH_SHORT).show();

        } else {

            Intent seeds = new Intent(wholesaler_sell_seeds.this, ChooseUserActivity_seeds.class);
            seeds.putExtra("Value", seedNameEditText.getText().toString());
            seeds.putExtra("Quantity", seedQuantityEditText.getText().toString());
            seeds.putExtra("Amount", seedAmountEditText.getText().toString());
            seeds.putExtra("Description",seedDescEditText.getText().toString());
            startActivity(seeds);

        }
    }

    public void discardSeeds(View view) {
        Intent intent = new Intent(wholesaler_sell_seeds.this, wholesaler_sell_by_category.class);
        startActivity(intent);
        finish();
    }
}