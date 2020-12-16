package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sellrent_equip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellrent_equip);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public void sellEquipments(View view) {
        Intent sellEquip = new Intent(this, wholesaler_sell_equip.class);
        startActivity(sellEquip);
    }

    public void rentEquipments(View view) {

    }

}