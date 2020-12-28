package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class farmer_buy_fertilizers extends AppCompatActivity {

    ListView fertilizersListView;
    ArrayList<String> users = new ArrayList<>();
    FirebaseAuth mAuth;
    ArrayList<DataSnapshot> info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_buy_fertilizers);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Disclaimer")
                .setMessage("Tap on the Item to know more about it or to add it in the cart and then click on Proceed!")
                .setPositiveButton("Okay", null)
                .show();

        fertilizersListView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
        fertilizersListView.setAdapter(arrayAdapter);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase.getInstance().getReference().child("farmer").child(mAuth.getCurrentUser().getUid()).child("fertilizers").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                users.add(snapshot.child("type").getValue().toString());
                info.add(snapshot);
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

        fertilizersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataSnapshot snapshot = info.get(position);

                Intent intent = new Intent(farmer_buy_fertilizers.this, ViewFertilizers.class);

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

    }

    public void nextBuyActivity(View view){

        Intent nxtIntent = new Intent(this, Cart_Activity_Farmer_Fertilizer.class);
        startActivity(nxtIntent);

    }

}