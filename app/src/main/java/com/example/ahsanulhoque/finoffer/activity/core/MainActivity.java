package com.example.ahsanulhoque.finoffer.activity.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.Merchant;
import com.example.ahsanulhoque.finoffer.domain.Product;
import com.example.ahsanulhoque.finoffer.service.ProductService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ProductService productService;

    EditText productName;
    EditText productDescription;
    EditText regularPrice;
    EditText discountIn;
    EditText editTextPrice;
    EditText location;

    ImageView imageView;

    Button buttonAddImage;
    Button buttonAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productService = new ProductService();

        productName = (EditText) findViewById(R.id.productName);
        productDescription = (EditText) findViewById(R.id.productDescription);
        regularPrice = (EditText) findViewById(R.id.regularPrice);
        discountIn = (EditText) findViewById(R.id.discountIn);
        editTextPrice = (EditText) findViewById(R.id.price);
        location = (EditText) findViewById(R.id.location);

        imageView = null;
        buttonAddImage = null;

        buttonAddProduct = (Button) findViewById(R.id.addProduct);

        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

    }
    
    void addProduct() {
        String merchantId = null;
        String name = productName.getText().toString().trim();
        double price = Double.parseDouble(regularPrice.getText().toString().trim());
        String description = productDescription.getText().toString().trim();
        double discountRate = Double.parseDouble(discountIn.getText().toString().trim());
        double regularPrc = Double.parseDouble(regularPrice.getText().toString().trim());
        String items = description;
        String localStore = location.getText().toString();
        String productTypeId = null;
        Product product = new Product(merchantId, name, price, description, discountRate, regularPrc, items, localStore, productTypeId);

        boolean isProductAdded = productService.isProductAdded(product);

        if (isProductAdded) {
            Toast.makeText(this, "Added Successfully!", Toast.LENGTH_LONG);

            productName.setText("");
            productDescription.setText("");
            regularPrice.setText("");
            discountIn.setText("");
            editTextPrice.setText("");
            location.setText("");
        } else {
            Toast.makeText(this, "Unable To Add!", Toast.LENGTH_LONG);
        }
    }

}
