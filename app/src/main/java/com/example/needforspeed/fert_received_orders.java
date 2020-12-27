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

public class fert_received_orders extends AppCompatActivity {

    ListView fertOrdersListView;
    ArrayList<String> buyers = new ArrayList<>();
    String Location, Buyer, Quantity, Type;
    ArrayList<DataSnapshot> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fert_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fertOrdersListView = findViewById(R.id.fertOrdersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buyers);
        fertOrdersListView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("wholesalerOrders").child("fertOrders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Quantity = snapshot.child("quantity").getValue().toString();
                Buyer = snapshot.child("buyer").getValue().toString();
                Location = snapshot.child("location").getValue().toString();
                Type = snapshot.child("type").getValue().toString();
                String finalItem = Buyer + " (" + Location + ") "
                        + " [" + Type + "]";
                buyers.add(finalItem);
                info.add(snapshot);
                adapter.notifyDataSetChanged();

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

        fertOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = info.get(position);

                Intent fertOrders = new Intent(fert_received_orders.this, fert_received_orders_2.class);
                fertOrders.putExtra("type", snapshot.child("type").getValue().toString());
                fertOrders.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                fertOrders.putExtra("key", snapshot.getKey());
                startActivity(fertOrders);
            }
        });
    }
}