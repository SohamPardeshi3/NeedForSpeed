package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CartActivity_Farmer_Rent extends AppCompatActivity {

    EditText addressEditText;

    ListView itemsCheckList;
    Set<String> RentEquipItemSet;


    String toRemove;
    String[] listItems;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__farmer__rent);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Toast.makeText(this, "Long press on the item to delete it!", Toast.LENGTH_LONG).show();

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("farmer").child(mAuth.getCurrentUser().getUid()).child("ListItems");

        addressEditText = findViewById(R.id.addressEditText);
        itemsCheckList = findViewById(R.id.listView);

        SharedPreferences hashSetValue = getSharedPreferences("Rent_Equip_hashSet_value", 0);
        RentEquipItemSet = hashSetValue.getStringSet("Rent_Equip_Final_List", null);

        Log.i("Equipment List", String.valueOf(RentEquipItemSet));

        RentEquipItemSet.remove("Items are: ");

        listItems = new String[RentEquipItemSet.size()];
        RentEquipItemSet.toArray(listItems);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        itemsCheckList.setAdapter(arrayAdapter);


        itemsCheckList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                toRemove = arrayAdapter.getItem(position);
                listItems[position] = "Removed";

                RentEquipItemSet.remove(toRemove);
                Log.i("After Seeds Item value", String.valueOf(RentEquipItemSet));

                SharedPreferences hashSetValue21 = getSharedPreferences("Rent_Equip_hashSet_value", 0);
                SharedPreferences.Editor editor21 = hashSetValue21.edit();
                editor21.putStringSet("Rent_Equip_Final_List", RentEquipItemSet);
                editor21.commit();


                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        SharedPreferences Addname = getSharedPreferences("Address_id", 0);
        String Address_p = Addname.getString("Given_address", null);

        addressEditText.setText(Address_p);

    }

    public void placeOrder(View view) {

        SharedPreferences hashSetValue = getSharedPreferences("Rent_Equip_hashSet_value", 0);
        Set<String> RentEquipItemSet2 = hashSetValue.getStringSet("Rent_Equip_Final_List", null);

        if (!addressEditText.getText().toString().isEmpty() && !RentEquipItemSet2.isEmpty()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Are you Sure?")
                    .setMessage("Do you want to place order?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
/*
                            SharedPreferences hashSetValue =getSharedPreferences("hashSet_value",0);
                            SharedPreferences.Editor editor12 = hashSetValue.edit();
                            editor12.clear();
                            editor12.apply();
                            finish();

 */
                            List<String> FinalListItems = new ArrayList<>();

                            FinalListItems.addAll(RentEquipItemSet2);

                            FinalListItems.remove("Items are: ");

                            reference.push().setValue(FinalListItems);

                            Intent order = new Intent(CartActivity_Farmer_Rent.this, placeOrderActivity.class);
                            startActivity(order);

                        }
                    })
                    .setNegativeButton("No", null)

                    .show();
        }else {
            Toast.makeText(this, "Address not given or Cart Empty!", Toast.LENGTH_SHORT).show();
        }

    }

    public void nextMap(View view){

        Intent imap = new Intent(this, MapActivity.class);
        startActivity(imap);

    }

    public void Reload(View view){

        finish();
        startActivity(getIntent());

    }

}