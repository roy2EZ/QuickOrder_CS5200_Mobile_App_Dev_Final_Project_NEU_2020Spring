package com.cs5520.quickerorder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder> {
    private static final String TAG = "CheckoutListAdapter";
    private int checkoutLayout;
    private List<OrderDish> mDishList;

    private Map<Integer, Integer> order;

    private View.OnClickListener deleteListener;
    private View.OnClickListener addListener;


    public CheckoutListAdapter(int checkoutLayoutId, View.OnClickListener deleteListener,
                               View.OnClickListener addListener) {
        this.checkoutLayout = checkoutLayoutId;
        this.addListener = addListener;
        this.deleteListener = deleteListener;
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
        final ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        // Log.d(TAG, "onBindViewHolder: " + mDishList.get(listPosition).getId());
        holder.dishID.setText(String.valueOf(mDishList.get(listPosition).getId()));
        holder.dishQuantity.setText(String.valueOf(mDishList.get(listPosition).getQuantity()));
        holder.addCheckout.setOnClickListener(addListener);
        holder.deleteCheckput.setOnClickListener(deleteListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishID;
        TextView dishQuantity;
        Button addCheckout;
        Button deleteCheckput;


        ViewHolder(View itemView) {
            super(itemView);
            dishID = itemView.findViewById(R.id.dish_id_card);
            dishQuantity = itemView.findViewById(R.id.textview_quantity_checkout);
            addCheckout = itemView.findViewById(R.id.cart_dish_add);
            deleteCheckput = itemView.findViewById(R.id.cart_dish_remove);

/*
            addCheckout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            deleteCheckput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // delete
                }
            });

 */

        }

    }

    @Override
    public int getItemCount() {
        return this.mDishList == null ? 0: mDishList.size();
    }
}
