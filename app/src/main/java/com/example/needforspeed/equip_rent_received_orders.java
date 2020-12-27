package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class equip_rent_received_orders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_rent_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void equipOrders(View view) {
        Intent equip = new Intent(this, equip_received_orders.class);
        startActivity(equip);
    }

    public void rentEquipOrders(View view) {
        Intent rent = new Intent(this, rent_received_orders.class);
        startActivity(rent);
    }
}