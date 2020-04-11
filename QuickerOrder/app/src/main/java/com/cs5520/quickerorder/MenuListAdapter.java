package com.cs5520.quickerorder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


    private Context context;




    public MenuListAdapter(Context current, int menuFragmentLayoutId, OnDishClickListener mOnDishClickListener, List<Dishes> l) {
        this.menuFragmentLayout = menuFragmentLayoutId;
        this.mOnDishClickListener = mOnDishClickListener;
        this.context = current;
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
        holder.dishImage.setImageBitmap( decodeSampledBitmapFromResource(this.context.getResources(), menu.get(listPosition).getPic(), 100, 100)
                ); //dishPrice.setText(String.valueOf(menu.get(listPosition).getPrice()));
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
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
