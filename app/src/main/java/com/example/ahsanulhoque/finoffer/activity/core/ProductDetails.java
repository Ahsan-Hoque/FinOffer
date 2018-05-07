package com.example.ahsanulhoque.finoffer.activity.core;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.Product;

public class ProductDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        //statusbar color
        updateStatusBarColor("#EF6C00");

        Product product = (Product) getIntent().getSerializableExtra("product");
    }

    public void updateStatusBarColor(String color) {
        // Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}
