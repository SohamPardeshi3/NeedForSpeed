package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class seed_received_orders_2 extends AppCompatActivity {

    ListView seedBuyersListView;
    ArrayList<String> seedBuyers = new ArrayList<>();
    String quantity, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_received_orders_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        seedBuyersListView = findViewById(R.id.seedBuyersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, seedBuyers);
        seedBuyersListView.setAdapter(adapter);

        quantity = getIntent().getStringExtra("quantity");
        type = getIntent().getStringExtra("type");

        String finalItem = type + " [ Quantity : " + quantity + "]";

        seedBuyers.add(finalItem);
        adapter.notifyDataSetChanged();
    }
}