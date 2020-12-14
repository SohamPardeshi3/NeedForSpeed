package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class wholesaler_sell_items extends AppCompatActivity {

    EditText itemNameEditText;
    EditText addQuantityEditText;
    EditText addAmountEditText;
    EditText addDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_items);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        itemNameEditText = findViewById(R.id.itemNameEditText);
        addQuantityEditText = findViewById(R.id.addQuantityEditText);
        addAmountEditText = findViewById(R.id.addAmountEditText);
        addDescEditText = findViewById(R.id.addDescEditText);
    }

    public void sellFertilizers(View view) {

        if(itemNameEditText.getText().toString().trim().length() == 0 && addQuantityEditText.getText().toString().trim().length() == 0 && addAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity and amount of the item", Toast.LENGTH_SHORT).show();

        } else if(addQuantityEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();

        } else if(addAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_SHORT).show();

        } else if(addDescEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter a short description about the item", Toast.LENGTH_SHORT).show();

        } else if(itemNameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter name of the item", Toast.LENGTH_SHORT).show();

        } else {

            Intent fertilizers = new Intent(wholesaler_sell_items.this, ChooseUserActivity.class);
            fertilizers.putExtra("Value", itemNameEditText.getText().toString());
            fertilizers.putExtra("Quantity", addQuantityEditText.getText().toString());
            fertilizers.putExtra("Amount", addAmountEditText.getText().toString());
            fertilizers.putExtra("Description",addDescEditText.getText().toString());
            startActivity(fertilizers);
        }
    }

    public void discard(View view) {
        Intent intent = new Intent(wholesaler_sell_items.this, wholesaler_sell_by_category.class);
        startActivity(intent);
        finish();
    }

}