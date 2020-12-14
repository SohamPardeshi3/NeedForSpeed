package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.HashMap;

public class ChooseUserActivity_seeds extends AppCompatActivity {

    ListView seedsUserListView;
    ArrayList<String> seedUsers = new ArrayList<>();
    ArrayList<String> seedKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        seedsUserListView = findViewById(R.id.seedsUserListView);

        ArrayAdapter<String> seedsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, seedUsers);
        seedsUserListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        seedsUserListView.setItemChecked(2, true);
        seedsUserListView.setAdapter(seedsArrayAdapter);

        FirebaseDatabase.getInstance().getReference().child("wholesaler").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String user = snapshot.child("name").getValue().toString();
                seedUsers.add(user);                                                                    // adds all the users from the database
                seedKeys.add(snapshot.getKey());                                                        // gets the key of each user from the database
                seedsArrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        seedsUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> seedMap = new HashMap<>();
                seedMap.put("from", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                seedMap.put("type", getIntent().getStringExtra("Value"));
                seedMap.put("rate", getIntent().getStringExtra("Amount"));
                seedMap.put("quantity", getIntent().getStringExtra("Quantity"));
                seedMap.put("description", getIntent().getStringExtra("Description"));

                FirebaseDatabase.getInstance().getReference().child("wholesaler").child(seedKeys.get(position)).child("seeds").push().setValue(seedMap);          // item gets add to the database when a user is selected

                // add a disclaimer here
            }
        });

    }
}