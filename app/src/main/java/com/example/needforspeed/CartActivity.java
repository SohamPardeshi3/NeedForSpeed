package com.example.needforspeed;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.trusted.sharing.ShareTarget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    EditText quanEditText;
    EditText amountEditText;
    EditText totalEditText;
    EditText rateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        amountEditText = findViewById(R.id.amountEditText);
        rateEditText = findViewById(R.id.rateEditText);
        totalEditText = findViewById(R.id.totalAmountEditText);
        quanEditText = findViewById(R.id.quanEditText);

        rateEditText.setText("30rs / kg");

        // quantity code
        Intent i = getIntent();
        String quantity = i.getStringExtra("Quantity");
        quanEditText.setText(quantity);
        quanEditText.setEnabled(false);                 // quanEditText is not further editable
        quanEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));

        // amount code
        int amount = Integer.valueOf(quantity) * 30 * 50;

        String totalAmount = String.valueOf(amount);
        amountEditText.setText(totalAmount);
        amountEditText.setEnabled(false);               // amountEditText is not further editable
        amountEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));

        totalEditText.setText(totalAmount);
        totalEditText.setEnabled(false);                // totalEditText is not further editable
        totalEditText.setTextColor(getApplicationContext().getResources().getColor(R.color.browser_actions_text_color));
    }

    public void placeOrder(View view) {
        if (quanEditText.getText().toString().trim().length() == 0) {           // check whether cart is empty or not
            Toast.makeText(this, "You have not added any items!", Toast.LENGTH_SHORT).show();
        } else {
            Intent order = new Intent(this, placeOrderActivity.class);
            startActivity(order);
            finish();
        }
    }
}