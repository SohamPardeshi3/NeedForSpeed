package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PreviousOrders_WHolesaler extends AppCompatActivity {



    TextView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders__w_holesaler);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

       listView = findViewById(R.id.textView16);



    }

    public void GetItems(View view) {



/*
        SharedPreferences PreviousOrders = getSharedPreferences("Previous_Orders", 0);
        Set<String> MyOrders = PreviousOrders.getStringSet("Previous_Orders_List", null);

        MyOrders.remove("Items are: ");

        Log.i("Previous Orders: ", String.valueOf(MyOrders));

        String[] listItems = new String[MyOrders.size()];
        MyOrders.toArray(listItems);

 */

    }

}