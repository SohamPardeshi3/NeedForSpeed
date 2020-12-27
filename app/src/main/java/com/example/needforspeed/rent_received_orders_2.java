package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class rent_received_orders_2 extends AppCompatActivity {

    ListView rentBuyersListView;
    ArrayList<String> Buyers = new ArrayList<>();
    String days, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_received_orders_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        rentBuyersListView = findViewById(R.id.rentBuyersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Buyers);
        rentBuyersListView.setAdapter(adapter);

        days = getIntent().getStringExtra("days");
        type = getIntent().getStringExtra("type");

        String finalItem = type + " [ No. of days : " + days + "]";

        Buyers.add(finalItem);
        adapter.notifyDataSetChanged();
    }
}