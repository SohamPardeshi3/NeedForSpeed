package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class rent_received_orders extends AppCompatActivity {

    ListView rentOrdersListView;
    ArrayList<String> buyers = new ArrayList<>();
    String location, buyer, days, type;
    ArrayList<DataSnapshot> Info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        rentOrdersListView = findViewById(R.id.rentOrdersListView);

        ArrayAdapter<String> Adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buyers);
        rentOrdersListView.setAdapter(Adapter);

        FirebaseDatabase.getInstance().getReference().child("wholesalerOrders").child("equipRentOrders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                days = snapshot.child("days").getValue().toString();
                buyer = snapshot.child("buyer").getValue().toString();
                location = snapshot.child("location").getValue().toString();
                type = snapshot.child("type").getValue().toString();
                String finalItem = buyer + " (" + location + ") "
                        + " [" + type + "]";
                buyers.add(finalItem);
                Info.add(snapshot);
                Adapter.notifyDataSetChanged();

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

        rentOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = Info.get(position);

                Intent equipOrders = new Intent(rent_received_orders.this, rent_received_orders_2.class);
                equipOrders.putExtra("type", snapshot.child("type").getValue().toString());
                equipOrders.putExtra("days", snapshot.child("days").getValue().toString());
                equipOrders.putExtra("key", snapshot.getKey());
                startActivity(equipOrders);
            }
        });
    }
}