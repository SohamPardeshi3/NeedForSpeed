package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this, introScreen.class);
                Intent trueIntent = new Intent(MainActivity.this, choose_niche.class);
                Intent nextIntent = new Intent(MainActivity.this, shoppingScreen.class);
                Intent wholesaler_intent = new Intent(MainActivity.this, wholesaler_shopping_screen.class);

                SharedPreferences settings8 = getSharedPreferences("your_preference_name8", 0);
                boolean isLoggedIn8 = settings8.getBoolean("LoggedIn8", false);


                SharedPreferences settings = getSharedPreferences("your_preference_name", 0);
                boolean isLoggedIn = settings.getBoolean("LoggedIn", false);

                SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
                boolean isLoggedIn1 = settings1.getBoolean("LoggedIn1", false);

                if (isLoggedIn8){
                    MainActivity.this.startActivity(wholesaler_intent);
                    MainActivity.this.finish();
                }else {

                    if (isLoggedIn1) {
                        MainActivity.this.startActivity(nextIntent);
                        MainActivity.this.finish();
                    } else {

                        if (isLoggedIn) {
                            //Go directly to Homescreen.
                            MainActivity.this.startActivity(trueIntent);
                            MainActivity.this.finish();
                        } else {

                            MainActivity.this.startActivity(mainIntent);
                            MainActivity.this.finish();

                        }
                    }
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }



}
