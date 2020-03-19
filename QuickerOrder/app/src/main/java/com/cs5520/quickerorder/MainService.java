package com.cs5520.quickerorder;

import android.content.Intent;
import android.os.Bundle;

import com.cs5520.quickerorder.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import java.util.List;

public class MainService extends FragmentActivity {
    private List<Dishes> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        Dishes d1 = new Dishes(1, "Big Mac", "pic1");
        Dishes d2 = new Dishes(2, "Spicy chicken sandwich", "pic2");
        Dishes d3 = new Dishes(3, "Fillet Fish", "pic3");
        menu.add(d1);
        menu.add(d2);
        menu.add(d3);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    public void gotoMainCheckout(View view) {

        Intent i = new Intent(this, CheckoutActivity.class);
        startActivity(i);
    }

}
