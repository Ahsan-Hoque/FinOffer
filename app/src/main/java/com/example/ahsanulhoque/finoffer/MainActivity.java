package com.example.ahsanulhoque.finoffer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.domain.Merchant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;

    DatabaseReference merchantDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        merchantDatabaseReference = FirebaseDatabase.getInstance().getReference("merchants");
        addMerchant();
    }

    void addMerchant() {
        String id = merchantDatabaseReference.push().getKey();
        String brandName = "K Super Market";
        double latitude = 100.00;
        double longitude = 75.00;
        String webLink = "ksupermarket.fi";
        String hotNumber = "+358 123456789";
        String email = "admin@ksupermarket.fi";

        Merchant merchant = new Merchant(id, brandName, latitude, longitude, webLink, hotNumber, email);
        merchantDatabaseReference.child(id).setValue(merchant);

        Toast.makeText(this, "Saved successfully!", Toast.LENGTH_LONG);
    }

}
