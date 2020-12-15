package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewSeeds extends AppCompatActivity {

    TextView fromSeedTextView;
    TextView typeSeedTextView;
    TextView quantitySeedTextView;
    TextView rateSeedTextView;
    TextView descSeedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fromSeedTextView = findViewById(R.id.fromSeedTextView);
        typeSeedTextView = findViewById(R.id.typeSeedTextView);
        quantitySeedTextView = findViewById(R.id.quantitySeedTextView);
        rateSeedTextView = findViewById(R.id.rateSeedTextView);
        descSeedTextView = findViewById(R.id.descSeedTextView);

        fromSeedTextView.setText(getIntent().getStringExtra("from"));
        typeSeedTextView.setText(getIntent().getStringExtra("type"));
        quantitySeedTextView.setText(getIntent().getStringExtra("quantity"));
        rateSeedTextView.setText(getIntent().getStringExtra("rate"));
        descSeedTextView.setText(getIntent().getStringExtra("description"));
    }
}