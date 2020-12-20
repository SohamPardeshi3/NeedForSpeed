package com.example.needforspeed;

import androidx.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashSet;
import java.util.Set;

public class wholesaler_shopping_screen extends AppCompatActivity {

    // wholesaler shopping screen menu
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

                return true;

            case R.id.update:
                Toast.makeText(getApplicationContext(), "You're already up to date",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);

                return true;

            case R.id.help:
                Log.i("help", "help clicked");
                return true;

            case R.id.logout:

                SharedPreferences settings8 = getSharedPreferences("your_preference_name8", 0);
                SharedPreferences.Editor editor8 = settings8.edit();
                editor8.putBoolean("LoggedIn8", false);
                editor8.commit();

                FirebaseAuth.getInstance().signOut();

                Intent log = new Intent(this, choose_niche.class);
                startActivity(log);
                finish();

            default:
                return false;
        }

    }

    public void sellItems(View view) {
        Intent sell = new Intent(this, wholesaler_sell_by_category.class);
        startActivity(sell);
    }

    public void wholesaleBuy(View view) {
        Intent buy = new Intent(this, wholesaler_buy_items.class);
        startActivity(buy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_shopping_screen);

        SharedPreferences naam = getSharedPreferences("Name_id1", 0);
        String name_p = naam.getString("Given_name1", null);


        setTitle("Welcome, "+ name_p);

        Set<String> ItemsSet = new HashSet<>();

        ItemsSet.add("Items are: ");

        SharedPreferences hashSetValue2 = getSharedPreferences("hashSet_value", 0);
        SharedPreferences.Editor editor12 = hashSetValue2.edit();
        editor12.putStringSet("Final_List", ItemsSet);
        editor12.commit();



    }
}