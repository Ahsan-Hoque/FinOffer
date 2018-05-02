package com.example.ahsanulhoque.finoffer.activity.authentication;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class SingOut extends AppCompatActivity {

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

}
