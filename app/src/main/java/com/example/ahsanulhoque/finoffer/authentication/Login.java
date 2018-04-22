package com.example.ahsanulhoque.finoffer.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.ProductDetails;
import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.landingpage.MainFinOffer;


public class Login extends AppCompatActivity {
    TextView SignUPTV;
    TextView ForgotPassTV;
    Button LogBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        updateStatusBarColor("#FFFFFF");

        getSupportActionBar().hide();

        SignUPTV = (TextView) findViewById(R.id.SignUPTV);
        ForgotPassTV = (TextView) findViewById(R.id.ForgotPassTV);
        LogBTN = (Button) findViewById(R.id.LogBTN);

        SignUPTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }

        });

        ForgotPassTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ProductDetails.class);
                startActivity(intent);
            }
        });

        LogBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Login.this, MainFinOffer.class);
                startActivity(intentMain);
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

}
