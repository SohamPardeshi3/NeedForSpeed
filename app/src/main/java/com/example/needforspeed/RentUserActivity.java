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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RentUserActivity extends AppCompatActivity {

    ListView rentUserListView;
    ArrayList<String> rentUsers = new ArrayList<>();
    ArrayList<String> rentKeys = new ArrayList<>();
    String Name;
    String Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Disclaimer")
                .setMessage("Choose the user to whom you want make this item available!")
                .setPositiveButton("Okay", null)
                .show();

        rentUserListView = findViewById(R.id.rentUserListView);

        ArrayAdapter<String> rentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, rentUsers);
        rentUserListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        rentUserListView.setItemChecked(2, true);
        rentUserListView.setAdapter(rentAdapter);

        FirebaseDatabase.getInstance().getReference().child("farmer").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String user = snapshot.child("name").getValue().toString();
                Location = snapshot.child("location").getValue().toString();
                rentUsers.add(user + " (" + Location + ")");                                                                    // adds all the users from the database
                rentKeys.add(snapshot.getKey());                                                        // gets the key of each user from the database
                rentAdapter.notifyDataSetChanged();
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

        //getting the name of wholesaler(current)
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("wholesaler").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Name = snapshot.child("name").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //adding values to the database
        rentUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> rentMap = new HashMap<>();
                rentMap.put("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                rentMap.put("from", Name);
                rentMap.put("type", getIntent().getStringExtra("Value"));
                rentMap.put("rate", getIntent().getStringExtra("Amount"));

                FirebaseDatabase.getInstance().getReference().child("farmer").child(rentKeys.get(position)).child("rentedItems").push().setValue(rentMap);          // item gets add to the database when a user is selected

                // add a disclaimer here

            }
        });

    }

    public void homeScreen(View view){

        Intent home = new Intent(this, wholesaler_shopping_screen.class);
        startActivity(home);

    }

}