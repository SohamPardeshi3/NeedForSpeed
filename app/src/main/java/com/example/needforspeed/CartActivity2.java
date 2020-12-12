package com.example.needforspeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity2 extends AppCompatActivity {

    EditText quanEditText;
    EditText amountEditText;
    EditText totalEditText;
    EditText rateEditText;
    EditText addressEditText;
    String savedAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        amountEditText = findViewById(R.id.amountEditText);
        rateEditText = findViewById(R.id.rateEditText);
        totalEditText = findViewById(R.id.totalAmountEditText);
        quanEditText = findViewById(R.id.quanEditText);
       // addressEditText = findViewById(R.id.addressEditText);

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

        SharedPreferences Addname = getSharedPreferences("Address_id", 0);
        String Address_p = Addname.getString("Given_address", null);

        TextView textView = findViewById(R.id.textView8);

        textView.setText(Address_p);

        //Intent saveIntent = getIntent();
        //savedAdd = saveIntent.getExtras().getString("MapAdd");

        //Log.i("Adress of helloji", savedAdd);

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          //addressEditText.autofill(AutofillValue.forText(savedAdd));
        //}
    }

    public void placeOrder(View view) {

       // if (!addressEditText.getText().toString().isEmpty()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Are you Sure?")
                    .setMessage("Do you want to place order?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (quanEditText.getText().toString().trim().length() == 0) {           // check whether cart is empty or not
                                Toast.makeText(CartActivity2.this, "You have not added any items!", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent order = new Intent(CartActivity2.this, placeOrderActivity.class);
                                startActivity(order);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("No", null)

                    .show();
       // }else {
           // Toast.makeText(this, "Address not given!", Toast.LENGTH_SHORT).show();
      //  }

    }

    public void nextMap(View view){

        Intent imap = new Intent(this, MapActivity.class);
        startActivity(imap);

    }

}