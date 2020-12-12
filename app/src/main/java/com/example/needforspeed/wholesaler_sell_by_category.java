package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class wholesaler_sell_by_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_sell_by_category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void sellFert(View view) {
        Intent sellFert = new Intent(this, wholesaler_sell_items.class);
        startActivity(sellFert);
    }
}