package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.net.Inet4Address;

public class shoppingScreen extends AppCompatActivity {

    //shopping screen menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.shopping_screen_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.profile1:
                Intent profile = new Intent(this, profile.class);
                startActivity(profile);
                finish();
                return true;

            case R.id.update:
                Toast.makeText(getApplicationContext(), "You're already up to date",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                finish();
            return true;

            case R.id.help:
                Log.i("help", "help clicked");
                return true;

            case R.id.logout:

                SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
                SharedPreferences.Editor editor1 = settings1.edit();
                editor1.putBoolean("LoggedIn1", false);
                editor1.commit();

                Intent log = new Intent(this, choose_niche.class);
                startActivity(log);
                finish();

            default:
                return false;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        SharedPreferences naam = getSharedPreferences("Name_id", 0);
        String name_p = naam.getString("Given_name", null);


        setTitle("Welcome, "+ name_p);
    }

    public void sellingScreen(View view) {                                  //onClick = sellingScreen
        Log.i("Button", "Sell button clicked");
        Intent intent = new Intent(this, sellingScreen.class);
        startActivity(intent);
    }

    public void buyingScreen(View view) {                                   //onClick = buyingScreen
        Log.i("Button", "Buy button clicked");
        Intent intent = new Intent(this, buyingScreen.class);
        startActivity(intent);
    }
}
