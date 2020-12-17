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
        Intent sellFert = new Intent(this, wholesaler_sell_items.class);              // wholesaler sell fertilizers
        startActivity(sellFert);
    }

    public void sellSeeds(View view) {
        Intent sellSeed = new Intent(this, wholesaler_sell_seeds.class);              // wholesaler sell seeds
        startActivity(sellSeed);
    }

    public void Equip(View view) {
        Intent equipments = new Intent(this, sellrent_equip.class);
        startActivity(equipments);
    }


}