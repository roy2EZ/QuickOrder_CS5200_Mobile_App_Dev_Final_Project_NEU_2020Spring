package com.cs5520.quickerorder.ui.main;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cs5520.quickerorder.FragmentCallService;
import com.cs5520.quickerorder.FragmentMenu;
import com.cs5520.quickerorder.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.full_menu, R.string.recommendation, R.string.call_service};
    private static final String TAG = "SectionsPagerAdapter";
    private final Context mContext;
    private Fragment fullmenu;
    private Fragment recommandation;
    private  Fragment callservice;


    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

        fullmenu = new FragmentMenu();
        recommandation = new FragmentMenu();
        callservice = new FragmentCallService();

    }
/*
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

 */

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).



        switch (position) {
            case 0:
                return fullmenu;
            case 1:
                return recommandation;
            case 2:
                return callservice;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        // dynamically
        return TAB_TITLES.length;
    }
}