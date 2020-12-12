package com.example.needforspeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class wholesaler_otp_login extends AppCompatActivity {
    EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6, inputMobile, name;

    String verificationCode, cd;

    Button genOtp, verifyOtp, next;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        inputMobile = findViewById(R.id.editText);
        name = findViewById(R.id.editText3);

        genOtp = findViewById(R.id.button2);
        verifyOtp = findViewById(R.id.button3);
        next = findViewById(R.id.button);

        next.setEnabled(false);
        next.setBackgroundColor(Color.GRAY);

        inputcode1 = findViewById(R.id.editText8);
        inputcode2 = findViewById(R.id.editText9);
        inputcode3 = findViewById(R.id.editText10);
        inputcode4 = findViewById(R.id.editText11);
        inputcode5 = findViewById(R.id.editText12);
        inputcode6 = findViewById(R.id.editText13);

        auth = FirebaseAuth.getInstance();

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                verificationCode = s;
                Toast.makeText(wholesaler_otp_login.this, "Code Sent to the number!", Toast.LENGTH_SHORT).show();
            }
        };

        setUpotp();

    }

    public void sms(View view){

        String numb = inputMobile.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+numb,60,TimeUnit.SECONDS,this,mCallback
        );

    }

    public void signIn(PhoneAuthCredential credential){

        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(wholesaler_otp_login.this, "Correct Otp", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(wholesaler_otp_login.this, "Wrong Otp, please enter correct otp!", Toast.LENGTH_SHORT).show();
                        }
                    }


                });

    }


    public void veri(View view){

        if (inputcode1.getText().toString().trim().isEmpty()
                || inputcode2.getText().toString().trim().isEmpty()
                || inputcode3.getText().toString().trim().isEmpty()
                || inputcode4.getText().toString().trim().isEmpty()
                || inputcode5.getText().toString().trim().isEmpty()
                || inputcode6.getText().toString().trim().isEmpty()
        ){
            Toast.makeText(wholesaler_otp_login.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
            return;
        }else {

            next.setBackgroundColor(Color.GREEN);
            next.setEnabled(true);


            cd = inputcode1.getText().toString() +
                    inputcode2.getText().toString() +
                    inputcode3.getText().toString() +
                    inputcode4.getText().toString() +
                    inputcode5.getText().toString() +
                    inputcode6.getText().toString() ;
        }


        verifyphonenumber(verificationCode, cd);



    }

    public void verifyphonenumber(String verification_code, String input_code){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code, input_code);
        signIn(credential);

    }



    public void setUpotp(){

        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){

                    inputcode2.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){

                    inputcode3.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){

                    inputcode4.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){

                    inputcode5.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){

                    inputcode6.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()){

                    inputcode5.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()){

                    inputcode4.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()){

                    inputcode3.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()){

                    inputcode2.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()){

                    inputcode1.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void next(View view){



        String n1 = name.getText().toString();
        // String n2 = inputMobile.getText().toString();

        /*
        SharedPreferences number = getSharedPreferences("Number_id", 0);
        SharedPreferences.Editor editor3 = number.edit();
        editor3.putString("Given_number", n2);
        editor3.commit();

//        SharedPreferences name1 = getSharedPreferences("Name_id1", 0);
//        SharedPreferences.Editor editor4 = name1.edit();
//        editor4.putString("Given_name1", n2);
//        editor4.commit();

         */


        if (!n1.isEmpty()) {

            SharedPreferences settings8 = getSharedPreferences("your_preference_name8", 0);
            SharedPreferences.Editor editor8 = settings8.edit();
            editor8.putBoolean("LoggedIn8", true);
            editor8.commit();

            SharedPreferences naam = getSharedPreferences("Name_id", 0);
            SharedPreferences.Editor editor2 = naam.edit();
            editor2.putString("Given_name", n1);
            editor2.commit();

            Toast.makeText(this, n1 + " you've successfully logged in!", Toast.LENGTH_SHORT).show();

            Intent intent8 = new Intent(this, wholesaler_shopping_screen.class);
            startActivity(intent8);
            finish();

        }else {

            Toast.makeText(this, "Please enter a Name!", Toast.LENGTH_SHORT).show();

        }

    }
}
