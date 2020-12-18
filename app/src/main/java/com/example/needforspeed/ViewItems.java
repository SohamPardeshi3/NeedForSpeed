package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewItems extends AppCompatActivity {

    TextView fromItemsTextView, typeItemsTextView, quantityItemsTextView, rateItemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fromItemsTextView = findViewById(R.id.fromItemsTextView);
        typeItemsTextView = findViewById(R.id.typeItemsTextView);
        quantityItemsTextView = findViewById(R.id.quantityItemsTextView);
        rateItemsTextView = findViewById(R.id.rateItemsTextView);

        fromItemsTextView.setText(getIntent().getStringExtra("from"));
        typeItemsTextView.setText(getIntent().getStringExtra("type"));
        quantityItemsTextView.setText(getIntent().getStringExtra("quantity"));
        rateItemsTextView.setText(getIntent().getStringExtra("rate"));
    }
}