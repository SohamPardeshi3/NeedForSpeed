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

public class farmer_buy_equipments extends AppCompatActivity {

    ListView equipListView;
    ArrayList<String> equipUsers = new ArrayList<>();
    FirebaseAuth mAuth;
    ArrayList<DataSnapshot> equipInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_buy_equipments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Disclaimer")
                .setMessage("Tap on the Item to know more about it or to add it in the cart!")
                .setPositiveButton("Okay", null)
                .show();

        equipListView = findViewById(R.id.equipListView);

        ArrayAdapter<String> equipAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipUsers);
        equipListView.setAdapter(equipAdapter);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().getReference().child("farmer").child(mAuth.getCurrentUser().getUid()).child("equipments").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                equipUsers.add(snapshot.child("type").getValue().toString());
                equipInfo.add(snapshot);
                equipAdapter.notifyDataSetChanged();
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

        equipListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = equipInfo.get(position);

                Intent equipIntent = new Intent(farmer_buy_equipments.this, ViewEquipments.class);

                equipIntent.putExtra("from", snapshot.child("from").getValue().toString());
                equipIntent.putExtra("phone", snapshot.child("phone").getValue().toString());
                equipIntent.putExtra("type", snapshot.child("type").getValue().toString());
                equipIntent.putExtra("rate", snapshot.child("rate").getValue().toString());
                equipIntent.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                equipIntent.putExtra("description", snapshot.child("description").getValue().toString());
                equipIntent.putExtra("key", snapshot.getKey());

                startActivity(equipIntent);
            }
        });
    }

    public void nextBuyActivity(View view){

        Intent nxtIntent = new Intent(this, CartActivity_Farmer_Euipments.class);
        startActivity(nxtIntent);

    }
}