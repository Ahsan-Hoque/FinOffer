package com.example.ahsanulhoque.finoffer;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class AddProduct extends AppCompatActivity {

    Toolbar lTopToolbar;
    DrawerLayout pDrawerlayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        updateStatusBarColor("#EF6C00");
        //for drawer ode
        pDrawerlayout = (DrawerLayout) findViewById(R.id.addp_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, pDrawerlayout, R.string.open, R.string.close);
        pDrawerlayout.addDrawerListener(mToggle );
        mToggle.syncState();
        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);

        //        for menu image click open drawer
        ImageButton menuBTN =(ImageButton) findViewById(R.id.menuBTN);
        menuBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                pDrawerlayout.openDrawer(Gravity.LEFT);
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
}
