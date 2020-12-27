package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewSeeds extends AppCompatActivity {

    TextView fromSeedTextView;
    TextView typeSeedTextView;
    TextView quantitySeedTextView;
    TextView rateSeedTextView;
    TextView descSeedTextView;

    EditText editTextNumber;
    String value, checkvalue;
    int val1,val2;

    Set<String> set = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        fromSeedTextView = findViewById(R.id.fromSeedTextView);
        typeSeedTextView = findViewById(R.id.typeSeedTextView);
        quantitySeedTextView = findViewById(R.id.quantitySeedTextView);
        rateSeedTextView = findViewById(R.id.rateSeedTextView);
        descSeedTextView = findViewById(R.id.descSeedTextView);

        editTextNumber = findViewById(R.id.EnterQunatityeditTextNumber);

        value = getIntent().getStringExtra("quantity");
        val1 = Integer.valueOf(value);

        fromSeedTextView.setText(getIntent().getStringExtra("from"));
        typeSeedTextView.setText(getIntent().getStringExtra("type"));
        quantitySeedTextView.setText(getIntent().getStringExtra("quantity"));
        rateSeedTextView.setText(getIntent().getStringExtra("rate"));
        descSeedTextView.setText(getIntent().getStringExtra("description"));
    }


    public void addToCart(View view){

        SharedPreferences hashSetValue = getSharedPreferences("Seeds_value", 0);
        Set<String>  SeedsItemSet = hashSetValue.getStringSet("Seeds_Final_List", null);

        Log.i("Item Set Value", String.valueOf(SeedsItemSet));

        checkvalue = editTextNumber.getText().toString();

        Log.i("Value of val1", String.valueOf(val1));
        Log.i("Value of val2", String.valueOf(val2));


        val2 = Integer.valueOf(checkvalue);


        if (checkvalue.isEmpty()){
            Toast.makeText(this, "Please enter quantity!", Toast.LENGTH_SHORT).show();
        }else {

            if (val2 <= val1) {

                set.addAll(SeedsItemSet);

                //Collections.reverse(asList);
                //Log.i("Reversed List Value", String.valueOf(asList));

                String finalItem;

                finalItem = "Item: " + getIntent().getStringExtra("type") + "  Quantity: " + editTextNumber.getText().toString() + "  Rs: " + getIntent().getStringExtra("rate");

                Log.i("Type of XYZ", String.valueOf(finalItem));


                set.add(finalItem);


                Log.i("HashSet Value", String.valueOf(set));

                SharedPreferences hashSetValue4 = getSharedPreferences("Seeds_value", 0);
                SharedPreferences.Editor editor14 = hashSetValue4.edit();
                editor14.putStringSet("Seeds_Final_List", set);
                editor14.commit();


                Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Enter less quantity!", Toast.LENGTH_SHORT).show();

            }

        }

    }



    public void DoneRef(View view){


        Intent intent = new Intent(this, farmer_buy_seeds.class);
        startActivity(intent);

    }

}