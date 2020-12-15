package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    // delete this activity later
public class cottonSeedsActivity extends AppCompatActivity {

    EditText quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotton_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        quantityEditText = findViewById(R.id.rateEditText);
    }

    public void cart(View view) {
        Intent emptyCart = new Intent(this, emptyCartActivity.class);
        startActivity(emptyCart);
    }

    public void addToCart(View view) {
        if (quantityEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Please enter the quantity of seeds you want to buy!", Toast.LENGTH_SHORT).show();
        } else {
            Intent quantity = new Intent(cottonSeedsActivity.this, CartActivity.class);
            quantity.putExtra("Quantity", quantityEditText.getText().toString());
            startActivity(quantity);
        }
    }
}