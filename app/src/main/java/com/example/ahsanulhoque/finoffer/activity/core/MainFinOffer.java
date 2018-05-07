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
import com.example.ahsanulhoque.finoffer.activity.authentication.LogIn;
import com.example.ahsanulhoque.finoffer.activity.authentication.SingOut;
import com.example.ahsanulhoque.finoffer.adapter.BrandAdapter;
import com.example.ahsanulhoque.finoffer.adapter.ListAdapter;
import com.example.ahsanulhoque.finoffer.domain.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainFinOffer extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;

    private Toolbar lTopToolbar;

    private RecyclerView itemList;

    // progress bar
    private Integer count;
    private ProgressBar progressBar;

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

        progressBar = (ProgressBar) findViewById(R.id.itemListProgressBar);
        progressBar.setVisibility(View.INVISIBLE);

        //recycler view
        itemList = (RecyclerView) findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        new TitleLoadingTask().execute();

        RecyclerView brandList = (RecyclerView) findViewById(R.id.brandList);
        LinearLayoutManager brandLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        brandList.setLayoutManager(brandLayoutManager);
        String[] heading = {"head1", "head2", "head3", "head4", "head5"};
        brandList.setAdapter(new BrandAdapter(heading));

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
                        Intent i = new Intent(MainFinOffer.this, ProductActivity.class);
                        startActivity(i);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.dash:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        //Toast.makeText(MainFinOffer.this, "Hello", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.lgot:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        new SingOut().signOut();
                        Intent intentMain = new Intent(MainFinOffer.this, LogIn.class);
                        startActivity(intentMain);
                        break;
                }
                return false;
            }
        });

    }

    private class TitleLoadingTask extends AsyncTask<Void, Integer, Void> {
        private List<Product> productList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // progress bar
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(0);
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
            itemList.setAdapter(new ListAdapter(productList));
        }
    }


}
