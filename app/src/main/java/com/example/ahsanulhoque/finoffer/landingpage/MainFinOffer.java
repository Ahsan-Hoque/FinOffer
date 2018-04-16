package com.example.ahsanulhoque.finoffer.landingpage;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.example.ahsanulhoque.finoffer.R;

public class MainFinOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateStatusBarColor("#EF6C00");
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF9800")));
//        actionBar.setTitle(Html.fromHtml("<font color='#FFFFFF'>Fin Offer</font>"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fin_offer);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));

        }
    }

}
