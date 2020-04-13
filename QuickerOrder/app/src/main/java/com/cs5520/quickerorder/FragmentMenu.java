package com.cs5520.quickerorder;

import android.content.Context;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class FragmentMenu extends Fragment implements MenuListAdapter.OnDishClickListener {
    private static final String TAG = "FragmentMenu";
    private static final String ADDDISH = "Added!";
    private static final String DELETEDISH = "Deleted!";
    private static final String NOITEM ="No such item in cart!";
    private static final String NOTRECOGNIZE ="Gesture is not recognized!";
    protected MenuListAdapter adapter;
    protected RecyclerView mRecyclerView;

    protected OrderRepository orderRepository;

    private List<Dishes> menu;

    private  List<OrderDish> orderDishes;
    protected Map<Integer, Integer> order;

    protected MainViewModel mViewModel;

    protected PopupWindow mPopWindow;
    protected GestureLibrary gLibrary;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = view.findViewById(R.id.recycle_menu);
        mRecyclerView.setHasFixedSize(true);

        adapter = new MenuListAdapter(this.getContext(), R.layout.card_dish_menu, this, menu);

        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        return view;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // add dish into our menu
        // TODO: put it into main service

        menu = new ArrayList<>(MainActivity.menu.values());

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        observerSetup();

        order = new HashMap<>();


    }

    protected void observerSetup() {
        mViewModel.getAllDishes().removeObservers(this);
        mViewModel.getAllDishes().observe(this, new Observer<List<OrderDish>>() {
            @Override
            public void onChanged(List<OrderDish> dishes) {
                order.clear();
                for (OrderDish d : dishes) {
                    order.put(d.getId(), d.getQuantity());
                }
            }
        });
    }

    @Override
    public void onItemClick(int pos) {
        Log.d(TAG, "onItemClick: " + menu.get(pos).toString());
        showPopupWindow(menu.get(pos));
    }

    protected void showPopupWindow(final Dishes dish) {
        //设置contentView
        View contentView = LayoutInflater.from(this.getContext()).inflate(R.layout.popup_menu, null);

        mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

                // = new CustomPopupWindows(contentView,
              //   ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        FloatingActionButton closeBtn = (FloatingActionButton) contentView.findViewById(R.id.btn_close_pop);
        TextView dishName = (TextView) contentView.findViewById(R.id.dish_name_pop);
        TextView dishPrice = (TextView) contentView.findViewById(R.id.dish_price_pop);

        TextView dishDescription = (TextView) contentView.findViewById(R.id.dish_desp_pop);
        GestureOverlayView gOverlay = contentView.findViewById(R.id.gOverlay);

        final Button addDish = (Button) contentView.findViewById(R.id.add_dish_pop);
//        final Button deleteDish = (Button) contentView.findViewById(R.id.delete_dish_pop);
        ImageView dishImage = (ImageView) contentView.findViewById(R.id.dish_image_pop);


        dishName.setText(dish.getName());
        dishPrice.setText(new StringBuilder().append("$").append(String.valueOf(dish.getPrice())));
        dishDescription.setText(dish.getDescription());
        Log.d(TAG, "showPopupWindow: " + dish.getPic());
        dishImage.setImageResource(dish.getPic());


        gOverlay.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {


                ArrayList<Prediction> predictions = gLibrary.recognize(gesture);


                if (predictions!= null && predictions.size() > 0 && predictions.get(0).score > 4) {
                    if (predictions.get(0).name.startsWith("circle")||predictions.get(0).name.startsWith("0")||predictions.get(0).name.startsWith("o")) {
                        addDish(dish);
                    } else if (predictions.get(0).name.startsWith("x")) {
                        deleteDish(dish);
                    }
                } else {
                    Toast.makeText(getContext(), NOTRECOGNIZE, NOTRECOGNIZE.length()).show();
                }
            }
        });


        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDish(dish);

            }

                /*
                boolean contains = false;
                for(OrderDish d : orderDishes) {
                    if (d.getId() == dish.getId()) {
                        contains = true;
                        d.setQuantity(d.getQuantity() + 1);
                    }
                }
                Log.d(TAG, "onGesturePerformed: contains ? " + contains);
                if (!contains) {
                    OrderDish newDish = new OrderDish(dish.getId(), 1);
                    orderDishes.add(newDish);
                    orderRepository.insertDish(newDish);
                }
            }

                 */
        });

//        deleteDish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDish(dish);
//            }
//        });

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

    protected void addDish(Dishes dish) {
        if (order.containsKey(dish.getId())) {
            order.put(dish.getId(), order.get(dish.getId()) + 1);
            orderRepository.updateDish(new OrderDish(dish.getId(), order.get((dish.getId())), dish.getPrice()));
        } else {
            order.put(dish.getId(), 1);
            orderRepository.insertDish(new OrderDish(dish.getId(), 1, dish.getPrice()));
        }
        Toast.makeText(getContext(), ADDDISH, ADDDISH.length()).show();
    }

    protected void deleteDish(Dishes dish) {
        if (order.containsKey(dish.getId())) {
            if (order.get(dish.getId()) > 1) {
                order.put(dish.getId(), order.get(dish.getId()) - 1);
                orderRepository.updateDish(new OrderDish(dish.getId(), order.get((dish.getId())), dish.getPrice()));
            } else {
                order.remove(dish.getId());
                orderRepository.deleteDish(dish.getId());
            }
            Toast.makeText(getContext(), DELETEDISH, DELETEDISH.length()).show();
        } else {
            Toast.makeText(getContext(), NOITEM, NOITEM.length()).show();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.gLibrary = ((MainService) getActivity()).passTo();
        this.orderRepository = ((MainService) getActivity()).passRepository();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.gLibrary = ((MainService) getActivity()).passTo();
        this.orderRepository = ((MainService) getActivity()).passRepository();
    }
}