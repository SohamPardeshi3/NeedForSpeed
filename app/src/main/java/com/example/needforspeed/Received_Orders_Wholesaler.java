package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Received_Orders_Wholesaler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received__orders__wholesaler);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void seedOrders(View view) {
        Intent seedOrder = new Intent(this, seed_received_orders.class);
        startActivity(seedOrder);
    }

    public void fertOrders(View view) {
        Intent fertOrder = new Intent(this, fert_received_orders.class);
        startActivity(fertOrder);
    }

    public void equipOrders(View view) {
        Intent equipOrders = new Intent(this, equip_rent_received_orders.class);
        startActivity(equipOrders);
    }
}