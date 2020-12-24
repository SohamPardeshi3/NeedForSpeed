package com.example.needforspeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlaceActivity_wholesaler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_wholesaler);
    }

    public void homeScreen(View view){

        Intent home = new Intent(this, wholesaler_shopping_screen.class);
        startActivity(home);

    }


}