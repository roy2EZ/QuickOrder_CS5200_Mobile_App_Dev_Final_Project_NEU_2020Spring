package com.cs5520.quickerorder;

import android.content.Intent;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService extends FragmentActivity {
    // private List<Dishes> menu;

    private GestureLibrary gLibrary;

    private Map<Dishes, Integer> order;

    public Map<Dishes, Integer> getOrder() {
        return order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);


        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        order = new HashMap<>();


        gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLibrary.load()) {
            finish();
        }


    }

    public void gotoMainCheckout(View view) {
        Intent i = new Intent(this, CheckoutActivity.class);
        startActivity(i);
    }

    public GestureLibrary passTo() {
        return this.gLibrary;
    }

}
