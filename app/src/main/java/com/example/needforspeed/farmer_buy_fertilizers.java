package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    ArrayList<String> fertilizers = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    String uid;
    ArrayList<String> fert = new ArrayList<>();
    FirebaseDatabase database;

    //olalala
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_buy_fertilizers);

        fertilizersListView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fert);
        fertilizersListView.setAdapter(arrayAdapter);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();

        FirebaseDatabase.getInstance().getReference().child("wholesaler").child(mAuth.getCurrentUser().getUid()).child("fertilizers").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    fert.add(dataSnapshot.child("value").getValue().toString());
                    arrayAdapter.notifyDataSetChanged();
                }
//                Query query = databaseReference.orderByChild("value");
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        }
//
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


            // Also getting results with uid
//        FirebaseDatabase.getInstance().getReference().child("wholesaler").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                String fertilizer = snapshot.child("fertilizers").getValue().toString();
//                fertilizers.add(fertilizer);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) { }
//        });
    }
}