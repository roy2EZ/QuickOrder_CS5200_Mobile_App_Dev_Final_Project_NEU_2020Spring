package com.cs5520.quickerorder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {
    private int menuFragmentLayout;
    private List<Dishes> menu;

    OnDishClickListener mOnDishClickListener;



    public MenuListAdapter(int menuFragmentLayoutId, OnDishClickListener mOnDishClickListener, List<Dishes> l) {
        this.menuFragmentLayout = menuFragmentLayoutId;
        this.mOnDishClickListener = mOnDishClickListener;

        menu = l;

    }

    public void setMenu(List<Dishes> menu) {
        this.menu = menu;
    }


    public interface OnDishClickListener {
        void onItemClick(int pos);
    }

    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(menuFragmentLayout, parent, false);
        MenuListAdapter.ViewHolder myViewHolder = new MenuListAdapter.ViewHolder(view, mOnDishClickListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MenuListAdapter.ViewHolder holder, final int listPosition) {
        holder.dishName.setText(String.valueOf(menu.get(listPosition).getName()));
        holder.dishPrice.setText(String.valueOf(menu.get(listPosition).getPrice()));
        holder.dishImage.setImageResource(
                menu.get(listPosition).getPic()); //dishPrice.setText(String.valueOf(menu.get(listPosition).getPrice()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dishName;
        TextView dishPrice;
        ImageView dishImage;

        OnDishClickListener mViewHolderOnDishClickListener;

        ViewHolder(View itemView, OnDishClickListener onDishClickListener) {
            super(itemView);
            mViewHolderOnDishClickListener = onDishClickListener;
            dishName = itemView.findViewById(R.id.dish_name);
            dishPrice = itemView.findViewById(R.id.dish_price);
            dishImage = itemView.findViewById(R.id.dish_pic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mViewHolderOnDishClickListener.onItemClick(getAdapterPosition());
        }

    }

    @Override
    public int getItemCount() {
        return this.menu == null ? 0: menu.size();
    }
}
