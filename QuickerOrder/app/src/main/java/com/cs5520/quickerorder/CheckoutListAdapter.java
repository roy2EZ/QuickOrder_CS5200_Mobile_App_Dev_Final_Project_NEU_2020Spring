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

    private MainViewModel mViewModel;


    public CheckoutListAdapter(int checkoutLayoutId, MainViewModel mViewModel) {
        this.checkoutLayout = checkoutLayoutId;
        this.mViewModel = mViewModel;
    }


    public void setmDishList(List<OrderDish> mDishList) {
        this.mDishList = mDishList;
        notifyDataSetChanged();
    }

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
        holder.dishID.setText(String.valueOf(MainActivity.menu.get(mDishList.get(listPosition).getId()).getName()));
        holder.dishQuantity.setText(String.valueOf(mDishList.get(listPosition).getQuantity()));
        holder.deleteCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDishList.get(listPosition).getQuantity() > 1) {
                    mDishList.get(listPosition).setQuantity(mDishList.get(listPosition).getQuantity() - 1);
                    mViewModel.update(new OrderDish(mDishList.get(listPosition).getId(), mDishList.get(listPosition).getQuantity()));
                    notifyDataSetChanged();
                } else {
                    mViewModel.deleteDish(mDishList.get(listPosition).getId());
                    mDishList.remove(listPosition);
                    notifyDataSetChanged();
                }
            }
        });
        holder.addCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDishList.get(listPosition).setQuantity(mDishList.get(listPosition).getQuantity() + 1);
                mViewModel.update(new OrderDish(mDishList.get(listPosition).getId(), mDishList.get(listPosition).getQuantity()));
                notifyDataSetChanged();
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishID;
        TextView dishQuantity;
        Button addCheckout;
        Button deleteCheckout;


        ViewHolder(View itemView) {
            super(itemView);
            dishID = itemView.findViewById(R.id.dish_id_card);
            dishQuantity = itemView.findViewById(R.id.textview_quantity_checkout);
            addCheckout = itemView.findViewById(R.id.cart_dish_add);
            deleteCheckout = itemView.findViewById(R.id.cart_dish_remove);

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
/*
    public void addDishCheckout(View view) {
        Log.d(TAG, "addDishCheckout: ");
    }

    public void removeDishCheckout(View view) {
        Log.d(TAG, "removeDishCheckout: ");

    }

 */


}
