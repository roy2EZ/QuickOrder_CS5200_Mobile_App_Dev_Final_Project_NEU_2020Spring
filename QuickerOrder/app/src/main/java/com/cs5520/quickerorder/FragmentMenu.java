package com.cs5520.quickerorder;

import android.app.Activity;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class FragmentMenu extends Fragment implements MenuListAdapter.OnDishClickListener {
    private static final String TAG = "FragmentMenu";
    private MenuListAdapter adapter;
    private RecyclerView mRecyclerView;


    private List<Dishes> menu;
    private Map<Dishes, Integer> order;


    private PopupWindow mPopWindow;
    private GestureLibrary gLibrary;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = view.findViewById(R.id.recycle_menu);
        mRecyclerView.setHasFixedSize(true);

        adapter = new MenuListAdapter(R.layout.card_dish_menu, this, menu);

        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // add dish into our menu
        // TODO: put it into main service
        menu = new LinkedList<>();
        Dishes d1 = new Dishes(1, "Big Mac", "pic1", 10.0);
        Dishes d2 = new Dishes(2, "Spicy chicken sandwich", "pic2", 5.5);
        Dishes d3 = new Dishes(3, "Fillet Fish", "pic3", 9.99);
        menu.add(d1);
        menu.add(d2);
        menu.add(d3);

        order = new HashMap<>();

    }


    @Override
    public void onItemClick(int pos) {
        showPopupWindow(menu.get(pos));
    }


    private void showPopupWindow(final Dishes dish) {
        //设置contentView
        View contentView = LayoutInflater.from(this.getContext()).inflate(R.layout.popup_menu, null);

        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

                // = new CustomPopupWindows(contentView,
              //   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        Button closeBtn = (Button) contentView.findViewById(R.id.btn_close_pop);
        TextView dishName = (TextView) contentView.findViewById(R.id.dish_name_pop);
        TextView dishPrice = (TextView) contentView.findViewById(R.id.dish_price_pop);
        GestureOverlayView gOverlay = contentView.findViewById(R.id.gOverlay);
        dishName.setText(dish.getName());
        dishPrice.setText(String.valueOf(dish.getPrice()));

        gOverlay.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {

            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

                ArrayList<Prediction> predictions = gLibrary.recognize(gesture);
                if (predictions.size() > 0 && predictions.get(0).score > 1.0) {
                    String action = predictions.get(0).name;
                    // Toast.makeText(, action, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onGesturePerformed: ");
                }
                Log.d(TAG, "onGesturePerformed: Not ");

                if (order.containsKey(dish)) {
                    order.put(dish, order.get(dish) + 1);
                } else {
                    order.put(dish, 1);
                }

                System.out.println(menu.toString());
            }
        });

        

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(this.getContext()).inflate(R.layout.activity_main_service
                , null);



        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.gLibrary = ((MainService) getActivity()).passTo();
    }
}