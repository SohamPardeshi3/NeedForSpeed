package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class additems extends AppCompatActivity {

    EditText itemName, itemRate, itemQuantity;

//    String item, quantity, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        itemName = findViewById(R.id.editText2);
        itemRate = findViewById(R.id.editText4);
        itemQuantity = findViewById(R.id.editText5);
    }

    public void save(View view){

        if(itemName.getText().toString().trim().length() == 0 && itemQuantity.getText().toString().trim().length() == 0 && itemRate.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

        } else if(itemQuantity.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();

        } else if(itemRate.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_SHORT).show();

        } else if(itemName.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter name of the item", Toast.LENGTH_SHORT).show();

        } else {

            Intent add = new Intent(additems.this, ChooseWholesalerActivity.class);
            add.putExtra("Value", itemName.getText().toString());
            add.putExtra("Quantity", itemQuantity.getText().toString());
            add.putExtra("Amount", itemRate.getText().toString());
            startActivity(add);

        }
    }

    public void discardItems(View view) {
        Intent dis = new Intent(this, shoppingScreen.class);
        startActivity(dis);
        finish();
    }

}
