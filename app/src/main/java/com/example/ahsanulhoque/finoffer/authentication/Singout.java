package com.example.ahsanulhoque.finoffer.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.landingpage.MainFinOffer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Singout extends AppCompatActivity {

    private EditText EmailET;
    private EditText PassET;

    /*private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }*/

    private void updateUI(FirebaseUser user) {
        EmailET = (EditText) findViewById(R.id.EmailET);
        PassET = (EditText) findViewById(R.id.PassET);

        EmailET.setText("");
        PassET.setText("");

        Intent intentMain = new Intent(Singout.this, Login.class);
        startActivity(intentMain);
    }

    private void signOut() {
        //firebaseAuth.signOut();
        FirebaseAuth.getInstance().signOut();
        updateUI(null);
    }

}