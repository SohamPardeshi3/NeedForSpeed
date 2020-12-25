package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PreviousOrders_WHolesaler extends AppCompatActivity {

    ListView previousListView;
    ArrayList<String> Users = new ArrayList<>();
    FirebaseAuth mAuth;
    ArrayList<DataSnapshot> ListInfo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders__w_holesaler);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        previousListView= findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Users);
        previousListView.setAdapter(arrayAdapter);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().getReference().child("wholesaler").child(mAuth.getCurrentUser().getUid()).child("ListItems").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Users.add(snapshot.getValue().toString());
                ListInfo.add(snapshot);
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
/*
        previousListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = ListInfo.get(position);

                Intent intent = new Intent(PreviousOrders_WHolesaler.this, ViewSeeds.class);

                intent.putExtra("from", snapshot.child("from").getValue().toString());
                intent.putExtra("phone", snapshot.child("phone").getValue().toString());
                intent.putExtra("type", snapshot.child("type").getValue().toString());
                intent.putExtra("rate", snapshot.child("rate").getValue().toString());
                intent.putExtra("quantity", snapshot.child("quantity").getValue().toString());
                intent.putExtra("description", snapshot.child("description").getValue().toString());
                intent.putExtra("key", snapshot.getKey());

                startActivity(intent);
            }
        });


 */

    }

}