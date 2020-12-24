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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChooseUserActivity extends AppCompatActivity {

    ListView chooseUserListView;
    ArrayList<String> users = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();
    String Name;
    String Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Disclaimer")
                .setMessage("Choose the user to whom you want make this item available!")
                .setPositiveButton("Okay", null)
                .show();

        chooseUserListView = findViewById(R.id.chooseUserListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, users);
        chooseUserListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        chooseUserListView.setItemChecked(2, true);
        chooseUserListView.setAdapter(adapter);

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


        FirebaseDatabase.getInstance().getReference().child("farmer").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String user = snapshot.child("name").getValue().toString();
                Location = snapshot.child("location").getValue().toString();
                Log.i("Location", Location);
                Log.i("Name", user);
                users.add(user + " (" + Location + ")");                                                                    // adds all the users from the database
                keys.add(snapshot.getKey());                                                        // gets the key of each user from the database
                adapter.notifyDataSetChanged();
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



        //adding values to the database
        chooseUserListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> fertMap = new HashMap<>();
                fertMap.put("phone", FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
                fertMap.put("from", Name);
                fertMap.put("type", getIntent().getStringExtra("Value"));
                fertMap.put("rate", getIntent().getStringExtra("Amount"));
                fertMap.put("quantity", getIntent().getStringExtra("Quantity"));
                fertMap.put("description", getIntent().getStringExtra("Description"));

                FirebaseDatabase.getInstance().getReference().child("farmer").child(keys.get(position)).child("fertilizers").push().setValue(fertMap);          // item gets add to the database when a user is selected

                // add a disclaimer here

            }
        });
    }

    public void homeScreen(View view){

        Intent home = new Intent(this, wholesaler_shopping_screen.class);
        startActivity(home);

    }

}