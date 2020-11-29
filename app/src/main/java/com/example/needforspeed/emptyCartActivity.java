package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class emptyCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public void addItems(View view) {
        Intent seeds = new Intent(this, seedsActivity.class);
        startActivity(seeds);
        finish();
    }


}