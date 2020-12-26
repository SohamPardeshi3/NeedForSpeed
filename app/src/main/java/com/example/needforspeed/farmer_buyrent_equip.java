package com.example.needforspeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class farmer_buyrent_equip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_buyrent_equip);
    }

    public void buyEquipments(View view) {
        Intent buy = new Intent(this, farmer_buy_equipments.class);
        startActivity(buy);
    }

    public void rent(View view) {
        Intent rent = new Intent(this, farmer_rent_equipments.class);
        startActivity(rent);
    }
}