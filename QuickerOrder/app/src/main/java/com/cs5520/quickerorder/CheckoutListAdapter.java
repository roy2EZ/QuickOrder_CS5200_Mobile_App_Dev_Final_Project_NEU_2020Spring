package com.cs5520.quickerorder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder> {
    private int checkoutLayout;
    private List<OrderDish> mDishList;

    private OnWebClickListener mOnWebClickListener;

    public CheckoutListAdapter(int checkoutLayout) {
        this.checkoutLayout = checkoutLayout;
    }


    public void setmDishList(List<OrderDish> mDishList) {
        this.mDishList = mDishList;
        notifyDataSetChanged();
    }

    public interface OnWebClickListener {
        void onItemClick(int pos);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(checkoutLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view, mOnWebClickListener); return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        holder.dishID.setText(mDishList.get(listPosition).getId());
        // holder.textWebUrl.setText(mDishList.get(listPosition).getWeb_url());
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dishID;
        OnWebClickListener monClickListener;

        ViewHolder(View itemView, OnWebClickListener onWebClickListener) {
            super(itemView);
            dishID = itemView.findViewById(R.id.dish_id_card);
            monClickListener = onWebClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            monClickListener.onItemClick(getAdapterPosition());
        }
    }

    public int psoIDtoItemId (int pos) {
        return mDishList.get(pos).getId();
    }




    /*
    public interface OnItemClickListener {
        void onItemClick(Websites item);
    }


    public WebsiteListAdapter(List<Websites> itemList, OnItemClickListener listener) {
        this.mWebItem = itemList;
        this.listener = listener;
    }

     */

    /*
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Websites currentItem = mWebItem.get(position);
        holder.bind(currentItem, listener);

    }

     */

    @Override
    public int getItemCount() {
        return this.mDishList == null ? 0: mDishList.size();
    }
}
