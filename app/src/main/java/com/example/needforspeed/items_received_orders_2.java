package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class items_received_orders_2 extends AppCompatActivity {

    ListView itemsUsersListView;
    ArrayList<String> Buyers = new ArrayList<>();
    String quantity, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_received_orders_2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        itemsUsersListView = findViewById(R.id.itemsUsersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Buyers);
        itemsUsersListView.setAdapter(adapter);

        quantity = getIntent().getStringExtra("quantity");
        type = getIntent().getStringExtra("type");

        String finalItem = type + " [ Quantity : " + quantity + "]";

        Buyers.add(finalItem);
        adapter.notifyDataSetChanged();
    }
}