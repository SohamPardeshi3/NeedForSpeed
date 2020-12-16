package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewEquipments extends AppCompatActivity {

    TextView fromEquipTextView;
    TextView typeEquipTextView;
    TextView quantityEquipTextView;
    TextView rateEquipTextView;
    TextView descEquipTextView;

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

        fromEquipTextView.setText(getIntent().getStringExtra("from"));
        typeEquipTextView.setText(getIntent().getStringExtra("type"));
        quantityEquipTextView.setText(getIntent().getStringExtra("quantity"));
        rateEquipTextView.setText(getIntent().getStringExtra("rate"));
        descEquipTextView.setText(getIntent().getStringExtra("description"));
    }
}