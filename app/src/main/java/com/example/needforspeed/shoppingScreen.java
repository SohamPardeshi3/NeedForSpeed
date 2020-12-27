package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class shoppingScreen extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

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

                return true;

            case R.id.update:
                Toast.makeText(getApplicationContext(), "You're already up to date",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);

            return true;

            case R.id.LawsFarming:
                Intent intent4=new Intent(Intent.ACTION_VIEW);
                intent4.setData(Uri.parse("http://agricoop.nic.in/acts-and-rules-listing"));
                startActivity(intent4);

                return true;

            case R.id.ModernAgri:
                Intent Webintent=new Intent(this, WebActivity.class);
                //intent.setData(Uri.parse("https://www.plugandplaytechcenter.com/resources/new-agriculture-technology-modern-farming/"));
                startActivity(Webintent);

                return true;

            case R.id.logout:

                SharedPreferences settings1 = getSharedPreferences("your_preference_name1", 0);
                SharedPreferences.Editor editor1 = settings1.edit();
                editor1.putBoolean("LoggedIn1", false);
                editor1.commit();

                FirebaseAuth.getInstance().signOut();

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

        //Seeds List

        Set<String> SeedsItemSet = new HashSet<>();

        SeedsItemSet.add("Items are: ");

        SharedPreferences hashSetValue4 = getSharedPreferences("Seeds_value", 0);
        SharedPreferences.Editor editor14 = hashSetValue4.edit();
        editor14.putStringSet("Seeds_Final_List", SeedsItemSet);
        editor14.commit();

        Set<String> FertItemsSet = new HashSet<>();

        FertItemsSet.add("Items are: ");

        SharedPreferences hashSetValue5 = getSharedPreferences("FerT_hashSet_value", 0);
        SharedPreferences.Editor editor15 = hashSetValue5.edit();
        editor15.putStringSet("Fert_Final_List", FertItemsSet);
        editor15.commit();

        Set<String> EquipItemsSet = new HashSet<>();

        EquipItemsSet.add("Items are: ");

        SharedPreferences hashSetValue6 = getSharedPreferences("Equip_hashSet_value", 0);
        SharedPreferences.Editor editor16 = hashSetValue6.edit();
        editor16.putStringSet("Equip_Final_List", EquipItemsSet);
        editor16.commit();

        Set<String> RentEquipItemsSet = new HashSet<>();

        RentEquipItemsSet.add("Items are: ");

        SharedPreferences hashSetValue21 = getSharedPreferences("Rent_Equip_hashSet_value", 0);
        SharedPreferences.Editor editor21 = hashSetValue21.edit();
        editor21.putStringSet("Rent_Equip_Final_List", RentEquipItemsSet);
        editor21.commit();

    }

    public void sellingScreen(View view) {                                  //onClick = sellingScreen
        Log.i("Button", "Sell button clicked");
        Intent intent = new Intent(this, additems.class);
        startActivity(intent);
    }

    public void buyingScreen(View view) {                                   //onClick = buyingScreen
        Log.i("Button", "Buy button clicked");
        Intent intent = new Intent(this, buyingScreen.class);
        startActivity(intent);
    }

    public void orders(View view) {
        Intent rorders = new Intent(this, items_received_orders.class);
        startActivity(rorders);
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
