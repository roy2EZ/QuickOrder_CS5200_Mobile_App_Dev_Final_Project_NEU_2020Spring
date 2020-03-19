package com.cs5520.quickerorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentMenu extends Fragment {
    private MenuListAdapter adapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = view.findViewById(R.id.recycle_menu);
        mRecyclerView.setHasFixedSize(true);

        adapter = new MenuListAdapter(R.layout.card_dish_menu);

        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
