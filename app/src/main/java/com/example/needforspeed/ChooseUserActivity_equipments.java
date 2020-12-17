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

public class ChooseUserActivity_equipments extends AppCompatActivity {

    ListView equipUserListView;
    ArrayList<String> equipUsers = new ArrayList<>();
    ArrayList<String> equipKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_equipments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        equipUserListView = findViewById(R.id.equipUserListView);

        ArrayAdapter<String> equipArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, equipUsers);
        equipUserListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        equipUserListView.setItemChecked(2, true);
        equipUserListView.setAdapter(equipArrayAdapter);

        FirebaseDatabase.getInstance().getReference().child("farmer").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String user = snapshot.child("name").getValue().toString();
                equipUsers.add(user);                                                                    // adds all the users from the database
                equipKeys.add(snapshot.getKey());                                                        // gets the key of each user from the database
                equipArrayAdapter.notifyDataSetChanged();
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

        equipUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> equipMap = new HashMap<>();
                equipMap.put("from", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                equipMap.put("type", getIntent().getStringExtra("Value"));
                equipMap.put("rate", getIntent().getStringExtra("Amount"));
                equipMap.put("quantity", getIntent().getStringExtra("Quantity"));
                equipMap.put("description", getIntent().getStringExtra("Description"));

                FirebaseDatabase.getInstance().getReference().child("farmer").child(equipKeys.get(position)).child("equipments").push().setValue(equipMap);          // item gets add to the database when a user is selected

                // add a disclaimer here
            }
        });
    }
}