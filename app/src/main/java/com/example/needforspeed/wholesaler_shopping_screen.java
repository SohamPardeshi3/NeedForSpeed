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

import java.net.Inet4Address;
import java.util.HashSet;
import java.util.Set;

public class wholesaler_shopping_screen extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
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

            case R.id.myOrder:
                Intent myOrderIntent = new Intent(this, PreviousOrders_WHolesaler.class);
                startActivity(myOrderIntent);

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

    public void receivedOrders(View view) {
        Intent receivedorders = new Intent(this, fert_received_orders.class);
        startActivity(receivedorders);
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




        Set<String> MyOrders = new HashSet<>();
        MyOrders.add("Items are: ");

        Log.i("May order value", String.valueOf(MyOrders));

        SharedPreferences PreviousOrders = getSharedPreferences("Previous_Orders", 0);
        SharedPreferences.Editor editor20 = PreviousOrders.edit();
        editor20.putStringSet("Previous_Orders_List", MyOrders);
        editor20.apply();


    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

}