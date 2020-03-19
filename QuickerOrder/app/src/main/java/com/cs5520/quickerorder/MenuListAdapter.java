package com.cs5520.quickerorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {
    private int menuFragmentLayout;
    private List<Dishes> menu;


    public MenuListAdapter(int menuFragmentLayoutId) {
        this.menuFragmentLayout = menuFragmentLayoutId;

        menu = new LinkedList<>();
        Dishes d1 = new Dishes(1, "Big Mac", "pic1", 10.0);
        Dishes d2 = new Dishes(2, "Spicy chicken sandwich", "pic2", 5.5);
        Dishes d3 = new Dishes(3, "Fillet Fish", "pic3", 9.99);
        menu.add(d1);
        menu.add(d2);
        menu.add(d3);
    }

    public void setMenu(List<Dishes> menu) {
        this.menu = menu;
    }

/*
    public interface OnWebClickListener {
        void onItemClick(int pos);
    }

     */

    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(menuFragmentLayout, parent, false);
        MenuListAdapter.ViewHolder myViewHolder = new MenuListAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MenuListAdapter.ViewHolder holder, final int listPosition) {
        holder.dishName.setText(String.valueOf(menu.get(listPosition).getName()));
        holder.dishPrice.setText(String.valueOf(menu.get(listPosition).getPrice()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        TextView dishPrice;

        ViewHolder(View itemView) {
            super(itemView);

            dishName = itemView.findViewById(R.id.dish_name);
            dishPrice = itemView.findViewById(R.id.dish_price);
        }

    }

    @Override
    public int getItemCount() {
        return this.menu == null ? 0: menu.size();
    }
}
