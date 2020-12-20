package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Cart_Activity_Farmer_Fertilizer extends AppCompatActivity {

    EditText addressEditText;
    String savedAdd;
    ListView itemsCheckList;
    Set<String> FertItemsSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart___farmer__fertilizer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        addressEditText = findViewById(R.id.addressEditText);
        itemsCheckList = findViewById(R.id.listView);



        SharedPreferences hashSetValue5 = getSharedPreferences("FerT_hashSet_value", 0);
        FertItemsSet = hashSetValue5.getStringSet("Fert_Final_List", null);

        FertItemsSet.remove("Items are: ");

        String[] listItems = new String[FertItemsSet.size()];
        FertItemsSet.toArray(listItems);

        List<String> FinalListItems = Arrays.asList(listItems);
        Collections.reverse(FinalListItems);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FinalListItems);

        itemsCheckList.setAdapter(arrayAdapter);

        SharedPreferences Addname = getSharedPreferences("Address_id", 0);
        String Address_p = Addname.getString("Given_address", null);

        addressEditText.setText(Address_p);
    }

    public void placeOrder(View view) {

        if (!addressEditText.getText().toString().isEmpty()) {

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
                            Intent order = new Intent(Cart_Activity_Farmer_Fertilizer.this, placeOrderActivity.class);
                            startActivity(order);

                        }
                    })
                    .setNegativeButton("No", null)

                    .show();
        }else {
            Toast.makeText(this, "Address not given!", Toast.LENGTH_SHORT).show();
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