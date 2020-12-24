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
import android.widget.SearchView;
import android.widget.Toast;

public class buyingScreen extends AppCompatActivity {

    SearchView buyingSearchView;

    //buying screen menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.buying_screen_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.profile:
                Log.i("profile","Profile clicked");
                Intent profile = new Intent(this, profile.class);
                startActivity(profile);

                return true;

            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);

                return true;

            case R.id.help:
                Log.i("help", "help clicked");
                return true;

            case R.id.logout1:

                SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
                SharedPreferences.Editor editor1 = settings1.edit();
                editor1.putBoolean("LoggedIn1", false);
                editor1.commit();

                Intent logo = new Intent(this, choose_niche.class);
                startActivity(logo);
                finish();

            default:
                return false;
        }

    }

    public void seeds(View view) {
        Intent seeds = new Intent(this, farmer_buy_seeds.class);
        startActivity(seeds);
    }

    public void buyFertilizers(View view) {
        Intent buyFertilizers = new Intent(this, farmer_buy_fertilizers.class);
        startActivity(buyFertilizers);
    }

    public void buyEquip(View view) {
        Intent buyrentEquipments = new Intent(this, farmer_buyrent_equip.class);
        startActivity(buyrentEquipments);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_screen);

        buyingSearchView = findViewById(R.id.buyingSearchView);
        // search code

        buyingSearchView.setIconified(false);
        buyingSearchView.clearFocus();
    }
}