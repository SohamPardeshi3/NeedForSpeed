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

public class ViewEquipments extends AppCompatActivity {

    TextView fromEquipTextView;
    TextView typeEquipTextView;
    TextView quantityEquipTextView;
    TextView rateEquipTextView;
    TextView descEquipTextView;

    EditText editTextNumber;
    String value, checkvalue;
    int val1,val2;

    Set<String> set = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_equipments);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fromEquipTextView = findViewById(R.id.fromEquipTextView);
        typeEquipTextView = findViewById(R.id.typeEquipTextView);
        quantityEquipTextView = findViewById(R.id.quantityEquipTextView);
        rateEquipTextView = findViewById(R.id.rateEquipTextView);
        descEquipTextView = findViewById(R.id.descEquipTextView);

        editTextNumber = findViewById(R.id.EnterQunatityeditTextNumber);

        value = getIntent().getStringExtra("quantity");
        val1 = Integer.valueOf(value);


        fromEquipTextView.setText(getIntent().getStringExtra("from"));
        typeEquipTextView.setText(getIntent().getStringExtra("type"));
        quantityEquipTextView.setText(getIntent().getStringExtra("quantity"));
        rateEquipTextView.setText(getIntent().getStringExtra("rate"));
        descEquipTextView.setText(getIntent().getStringExtra("description"));
    }


    public void addToCart(View view){

        SharedPreferences hashSetValue = getSharedPreferences("Equip_hashSet_value", 0);
        Set<String>  EquipItemSet = hashSetValue.getStringSet("Equip_Final_List", null);

        Log.i("Item Set Value", String.valueOf(EquipItemSet));

        checkvalue = editTextNumber.getText().toString();

        Log.i("Value of val1", String.valueOf(val1));
        Log.i("Value of val2", String.valueOf(val2));


        val2 = Integer.valueOf(checkvalue);


        if (val2 <= val1) {

            set.addAll(EquipItemSet);

            //Collections.reverse(asList);
            //Log.i("Reversed List Value", String.valueOf(asList));

            String finalItem;

            finalItem = "Item: " + getIntent().getStringExtra("type") + "  Quantity: " + editTextNumber.getText().toString() + "  Rs: " + getIntent().getStringExtra("rate");

            Log.i("Type of XYZ", String.valueOf(finalItem));



            set.add(finalItem);









            Log.i("HashSet Value", String.valueOf(set));

            SharedPreferences hashSetValue6 = getSharedPreferences("Equip_hashSet_value", 0);
            SharedPreferences.Editor editor16 = hashSetValue6.edit();
            editor16.putStringSet("Equip_Final_List", set);
            editor16.commit();




            Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Enter less quantity!", Toast.LENGTH_SHORT).show();

        }



    }

    public void DoneRef(View view){


        Intent intent = new Intent(this, farmer_buy_equipments.class);
        startActivity(intent);

    }

}