package com.cs5520.quickerorder;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;


public class FragmentMenu extends Fragment implements MenuListAdapter.OnDishClickListener {
    private static final String TAG = "FragmentMenu";
    private MenuListAdapter adapter;
    private RecyclerView mRecyclerView;


    private List<Dishes> menu;



    private PopupWindow mPopWindow;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_menu, container, false);
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

        menu = new LinkedList<>();
        Dishes d1 = new Dishes(1, "Big Mac", "pic1", 10.0);
        Dishes d2 = new Dishes(2, "Spicy chicken sandwich", "pic2", 5.5);
        Dishes d3 = new Dishes(3, "Fillet Fish", "pic3", 9.99);
        menu.add(d1);
        menu.add(d2);
        menu.add(d3);
/*
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            handleExtras(extras);
        }

 */
    }

    @Override
    public void onItemClick(int pos) {
        Log.d(TAG, "onItemClick: Found");
        Toast.makeText(getContext(), "click", (int) 10).show();




        showPopupWindow(pos);

    }

    private void showPopupWindow(int id) {
        //设置contentView
        View contentView = LayoutInflater.from(this.getContext()).inflate(R.layout.popup_menu, null);

        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        ImageButton closeBtn = (ImageButton) contentView.findViewById(R.id.btn_close_pop);
        TextView dishName = (TextView) contentView.findViewById(R.id.dish_name_pop);
        dishName.setText(menu.get(id).getName());

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

}
