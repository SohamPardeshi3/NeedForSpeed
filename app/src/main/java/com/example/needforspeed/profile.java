package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profile extends AppCompatActivity {

    EditText nameEditText;
    EditText numberEditText;
    String profileName;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        nameEditText = findViewById(R.id.nameEditText);
        numberEditText = findViewById(R.id.numberEditText);


//        SharedPreferences name1 = getSharedPreferences("Name_id1", 0);
//        String name_p = name1.getString("Given_number", null);

//        SharedPreferences number = getSharedPreferences("Number_id", 0);
//        String number_p = number.getString("Given_name1", null);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            nameEditText.autofill(AutofillValue.forText(name_p));
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            numberEditText.autofill(AutofillValue.forText(number_p));
//        }

        //SharedPreferences name1 = getSharedPreferences("Name_id1", 0);
        //String name_p = name1.getString("Given_number", null);

        //SharedPreferences number = getSharedPreferences("Number_id", 0);
        //String number_p = number.getString("Given_name1", null);

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         //   nameEditText.autofill(AutofillValue.forText(name_p));
        //}

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // numberEditText.autofill(AutofillValue.forText(number_p));
       // }



    }

    public void save(View view) {

        if (nameEditText.getText().toString().trim().length() == 0 && numberEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your name and phone number", Toast.LENGTH_SHORT).show();

        } else if (nameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

        } else if (numberEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your number", Toast.LENGTH_SHORT).show();

        } else {

            String name = nameEditText.getText().toString();

            SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
            SharedPreferences.Editor editor1 = settings1.edit();
            editor1.putBoolean("LoggedIn1", true);
            editor1.commit();

            SharedPreferences naam = getSharedPreferences("Name_id", 0);
            SharedPreferences.Editor editor2 = naam.edit();
            editor2.putString("Given_name", name);
            editor2.commit();

           Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();

           Intent intent = new Intent(this, shoppingScreen.class);
           startActivity(intent);
           finish();

        }
    }

    public void logout(View view) {
        // logout code

        SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
        SharedPreferences.Editor editor1 = settings1.edit();
        editor1.putBoolean("LoggedIn1", false);
        editor1.commit();

        Intent logout = new Intent(this,choose_niche.class);
        startActivity(logout);
        finish();
    }

}