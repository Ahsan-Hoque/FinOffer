package com.example.ahsanulhoque.finoffer.activity.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.Product;

public class ProductDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Product product = (Product) getIntent().getSerializableExtra("product");
    }
}
