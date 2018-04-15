package com.example.ahsanulhoque.finoffer.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.MainActivity;
import com.example.ahsanulhoque.finoffer.R;

public class Login extends AppCompatActivity {
    TextView SignUPTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SignUPTV = (TextView) findViewById(R.id.SignUPTV);
        SignUPTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

    }
}
