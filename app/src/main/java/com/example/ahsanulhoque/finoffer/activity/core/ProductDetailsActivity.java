package com.example.ahsanulhoque.finoffer.activity.core;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.activity.authentication.LogInActivity;
import com.example.ahsanulhoque.finoffer.activity.authentication.SingOutActivity;
import com.example.ahsanulhoque.finoffer.domain.Product;

import java.io.InputStream;

public class ProductDetailsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    private Toolbar lTopToolbar;

    ImageView image;
    TextView title;
    TextView details;
    TextView location;
    TextView regularPrice;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        //statusbar color
        updateStatusBarColor("#EF6C00");

        Product product = (Product) getIntent().getSerializableExtra("product");

        title = (TextView) findViewById(R.id.ProductDTitleTV);
        details = (TextView) findViewById(R.id.ProductDDetailsTV);
        image = (ImageView) findViewById(R.id.bgIMGV);
        location = (TextView) findViewById(R.id.ProductDLocationTV);
        regularPrice = (TextView) findViewById(R.id.PDOldPriceTV);
        price = (TextView) findViewById(R.id.PDNewPriceTV);

        String title1 = product.getName();
        String regPrice = String.valueOf(product.getRegularPrice());
        String newPrice = String.valueOf(product.getPrice());
        String location1 = product.getLocation();
        String details1 = product.getDescription();

        title.setText(title1);
        regularPrice.setText("€" + regPrice);
        price.setText("€" + newPrice);
        location.setText(location1);
        details.setText(details1);
        if (!TextUtils.isEmpty(product.getImageUrl())) {
            new DownloadImageTask(image, 160, 120).execute(product.getImageUrl());
            //image.setImageURI(Uri.parse(product.getImageUrl()));
        }

        //for drawer ode
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);

        // for menu image click open drawer
        ImageButton menuBTN = (ImageButton) findViewById(R.id.menuBTN);
        menuBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDrawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        initInstances();

    }

    private void initInstances() {
        navigation = (NavigationView) findViewById(R.id.navVW);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.addPRD:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        Intent i = new Intent(ProductDetailsActivity.this, AddProductActivity.class);
                        startActivity(i);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.dash:
                        Intent j = new Intent(ProductDetailsActivity.this, MainFinOfferActivity.class);
                        startActivity(j);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.faq:
                        Intent k = new Intent(ProductDetailsActivity.this, FaqActivity.class);
                        startActivity(k);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.about:
                        Intent l = new Intent(ProductDetailsActivity.this, AboutActivity.class);
                        startActivity(l);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.lgot:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        new SingOutActivity().signOut();
                        Intent intentMain = new Intent(ProductDetailsActivity.this, LogInActivity.class);
                        startActivity(intentMain);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    public void updateStatusBarColor(String color) {
        // Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        int height, width;

        public DownloadImageTask(ImageView bmImage, int height, int width) {
            this.bmImage = bmImage;
            this.height = height;
            this.width = width;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, this.height, this.width, false));
        }
    }
}
