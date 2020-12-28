package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class equip_received_orders_2 extends AppCompatActivity {

    ListView equipBuyersListView;
    ArrayList<String> equipBuyers = new ArrayList<>();
    String quantity, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_received_orders_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        equipBuyersListView = findViewById(R.id.equipBuyersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, equipBuyers);
        equipBuyersListView.setAdapter(adapter);

        quantity = getIntent().getStringExtra("quantity");
        type = getIntent().getStringExtra("type");

        String finalItem = type + " [ Quantity : " + quantity + "]";

        equipBuyers.add(finalItem);
        adapter.notifyDataSetChanged();
    }
}