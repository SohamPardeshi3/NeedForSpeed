package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class rent_equipments extends AppCompatActivity {

    EditText rentNameEditText, rentAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_equipments);

        rentNameEditText = findViewById(R.id.rentNameEditText);
        rentAmountEditText = findViewById(R.id.rentAmountEditText);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public void rentEquip(View view) {

        if(rentNameEditText.getText().toString().trim().length() == 0 && rentAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the Name and Rate of the item!", Toast.LENGTH_SHORT).show();

        } else if(rentNameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the Name of the item!", Toast.LENGTH_SHORT).show();

        } else if(rentAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the rate/day of the item!", Toast.LENGTH_SHORT).show();

        } else {

            Intent rent = new Intent(rent_equipments.this, RentUserActivity.class);
            rent.putExtra("Value", rentNameEditText.getText().toString());
            rent.putExtra("Amount", rentAmountEditText.getText().toString());
            startActivity(rent);
        }

    }

    public void discardEquip(View view) {
        Intent discardEqui = new Intent(this, sellrent_equip.class);
        startActivity(discardEqui);
        finish();
    }
}