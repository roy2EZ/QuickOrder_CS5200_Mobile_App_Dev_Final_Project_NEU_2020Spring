package com.cs5520.quickerorder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder> {
    private static final String TAG = "CheckoutListAdapter";
    private int checkoutLayout;
    private List<OrderDish> mDishList;


    public CheckoutListAdapter(int checkoutLayoutId) {
        this.checkoutLayout = checkoutLayoutId;
    }


    public void setmDishList(List<OrderDish> mDishList) {
        this.mDishList = mDishList;
        notifyDataSetChanged();
    }

    /*
    public interface OnWebClickListener {
        void onItemClick(int pos);
    }

     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(checkoutLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        Log.d(TAG, "onBindViewHolder: " + mDishList.get(listPosition).getId());
        holder.dishID.setText(String.valueOf(mDishList.get(listPosition).getId()));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishID;

        ViewHolder(View itemView) {
            super(itemView);
            dishID = itemView.findViewById(R.id.dish_id_card);
        }

    }

    @Override
    public int getItemCount() {
        return this.mDishList == null ? 0: mDishList.size();
    }
}
