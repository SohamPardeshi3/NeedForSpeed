package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    ListView list;
    String quant, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        list = findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();

        Bundle extra =  getIntent().getExtras();

        if(extra !=null) {
            String value = extra.getString("Value");
            arrayList.add(value);
            quant = extra.getString("Quantity");
            rate = extra.getString("Rate");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(list.this, "Quantity "+ quant + " at price: " + rate, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
