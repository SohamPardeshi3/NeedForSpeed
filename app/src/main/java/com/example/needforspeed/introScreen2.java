package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class introScreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void screen3(View view){

        Intent intent = new Intent(this, introScreen3.class);
        startActivity(intent);
        finish();


    }
}
