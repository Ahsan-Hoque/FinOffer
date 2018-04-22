package com.example.ahsanulhoque.finoffer.authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.landingpage.MainFinOffer;
import com.example.ahsanulhoque.finoffer.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText EmailET;
    private EditText PassET;

    private TextView ForgotPassTV;
    private TextView SignUPTV;
    private Button LogBTN;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();

        updateStatusBarColor("#FFFFFF");

        EmailET = (EditText) findViewById(R.id.EmailET);
        PassET = (EditText) findViewById(R.id.PassET);

        SignUPTV = (TextView) findViewById(R.id.SignUPTV);
        LogBTN = (Button) findViewById(R.id.LogBTN);
        ForgotPassTV = (TextView) findViewById(R.id.ForgotPassTV);

        SignUPTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }

        });

        LogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intentMain = new Intent(Login.this, MainFinOffer.class);
                startActivity(intentMain);*/
                signIn(EmailET.getText().toString().trim(), PassET.getText().toString().trim());
            }
        });

        ForgotPassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intentMain = new Intent(Login.this, MainFinOffer.class);
                startActivity(intentMain);*/
            }
        });


    }

    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));

        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = EmailET.getText().toString();
        if (TextUtils.isEmpty(email)) {
            EmailET.setError("Required!");
            valid = false;
        } else if (!Utils.isEmailValid(email)) {
            EmailET.setError("Enter a valid email!");
            valid = false;
        } else {
            EmailET.setError(null);
        }

        String password = PassET.getText().toString();
        if (TextUtils.isEmpty(password)) {
            PassET.setError("Required!");
            valid = false;
        } else if (!Utils.isPasswordValid(password)) {
            PassET.setError("At least 6 characters required!");
            valid = false;
        } else {
            PassET.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // redirect to landing page
            Intent intentMain = new Intent(Login.this, MainFinOffer.class);
            startActivity(intentMain);
        } else {
            EmailET.setVisibility(View.VISIBLE);
            PassET.setVisibility(View.VISIBLE);

            LogBTN.setVisibility(View.VISIBLE);
        }
    }

    private void signIn(String email, String password) {

        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            System.out.println("----------------------------------------------------------------------------------------------");
                            System.out.println(user.getDisplayName());
                            System.out.println(user.getEmail());
                            System.out.println(user.getPhotoUrl());
                            System.out.println(user.getUid());
                            System.out.println(user.getPhoneNumber());
                            System.out.println("----------------------------------------------------------------------------------------------");
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        firebaseAuth.signOut();
        updateUI(null);
    }

}
