package com.example.needforspeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choose_niche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_niche);
    }

    public void Farmer(View view){

        Intent intent = new Intent(this, otp_login.class);
        startActivity(intent);

    }
}