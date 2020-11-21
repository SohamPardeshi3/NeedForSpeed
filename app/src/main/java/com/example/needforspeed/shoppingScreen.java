package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class shoppingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void sellingScreen(View view) {                                  //onClick = sellingScreen
        Log.i("Button", "Sell button clicked");
    }

    public void buyingScreen(View view) {                                   //onClick = buyingScreen
        Log.i("Button", "Buy button clicked");
    }
}
