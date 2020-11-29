package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sellingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling_screen);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void next(View view){

        Intent intent = new Intent(this, additems.class);
        startActivity(intent);

    }
}