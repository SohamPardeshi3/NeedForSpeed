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

public class equip_received_orders extends AppCompatActivity {

    ListView equipOrdersListView;
    ArrayList<String> equipbuyers = new ArrayList<>();
    String location, buyer, quantity, type;
    ArrayList<DataSnapshot> equipInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        equipOrdersListView = findViewById(R.id.equipOrdersListView);

        ArrayAdapter<String> equipAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, equipbuyers);
        equipOrdersListView.setAdapter(equipAdapter);

        FirebaseDatabase.getInstance().getReference().child("wholesalerOrders").child("equipOrders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                quantity = snapshot.child("quantity").getValue().toString();
                buyer = snapshot.child("buyer").getValue().toString();
                location = snapshot.child("location").getValue().toString();
                type = snapshot.child("type").getValue().toString();
                String finalItem = buyer + " (" + location + ") "
                        + " [" + type + "]";
                equipbuyers.add(finalItem);
                equipInfo.add(snapshot);
                equipAdapter.notifyDataSetChanged();

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

        equipOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = equipInfo.get(position);

                Intent equipOrders = new Intent(equip_received_orders.this, equip_received_orders_2.class);
                equipOrders.putExtra("type", snapshot.child("type").getValue().toString());
                equipOrders.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                equipOrders.putExtra("key", snapshot.getKey());
                startActivity(equipOrders);
            }
        });
    }
}