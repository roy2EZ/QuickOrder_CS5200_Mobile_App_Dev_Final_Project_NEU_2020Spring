package com.cs5520.quickerorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

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
        mViewModel.getAllDishes().observe(this, new Observer<List<OrderDish>>() {
            @Override public void onChanged(@Nullable final List<OrderDish> dishes) {
                adapter.setmDishList(dishes);
            }
        });
/*
        mViewModel.getSearchResults().observe(this, new Observer<List<Product>>() {
            @Override public void onChanged(@Nullable final List<Product> products) {
                if (products.size() > 0) {
                    productId.setText(String.format(Locale.US, "%d", products.get(0).getId()));
                    productName.setText(products.get(0).getName());
                    productQuantity.setText(String.format(Locale.US, "%d", products.get(0).getQuantity()));
                } else {
                    productId.setText("No Match");
                }
            }
        });

 */
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
