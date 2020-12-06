package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class wholesaler_sell_fertilizers extends AppCompatActivity {

    ListView fertilizersListView;
    ArrayList<String> fertilizers = new ArrayList<>();
    String quantity, amount, value;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_fertilizers);

        fertilizersListView = findViewById(R.id.listView);

        Bundle extra =  getIntent().getExtras();

        if(extra != null) {
            value = extra.getString("Value");
            fertilizers.add(value);
            quantity = extra.getString("Quantity");
            amount = extra.getString("Amount");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fertilizers);
        fertilizersListView.setAdapter(arrayAdapter);

        // getting uid of the current user
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Map<String, Object> childUpdates = new HashMap<>();
        database.getReference("wholesaler").updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null) {
                    // storing the added items in the database by generating uid
                    FirebaseDatabase.getInstance().getReference().child("wholesaler").child(uid).child("fertilizers").child(ref.push().getKey()).setValue(value);
                } else {

                    Toast.makeText(wholesaler_sell_fertilizers.this, error.getDetails(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}