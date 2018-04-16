package com.example.ahsanulhoque.finoffer.authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.MainActivity;
import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.landingpage.MainFinOffer;

public class Login extends AppCompatActivity {
    TextView SignUPTV;
    Button LogBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();
        SignUPTV = (TextView) findViewById(R.id.SignUPTV);
        LogBTN = (Button) findViewById(R.id.LogBTN);
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
                Intent intentMain = new Intent(Login.this, MainFinOffer.class);
                startActivity(intentMain);
            }
        });


    }

}
