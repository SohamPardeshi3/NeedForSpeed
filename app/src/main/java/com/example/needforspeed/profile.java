package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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

    }

    public void save(View view) {

        if (nameEditText.getText().toString().trim().length() == 0 && numberEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your name and phone number", Toast.LENGTH_SHORT).show();

        } else if (nameEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

        } else if (numberEditText.getText().toString().trim().length() == 0) {

            Toast.makeText(this, "Please enter your number", Toast.LENGTH_SHORT).show();

        } else {

           Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();

        }
    }

    public void logout(View view) {
        // logout code
        Intent logout = new Intent(this,shoppingScreen.class);
        startActivity(logout);
        finish();
    }

}