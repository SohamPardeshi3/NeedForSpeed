package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class wholesaler_sell_equip extends AppCompatActivity {

    EditText equipNameEditText;
    EditText equipQuantityEditText;
    EditText equipAmountEditText;
    EditText equipDescEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_equip);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        equipNameEditText = findViewById(R.id.equipNameEditText);
        equipQuantityEditText = findViewById(R.id.equipQuantityEditText);
        equipAmountEditText = findViewById(R.id.equipAmountEditText);
        equipDescEditText = findViewById(R.id.equipDescEditText);
    }

    public void sellEquip(View view) {
        if(equipNameEditText.getText().toString().trim().length() == 0 && equipQuantityEditText.getText().toString().trim().length() == 0 && equipAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

        } else if(equipQuantityEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();

        } else if(equipAmountEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter the amount!", Toast.LENGTH_SHORT).show();

        } else if(equipDescEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter a short description about the item", Toast.LENGTH_SHORT).show();

        } else if(equipNameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter name of the item", Toast.LENGTH_SHORT).show();

        } else {

            Intent equips = new Intent(wholesaler_sell_equip.this, ChooseUserActivity_equipments.class);
            equips.putExtra("Value", equipNameEditText.getText().toString());
            equips.putExtra("Quantity", equipQuantityEditText.getText().toString());
            equips.putExtra("Amount", equipAmountEditText.getText().toString());
            equips.putExtra("Description",equipDescEditText.getText().toString());
            startActivity(equips);

        }
    }

    public void discardEquip(View view) {
        Intent discardEquipments = new Intent(this, sellrent_equip.class);
        startActivity(discardEquipments);
        finish();
    }
}