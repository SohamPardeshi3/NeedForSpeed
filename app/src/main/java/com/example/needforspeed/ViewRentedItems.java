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

import java.util.HashSet;
import java.util.Set;

public class ViewRentedItems extends AppCompatActivity {

    TextView fromEquipTextView;
    TextView itemEquipTextView;
    //TextView quantityEquipTextView;
    TextView rateEquipTextView;
    //TextView descEquipTextView;

    EditText editTextNumber;
    String value, checkvalue;
    int val1,val2;

    Set<String> set = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rented_items);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fromEquipTextView = findViewById(R.id.fromEquipTextView);
        itemEquipTextView = findViewById(R.id.quantityEquipTextView);
        //quantityEquipTextView = findViewById(R.id.quantityEquipTextView);
        rateEquipTextView = findViewById(R.id.rateEquipTextView);
       // descEquipTextView = findViewById(R.id.descEquipTextView);


        editTextNumber = findViewById(R.id.EnterQunatityeditTextNumber);

        //value = getIntent().getStringExtra("quantity");
        //val1 = Integer.valueOf(value);

        fromEquipTextView.setText(getIntent().getStringExtra("from"));
        itemEquipTextView.setText(getIntent().getStringExtra("type"));
        //quantityEquipTextView.setText(getIntent().getStringExtra("quantity"));
        rateEquipTextView.setText(getIntent().getStringExtra("rate"));
        //descEquipTextView.setText(getIntent().getStringExtra("description"));

    }


    public void addToCart(View view){

        SharedPreferences hashSetValue = getSharedPreferences("Rent_Equip_hashSet_value", 0);
        Set<String>  RentEquipItemSet = hashSetValue.getStringSet("Rent_Equip_Final_List", null);

        Log.i("Item Set Value", String.valueOf(RentEquipItemSet));

        checkvalue = editTextNumber.getText().toString();

        //Log.i("Value of val1", String.valueOf(val1));
        //Log.i("Value of val2", String.valueOf(val2));


        val2 = Integer.valueOf(checkvalue);


        if (val2 <= 140) {

            set.addAll(RentEquipItemSet);

            //Collections.reverse(asList);
            //Log.i("Reversed List Value", String.valueOf(asList));

            String finalItem;

            finalItem = "Item: " + getIntent().getStringExtra("type") + "  Number of Days: " + editTextNumber.getText().toString() + "  Rs: " + getIntent().getStringExtra("rate");

            Log.i("Type of XYZ", String.valueOf(finalItem));



            set.add(finalItem);









            Log.i("HashSet Value", String.valueOf(set));

            SharedPreferences hashSetValue21 = getSharedPreferences("Rent_Equip_hashSet_value", 0);
            SharedPreferences.Editor editor21 = hashSetValue21.edit();
            editor21.putStringSet("Rent_Equip_Final_List", set);
            editor21.commit();




            Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Enter Valid amount of days!", Toast.LENGTH_SHORT).show();

        }



    }


    public void DoneRef(View view){


        Intent intent = new Intent(this, farmer_rent_equipments.class);
        startActivity(intent);

    }


}