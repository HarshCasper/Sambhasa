package com.andapps.greeting.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andapps.greeting.GreetingActivity;
import com.andapps.greeting.listener.TabLayoutSetupCallback;

import java.util.ArrayList;
import java.util.List;


public class BaseFragment extends Fragment {
    protected TabLayoutSetupCallback mToolbarSetupCallback;
    protected List<String> mTabNamesList;
    protected Bundle bundle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof GreetingActivity) {
            mToolbarSetupCallback = (TabLayoutSetupCallback) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement TabLayoutSetupCallback");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTabNamesList = new ArrayList<>();
        mTabNamesList.add("English");
        mTabNamesList.add("Gujarati");
        mTabNamesList.add("Hindi");

        bundle = getArguments();
    }

    public static class ItemsPagerAdapter extends FragmentStatePagerAdapter {

        private List<String> mTabs = new ArrayList<>();
        private Bundle bundle;

        public ItemsPagerAdapter(FragmentManager fm, List<String> tabNames, Bundle bundle) {
            super(fm);

            mTabs = tabNames;
            this.bundle = bundle;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    EnglishFragment englishFragment = new EnglishFragment();
                    englishFragment.setArguments(bundle);
                    return englishFragment;
                case 1:
                    GujaratiFragment gujaratiFragment = new GujaratiFragment();
                    gujaratiFragment.setArguments(bundle);
                    return gujaratiFragment;
                case 2:
                    HindiFragment hindiFragment = new HindiFragment();
                    hindiFragment.setArguments(bundle);
                    return hindiFragment;
                default:
                    return new EnglishFragment();
            }

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position);
        }
    }
}
