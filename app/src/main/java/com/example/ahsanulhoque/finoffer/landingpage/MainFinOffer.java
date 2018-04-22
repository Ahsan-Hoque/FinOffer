package com.example.ahsanulhoque.finoffer.landingpage;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.UserProfile;
import com.example.ahsanulhoque.finoffer.service.UserProfileService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainFinOffer extends AppCompatActivity {

    private Toolbar lTopToolbar;
    //private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateStatusBarColor("#EF6C00");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fin_offer);

        lTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(lTopToolbar);
        getSupportActionBar().setTitle("Fin Offer");

        RecyclerView itemList = (RecyclerView) findViewById(R.id.itemlist);
        itemList.setLayoutManager(new LinearLayoutManager(this));
        String[] titles = {"Fresh Pizza", "Fresh Curry", "Fresh Burger", "C++", "Java", "Python", "Ruby"};
        itemList.setAdapter(new ListAdapter(titles));

        RecyclerView brandList = (RecyclerView) findViewById(R.id.brandList);
        LinearLayoutManager brandLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        brandList.setLayoutManager(brandLayoutManager);
        String[] heading = {"head1", "head2", "head3", "head4", "head5"};
        brandList.setAdapter(new BrandAdapter(heading));

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("------------------------------------------------------------------------");
        System.out.println(firebaseUser.getUid());
        System.out.println(new UserProfileService().getUserProfile(firebaseUser.getUid()));
        System.out.println("------------------------------------------------------------------------");
    }

    public void updateStatusBarColor(String color){
        // Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));

        }
    }

}
