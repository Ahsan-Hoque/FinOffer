package com.example.ahsanulhoque.finoffer.landingpage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.AddProduct;
import com.example.ahsanulhoque.finoffer.R;

public class MainFinOffer extends AppCompatActivity {
    Toolbar lTopToolbar;
    DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_fin_offer);
        super.onCreate(savedInstanceState);
//        statusbar color
        updateStatusBarColor("#EF6C00");

        //for drawer ode
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle );
        mToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);
//        getSupportActionBar().setTitle("Fin Offer");

//        recycler view
        RecyclerView itemList = (RecyclerView) findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"Fresh Pizza", "Fresh Curry", "Fresh Burger", "C++", "Java", "Python", "Ruby"};
        itemList.setAdapter(new ListAdapter(titles));

        RecyclerView brandList = (RecyclerView) findViewById(R.id.brandList);
        LinearLayoutManager brandLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        brandList.setLayoutManager(brandLayoutManager);
        String[] heading = {"head1", "head2", "head3", "head4", "head5"};
        brandList.setAdapter(new BrandAdapter(heading));


//        for menu image click open drawer
        ImageButton menuBTN =(ImageButton) findViewById(R.id.menuBTN);
        menuBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                    mDrawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        initInstances();



       /* updateStatusBarColor("#EF6C00");
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);
        getSupportActionBar().setTitle("Fin Offer");

        RecyclerView itemList = (RecyclerView) findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"Fresh Pizza", "Fresh Curry", "Fresh Burger", "C++", "Java", "Python", "Ruby"};
        itemList.setAdapter(new ListAdapter(titles));

        RecyclerView brandList = (RecyclerView) findViewById(R.id.brandList);
        LinearLayoutManager brandLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        brandList.setLayoutManager(brandLayoutManager);
        String[] heading = {"head1", "head2", "head3", "head4", "head5"};
        brandList.setAdapter(new BrandAdapter(heading));*/


    }



    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
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
                        Intent i = new Intent(MainFinOffer.this, AddProduct.class);
                        startActivity(i);
                        mDrawerlayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.dash:
                        //Do some thing here
                        // add navigation drawer item onclick method here
//                        Toast.makeText(MainFinOffer.this, "Hello", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });

    }


}





