package com.cs5520.quickerorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentMenu extends Fragment implements MenuListAdapter.OnDishClickListener {
    private static final String TAG = "FragmentMenu";
    private MenuListAdapter adapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_menu, container, false);
        mRecyclerView = view.findViewById(R.id.recycle_menu);
        mRecyclerView.setHasFixedSize(true);

        adapter = new MenuListAdapter(R.layout.card_dish_menu, this);

        // recyclerView = getView().findViewById(R.id.product_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onItemClick(int pos) {
        Log.d(TAG, "onItemClick: Found");
        Toast.makeText(getContext(), "click", (int) 10).show();
        /*
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mWebList.get(pos).getWeb_url()));
        Log.d(TAG, "onItemClick: " + intent.toString());
        startActivity(intent);

         */

        Fragment fragmentDishPage = new FragmentDishPage();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.view_pager, fragmentDishPage);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
