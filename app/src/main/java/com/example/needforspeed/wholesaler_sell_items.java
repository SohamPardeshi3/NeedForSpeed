package com.example.needforspeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class wholesaler_sell_items extends AppCompatActivity {

    EditText nameEditText;
    EditText addQuantityEditText;
    EditText addAmountEditText;
    Button sellFertilizers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_items);

        nameEditText = findViewById(R.id.nameEditText);
        addQuantityEditText = findViewById(R.id.addQuantityEditText);
        addAmountEditText = findViewById(R.id.addAmountEditText);
        sellFertilizers = findViewById(R.id.sellFertilizersButton);
    }

    public void sellFertilizers(View view) {

        if(addQuantityEditText.getText().toString().trim().length() == 0 && addAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity and amount of the item", Toast.LENGTH_SHORT).show();

        } else if(addQuantityEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();

        } else if(addAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_SHORT).show();

        } else {

            Intent fertilizers = new Intent(this, wholesaler_sell_fertilizers.class);
            fertilizers.putExtra("Value", nameEditText.getText().toString());
            fertilizers.putExtra("Quantity", addQuantityEditText.getText().toString());
            fertilizers.putExtra("Amount", addAmountEditText.getText().toString());
            startActivity(fertilizers);

        }

    }
}