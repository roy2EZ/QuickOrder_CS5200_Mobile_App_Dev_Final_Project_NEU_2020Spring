package com.cs5520.quickerorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {
    private List<OrderDish> mOrder;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainViewModel mViewModel;
    private CheckoutListAdapter adapter;
    private Map<Integer, Integer> order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mRecyclerView = findViewById(R.id.recyclerView_checkout);
        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager(this);

        order = new HashMap<>();
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        observerSetup();
        recyclerSetup();
    }



    private void observerSetup() {
        mViewModel.getAllDishes().observe(this, new Observer<List<OrderDish>>() {
            @Override public void onChanged(@Nullable final List<OrderDish> dishes) {
                for (OrderDish d: dishes) {
                    order.put(d.getId(), d.getQuantity());
                }
                adapter.setmDishList(dishes);
            }
        });
    }

    private void recyclerSetup() {
        adapter = new CheckoutListAdapter(R.layout.card_dish,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                },
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }

    interface BtnListener {
        void onClick(View v, int id);

    }

}
