package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class introScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void screen2(View view){

        Intent intent = new Intent(this, introScreen2.class);

        startActivity(intent);
        finish();

    }

    public void screen4(View view){

        SharedPreferences settings = getSharedPreferences("your_preference_name", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("LoggedIn", true);
        editor.commit();

        Intent intent = new Intent(this, choose_niche.class);

        startActivity(intent);
        finish();

    }
}
