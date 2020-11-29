package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

public class seedsActivity extends AppCompatActivity {

    SearchView buyingSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeds);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        buyingSearchView = findViewById(R.id.buyingSearchView);

        buyingSearchView.setIconified(false);
        buyingSearchView.clearFocus();
    }

    public void cart(View view) {
        Intent emptyCart = new Intent(this, emptyCartActivity.class);
        startActivity(emptyCart);
    }

    public void cotton(View view) {
        Intent cotton = new Intent(this, cottonSeedsActivity.class);
        startActivity(cotton);
    }

}
