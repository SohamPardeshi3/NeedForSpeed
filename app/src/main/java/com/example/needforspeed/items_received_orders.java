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

public class items_received_orders extends AppCompatActivity {

    ListView itemsOrdersListView;
    ArrayList<String> buyers = new ArrayList<>();
    String Location, Buyer, Quantity, Type;
    ArrayList<DataSnapshot> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_received_orders);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        itemsOrdersListView = findViewById(R.id.itemsOrdersListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, buyers);
        itemsOrdersListView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("farmerOrders").child("itemOrders").addChildEventListener(new ChildEventListener() {
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

        itemsOrdersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = info.get(position);

                Intent Orders = new Intent(items_received_orders.this, items_received_orders_2.class);
                Orders.putExtra("type", snapshot.child("type").getValue().toString());
                Orders.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                Orders.putExtra("key", snapshot.getKey());
                startActivity(Orders);
            }
        });
    }
}