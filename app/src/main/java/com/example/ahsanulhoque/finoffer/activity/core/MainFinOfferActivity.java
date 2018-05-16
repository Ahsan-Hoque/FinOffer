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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.activity.authentication.LogInActivity;
import com.example.ahsanulhoque.finoffer.activity.authentication.SingOutActivity;
import com.example.ahsanulhoque.finoffer.adapter.BrandAdapter;
import com.example.ahsanulhoque.finoffer.adapter.ListAdapter;
import com.example.ahsanulhoque.finoffer.domain.Product;
import com.example.ahsanulhoque.finoffer.helper.RecyclerTouchListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainFinOfferActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    private Toolbar lTopToolbar;

    private RecyclerView itemListRecyclerView;
    private RecyclerView brandListRecyclerView;

    // progress bar
    private Integer itemCount;
    private Integer brandCount;
    private ProgressBar itemListProgressBar;
    private ProgressBar brandListProgressBar;

    // firebase db
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fin_offer);

        //statusbar color
        updateStatusBarColor("#EF6C00");

        //for drawer ode
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);

        // progressbar
        itemListProgressBar = (ProgressBar) findViewById(R.id.itemListProgressBar);
        itemListProgressBar.setVisibility(View.INVISIBLE);
        brandListProgressBar = (ProgressBar) findViewById(R.id.brandListProgressBar);
        brandListProgressBar.setVisibility(View.INVISIBLE);

        //recycler view
        itemListRecyclerView = (RecyclerView) findViewById(R.id.itemlist);
        itemListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemListLoadingTask().execute();

        brandListRecyclerView = (RecyclerView) findViewById(R.id.brandList);
        LinearLayoutManager brandLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        brandListRecyclerView.setLayoutManager(brandLayoutManager);
        new BrandListLoadingTask().execute();


        // for menu image click open drawer
        ImageButton menuBTN = (ImageButton) findViewById(R.id.menuBTN);
        menuBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDrawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        initInstances();
    }

    public void updateStatusBarColor(String color) {
        // Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
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
                        Intent i = new Intent(MainFinOfferActivity.this, AddProductActivity.class);
                        startActivity(i);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.dash:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        //Toast.makeText(MainFinOfferActivity.this, "Hello", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.faq:
                        Intent k = new Intent(MainFinOfferActivity.this, FaqActivity.class);
                        startActivity(k);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.about:
                        Intent l = new Intent(MainFinOfferActivity.this, AboutActivity.class);
                        startActivity(l);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.lgot:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        new SingOutActivity().signOut();
                        Intent intentMain = new Intent(MainFinOfferActivity.this, LogInActivity.class);
                        startActivity(intentMain);
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    private class ItemListLoadingTask extends AsyncTask<Void, Integer, Void> {
        private List<Product> productList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // progress bar
            itemListProgressBar.setVisibility(View.VISIBLE);
            itemListProgressBar.setProgress(0);
            itemListProgressBar.setMax(100);
            productList = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Query query = databaseReference;
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot issue : dataSnapshot.getChildren()) {
                            // do something with the individual "issues"
                            Product product = issue.getValue(Product.class);
                            productList.add(product);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            // progress bar
            for (itemCount = 1; itemCount <= 5; itemCount++) {
                try {
                    Thread.sleep(500);
                    publishProgress(20 * itemCount);
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
            itemListProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // progressbar
            itemListProgressBar.setVisibility(View.GONE);
            itemListRecyclerView.setAdapter(new ListAdapter(productList));
            itemListRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), itemListRecyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Product product = productList.get(position);
                    Intent productDetailsIntent = new Intent(MainFinOfferActivity.this, ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("product", product);
                    startActivity(productDetailsIntent);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }
    }

    private class BrandListLoadingTask extends AsyncTask<Void, Integer, Void> {

        String[] heading = {"Foody", "Mara", "Fossil", "Offerin", "Delux"};

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            brandListProgressBar.setVisibility(View.VISIBLE);
            brandListProgressBar.setProgress(0);
            brandListProgressBar.setMax(100);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // progressbar
            for (brandCount = 1; brandCount <= 5; brandCount++) {
                try {
                    Thread.sleep(500);
                    publishProgress(20 * brandCount);
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
            brandListProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // progress bar
            brandListProgressBar.setVisibility(View.GONE);
            brandListRecyclerView.setAdapter(new BrandAdapter(heading));

        }
    }


}
