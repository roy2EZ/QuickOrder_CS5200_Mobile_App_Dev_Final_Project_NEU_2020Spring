package com.cs5520.quickerorder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentSpecial extends FragmentMenu {
    private static final String TAG = "FragmentSpecial";
    private List<Dishes> menu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = view.findViewById(R.id.recycle_menu);
        mRecyclerView.setHasFixedSize(true);

        int rand = new Random().nextInt(15) + 1;
        Log.d(TAG, "onCreateView: " + rand);
        this.menu = new ArrayList<>(1);
        this.menu.add(MainActivity.menu.get(rand));
        adapter = new MenuListAdapter(this.getContext(), R.layout.card_dish_menu, this, this.menu);

        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        return view;
    }

    @Override
    public void onItemClick(int pos) {
        Log.d(TAG, "onItemClick: " + this.menu.get(pos).toString());
        showPopupWindow(this.menu.get(pos));
    }
}
