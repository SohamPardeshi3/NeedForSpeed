package com.example.needforspeed;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    DatabaseReference rootRef, demoRef;


    EditText addressEditText;

    ListView itemsCheckList;
    Set<String> ItemsSet;
    List<String> FinalListItems;

    String toRemove;
    String[] listItems;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Toast.makeText(this, "Long press on the item to delete it!", Toast.LENGTH_LONG).show();

        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("wholesaler").child(mAuth.getCurrentUser().getUid()).child("ListItems");

        addressEditText = findViewById(R.id.addressEditText);
        itemsCheckList = findViewById(R.id.listView);


        SharedPreferences hashSetValue = getSharedPreferences("hashSet_value", 0);
        ItemsSet = hashSetValue.getStringSet("Final_List", null);

        ItemsSet.remove("Items are: ");

        listItems = new String[ItemsSet.size()];
        ItemsSet.toArray(listItems);


        //rootRef = FirebaseDatabase.getInstance().getReference();

        //demoRef = rootRef.child("Demo");

//        demoRef.setValue(FinalListItems);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        itemsCheckList.setAdapter(arrayAdapter);

        itemsCheckList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                toRemove = arrayAdapter.getItem(position);
                listItems[position] = "Removed";

                ItemsSet.remove(toRemove);
                Log.i("After Seeds Item value", String.valueOf(ItemsSet));

                SharedPreferences hashSetValue2 = getSharedPreferences("hashSet_value", 0);
                SharedPreferences.Editor editor12 = hashSetValue2.edit();
                editor12.putStringSet("Final_List", ItemsSet);
                editor12.commit();

                arrayAdapter.notifyDataSetChanged();



                return true;
            }
        });


        //typeItemsTextView.setText(getIntent().getStringExtra("type"));



        // quantity code
        /*
        Intent i = getIntent();
        String quantity = i.getStringExtra("Quantity");
        quanEditText.setText(quantity);
        quanEditText.setEnabled(false);                 // quanEditText is not further editable
        quanEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));

        // amount code
        int amount = Integer.valueOf(quantity) * 30 * 50;

        String totalAmount = String.valueOf(amount);
        amountEditText.setText(totalAmount);
        amountEditText.setEnabled(false);               // amountEditText is not further editable
        amountEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));

        totalEditText.setText(totalAmount);
        totalEditText.setEnabled(false);                // totalEditText is not further editable
        totalEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));

         */

        SharedPreferences Addname = getSharedPreferences("Address_id", 0);
        String Address_p = Addname.getString("Given_address", null);

        addressEditText.setText(Address_p);
    }

    public void placeOrder(View view) {

        SharedPreferences hashSetValue = getSharedPreferences("hashSet_value", 0);
        Set<String> ItemsSet2 = hashSetValue.getStringSet("Final_List", null);

        SharedPreferences PreviousOrders = getSharedPreferences("Previous_Orders", 0);
        Set<String> MyOrders = PreviousOrders.getStringSet("Previous_Orders_List", null);

        if (!addressEditText.getText().toString().isEmpty() && !ItemsSet2.isEmpty()) {

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
                            MyOrders.addAll(ItemsSet2);
                            Log.i("All Orders", String.valueOf(MyOrders));


                            SharedPreferences PreviousOrders = getSharedPreferences("Previous_Orders", 0);
                            SharedPreferences.Editor editor20 = PreviousOrders.edit();
                            editor20.putStringSet("Previous_Orders_List", MyOrders);
                            editor20.commit();

                            List<String> FinalListItems = new ArrayList<>();

                            FinalListItems.addAll(MyOrders);

                            FinalListItems.remove("Items are: ");

                            reference.push().setValue(FinalListItems);

                            Intent order = new Intent(CartActivity.this, PlaceActivity_wholesaler.class);
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