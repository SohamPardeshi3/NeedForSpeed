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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CartActivity_Farmer_Seeds extends AppCompatActivity {


    EditText addressEditText;

    ListView itemsCheckList;
    Set<String> SeedsItemSet;
    List<String> FinalListItems;

    String toRemove;
    String[] listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart__farmer__seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Toast.makeText(this, "Long press on the item to delete it!", Toast.LENGTH_LONG).show();


        addressEditText = findViewById(R.id.addressEditText);
        itemsCheckList = findViewById(R.id.listView);


        SharedPreferences hashSetValue4 = getSharedPreferences("Seeds_value", 0);
        SeedsItemSet = hashSetValue4.getStringSet("Seeds_Final_List", null);

        SeedsItemSet.remove("Items are: ");
        Log.i("Before Seeds Item value", String.valueOf(SeedsItemSet));


        listItems = new String[SeedsItemSet.size()];
        SeedsItemSet.toArray(listItems);

        FinalListItems = Arrays.asList(listItems);
        Collections.reverse(FinalListItems);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        itemsCheckList.setAdapter(arrayAdapter);

        itemsCheckList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                toRemove = arrayAdapter.getItem(position);
                listItems[position] = "Removed";

                SeedsItemSet.remove(toRemove);
                Log.i("After Seeds Item value", String.valueOf(SeedsItemSet));
                
                SharedPreferences hashSetValue4 = getSharedPreferences("Seeds_value", 0);
                SharedPreferences.Editor editor14 = hashSetValue4.edit();
                editor14.putStringSet("Seeds_Final_List", SeedsItemSet);
                editor14.commit();

                 arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });



        SharedPreferences Addname = getSharedPreferences("Address_id", 0);
        String Address_p = Addname.getString("Given_address", null);

        addressEditText.setText(Address_p);
    }

    public void placeOrder(View view) {

        SharedPreferences hashSetValue4 = getSharedPreferences("Seeds_value", 0);
        Set<String> SeedsItemList2 = hashSetValue4.getStringSet("Seeds_Final_List", null);

        if (!addressEditText.getText().toString().isEmpty() && !SeedsItemList2.isEmpty()) {

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
                            Intent order = new Intent(CartActivity_Farmer_Seeds.this, placeOrderActivity.class);
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