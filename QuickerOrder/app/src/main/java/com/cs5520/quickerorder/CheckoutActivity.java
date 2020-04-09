package com.cs5520.quickerorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private OrderRepository repository;


    private SensorManager sm;
    private Sensor ligthSensor;
    private StringBuffer sb;
    private TextView tvValue;


    private List<OrderDish> t;


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


        sb = new StringBuffer();

        //获取SensorManager对象
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取Sensor对象
        ligthSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        sm.registerListener(new MySensorListener(), ligthSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }



    private void observerSetup() {
        mViewModel.getAllDishes().observe(this, new Observer<List<OrderDish>>() {
            @Override public void onChanged(@Nullable final List<OrderDish> dishes) {
                adapter.setmDishList(dishes);
            }
        });
    }

    private void recyclerSetup() {
        adapter = new CheckoutListAdapter(R.layout.card_dish, mViewModel);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        observerSetup();
    }

    private void checkout() {
        List<OrderDish> orderDishes = mViewModel.getAllDishes().getValue();
        for (OrderDish o: orderDishes) {
            mViewModel.deleteDish(o.getId());
        }
        Toast.makeText(getApplicationContext(), "Checked out!", (int) 10).show();
    }

    public class MySensorListener implements SensorEventListener {

        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent event) {
            //获取精度
            float acc = event.accuracy;
            //获取光线强度
            float lux = event.values[0];

            sb.append("acc ----> " + acc);
            sb.append("\n");
            sb.append("lux ----> " + lux);
            sb.append("\n");

            if (lux < 5000) checkout();
        }

    }

}
