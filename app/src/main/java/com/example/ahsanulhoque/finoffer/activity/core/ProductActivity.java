package com.example.ahsanulhoque.finoffer.activity.core;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.activity.authentication.LogIn;
import com.example.ahsanulhoque.finoffer.activity.authentication.SingOut;
import com.example.ahsanulhoque.finoffer.domain.Product;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductActivity extends AppCompatActivity {

    // drawer fields
    private Toolbar lTopToolbar;
    private DrawerLayout pDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    // add product fields
    private EditText productName;
    private EditText productDescription;
    private EditText regularPrice;
    private EditText discountIn;
    private EditText editTextPrice;
    private EditText location;
    private ImageView imageView;
    private Button buttonAddImage;
    private Button buttonAddProduct;

    // progress bar
    private Integer count;
    private ProgressBar progressBar;

    // firebase db
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        updateStatusBarColor("#EF6C00");

        // for drawer ode
        pDrawerlayout = (DrawerLayout) findViewById(R.id.addp_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, pDrawerlayout, R.string.open, R.string.close);
        pDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);

        // for menu image click open drawer
        ImageButton menuBTN = (ImageButton) findViewById(R.id.menuBTN);
        menuBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pDrawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        productName = (EditText) findViewById(R.id.productName);
        productDescription = (EditText) findViewById(R.id.productDescription);
        regularPrice = (EditText) findViewById(R.id.regularPrice);
        discountIn = (EditText) findViewById(R.id.discountIn);
        editTextPrice = (EditText) findViewById(R.id.price);
        location = (EditText) findViewById(R.id.location);

        imageView = null;
        buttonAddImage = null;

        progressBar = (ProgressBar) findViewById(R.id.addProductProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        buttonAddProduct = (Button) findViewById(R.id.addProduct);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchantId = null;
                String name = productName.getText().toString().trim();
                double price = Double.parseDouble(editTextPrice.getText().toString().trim());
                String description = productDescription.getText().toString().trim();
                double discountRate = Double.parseDouble(discountIn.getText().toString().trim());
                double regularPrc = Double.parseDouble(regularPrice.getText().toString().trim());
                String items = description;
                String productTypeId = null;
                Product product = new Product(merchantId, name, price, description, discountRate, regularPrc, items, productTypeId);
                new AddProductTask().execute(product);
            }
        });

        initInstances();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCalculatedPrice();
    }

    private void initInstances() {
        navigation = (NavigationView) findViewById(R.id.navVW);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.dash:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        Intent i = new Intent(ProductActivity.this, MainFinOffer.class);
                        startActivity(i);
                        pDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.addPRD:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        //Toast.makeText(MainFinOffer.this, "Hello", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.lgot:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        new SingOut().signOut();
                        Intent intentMain = new Intent(ProductActivity.this, LogIn.class);
                        startActivity(intentMain);
                        break;
                }
                return false;
            }
        });
    }

    private void updateStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    private void setCalculatedPrice() {
        regularPrice.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setPriceText();
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
        discountIn.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setPriceText();
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void setPriceText() {
        float price, discount;
        if (TextUtils.isEmpty(regularPrice.getText())) {
            price = 0.0f;
        } else {
            price = Float.valueOf(regularPrice.getText().toString());
        }
        if (TextUtils.isEmpty(discountIn.getText())) {
            discount = 0.0f;
        } else {
            discount = price * Float.valueOf(discountIn.getText().toString()) / 100;
        }
        editTextPrice.setText(String.valueOf(price - discount));
    }

    public class AddProductTask extends AsyncTask<Product, Integer, Void> {
        boolean isAdded;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isAdded = false;
            // progress bar
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected Void doInBackground(Product... products) {
            Product product = products[0];
            String id = databaseReference.push().getKey();
            product.setId(id);

            databaseReference.child(id).setValue(product, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    isAdded = true;
                }
            });
            // progress bar
            for (count = 1; count <= 5; count++) {
                try {
                    Thread.sleep(500);
                    publishProgress(20 * count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // progress bar
            progressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // progress bar
            progressBar.setVisibility(View.GONE);
            if (isAdded) {
                clearAddProductFields();
                Toast.makeText(ProductActivity.this, "Added Successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ProductActivity.this, "Unable To Add!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void clearAddProductFields() {
        productName.setText("");
        productDescription.setText("");
        regularPrice.setText("");
        discountIn.setText("");
        editTextPrice.setText("");
        location.setText("");
    }
}
