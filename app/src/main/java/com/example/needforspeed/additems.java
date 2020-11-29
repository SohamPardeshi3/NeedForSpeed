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

    String item, quantity, rate;

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

        Toast.makeText(this, "Item successfully added!", Toast.LENGTH_SHORT).show();

        if (!itemName.getText().toString().isEmpty()) {
            item = itemName.getText().toString();
        }else {

            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            return;

        }

        if (!itemQuantity.getText().toString().isEmpty()) {
           quantity = itemQuantity.getText().toString() + "kg";
        }else {

            Toast.makeText(this, "Please Enter Quantity!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!itemRate.getText().toString().isEmpty()) {
            rate = itemRate.getText().toString() + "Rs/kg";
        }else {

            Toast.makeText(this, "Please Enter Rate!", Toast.LENGTH_SHORT).show();
            return;

        }


        Intent i = new Intent(this, list.class);
        i.putExtra("Value", item);
        i.putExtra("Rate", rate);
        i.putExtra("Quantity", quantity);
        startActivity(i);



    }

}
