package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class seed_received_orders extends AppCompatActivity {

    ListView seedsOrdersListView;
    ArrayList<String> seedbuyers = new ArrayList<>();
    String location, buyer, quantity, type;
    ArrayList<DataSnapshot> seedInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        seedsOrdersListView = findViewById(R.id.seedsOrdersListView);

        ArrayAdapter<String> seedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, seedbuyers);
        seedsOrdersListView.setAdapter(seedAdapter);

        FirebaseDatabase.getInstance().getReference().child("wholesalerOrders").child("seedOrders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                quantity = snapshot.child("quantity").getValue().toString();
                buyer = snapshot.child("buyer").getValue().toString();
                location = snapshot.child("location").getValue().toString();
                type = snapshot.child("type").getValue().toString();
                String finalItem = buyer + " (" + location + ") "
                        + " [" + type + "]";
                seedbuyers.add(finalItem);
                seedInfo.add(snapshot);
                seedAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        seedsOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = seedInfo.get(position);

                Intent seedOrders = new Intent(seed_received_orders.this, seed_received_orders_2.class);
                seedOrders.putExtra("type", snapshot.child("type").getValue().toString());
                seedOrders.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                seedOrders.putExtra("key", snapshot.getKey());
                startActivity(seedOrders);
            }
        });
    }
}