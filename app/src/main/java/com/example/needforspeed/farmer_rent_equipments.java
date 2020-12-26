package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class farmer_rent_equipments extends AppCompatActivity {

    ListView rentListView;
    ArrayList<String> rentedUsers = new ArrayList<>();
    FirebaseAuth mAuth;
    ArrayList<DataSnapshot> rentInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_rent_equipments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Disclaimer")
                .setMessage("Tap on the Item to know more about it or to add it in the cart!")
                .setPositiveButton("Okay", null)
                .show();

        rentListView = findViewById(R.id.rentListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rentedUsers);
        rentListView.setAdapter(arrayAdapter);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().getReference().child("farmer").child(mAuth.getCurrentUser().getUid()).child("rentedItems").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                rentedUsers.add(snapshot.child("type").getValue().toString());
                rentInfo.add(snapshot);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        rentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = rentInfo.get(position);

                Intent intent = new Intent(farmer_rent_equipments.this, ViewRentedItems.class);     

                intent.putExtra("from", snapshot.child("from").getValue().toString());
                intent.putExtra("phone", snapshot.child("phone").getValue().toString());
                intent.putExtra("type", snapshot.child("type").getValue().toString());
                intent.putExtra("rate", snapshot.child("rate").getValue().toString());
                intent.putExtra("key", snapshot.getKey());

                startActivity(intent);
            }
        });
    }

    public void proceed(View view) {
        //add code here
        Intent checkOut = new Intent(this, CartActivity_Farmer_Rent.class);
        startActivity(checkOut);

    }
}