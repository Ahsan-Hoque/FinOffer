package com.example.ahsanulhoque.finoffer.landingpage;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.AddProduct;
import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.authentication.Login;
import com.example.ahsanulhoque.finoffer.authentication.Singout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainFinOffer extends AppCompatActivity {

    DrawerLayout mDrawerlayout;
    ActionBarDrawerToggle mToggle;
    NavigationView navigation;

    private Toolbar lTopToolbar;
    //private FirebaseAuth firebaseAuth;

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

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for custom toolbar
        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);

        //getSupportActionBar().setTitle("Fin Offer");

        //recycler view
        RecyclerView itemList = (RecyclerView) findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"Fresh Pizza", "Fresh Curry", "Fresh Burger", "C++", "Java", "Python", "Ruby"};
        itemList.setAdapter(new ListAdapter(titles));



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

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(firebaseUser.getUid());
        //System.out.println(new LoginedUser(firebaseUser.getUid()).execute());
        System.out.println("------------------------------------------------------------------------");
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
                        Intent i = new Intent(MainFinOffer.this, AddProduct.class);
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
                        new Singout().signOut();
                        Intent intentMain = new Intent(MainFinOffer.this, Login.class);
                        startActivity(intentMain);
                        break;
                }
                return false;
            }
        });

    }



    /*private class LoginedUser extends AsyncTask<String, Void, UserProfile> {
        public LoginedUser(String uid) {
        }

        protected UserProfile doInBackground(String... id) {
            return new UserProfileService().getUserProfile(id[0]);
        }

        protected void onProgressUpdate() {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(UserProfile userProfile) {
            System.out.println(userProfile);
        }
    }*/

}
