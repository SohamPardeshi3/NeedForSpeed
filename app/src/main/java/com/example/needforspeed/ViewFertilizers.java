package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewFertilizers extends AppCompatActivity {

    TextView fromTextView;
    TextView typeTextView;
    TextView quantityTextView;
    TextView rateTextView;
    TextView descTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fertilizers);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fromTextView = findViewById(R.id.fromTextView);
        typeTextView = findViewById(R.id.typeTextView);
        quantityTextView = findViewById(R.id.quantityTextView);
        rateTextView = findViewById(R.id.rateTextView);
        descTextView = findViewById(R.id.descTextView);


            fromTextView.setText(getIntent().getStringExtra("from"));
            typeTextView.setText(getIntent().getStringExtra("type"));
            quantityTextView.setText(getIntent().getStringExtra("quantity"));
            rateTextView.setText(getIntent().getStringExtra("rate"));
            descTextView.setText(getIntent().getStringExtra("description"));

    }
}