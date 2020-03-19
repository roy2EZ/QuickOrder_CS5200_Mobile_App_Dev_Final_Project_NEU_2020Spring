package com.cs5520.quickerorder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    private List<OrderDish> mOrder;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainViewModel mViewModel;
    private CheckoutListAdapter adapter;



    private EditText inputName;
    private EditText inputUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mRecyclerView = findViewById(R.id.recyclerView_checkout);
        mRecyclerView.setHasFixedSize(true);
        // mLayoutManager = new LinearLayoutManager(this);

        this.mOrder = new LinkedList<>();

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        observerSetup();
        recyclerSetup();
    }



    private void observerSetup() {
        mViewModel.getAllWebs().observe(this, new Observer<List<OrderDish>>() {
            @Override
            public void onChanged(@Nullable final List<OrderDish> dishes) {
                if(mOrder.size() > 0){
                    mOrder.clear();
                }
                if(dishes != null){
                    mOrder.addAll(dishes);
                }
                adapter.notifyDataSetChanged();

                adapter.setmDishList(dishes);
            }
        });
    }

    private void recyclerSetup() {
        // RecyclerView recyclerView;
        adapter = new CheckoutListAdapter(R.layout.card_dish);
        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }

/*

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper
            .SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d(TAG, "adapter pos: " + viewHolder.getAdapterPosition());
            mViewModel.deleteWebsite(adapter.psoIDtoItemId(viewHolder.getAdapterPosition()));
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mWebList.get(pos).getWeb_url()));
        Log.d(TAG, "onItemClick: " + intent.toString());
        startActivity(intent);
    }


 */
}
