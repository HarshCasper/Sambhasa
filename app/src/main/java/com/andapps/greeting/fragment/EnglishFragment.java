package com.andapps.greeting.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andapps.greeting.R;

import static com.andapps.greeting.constants.IConstants.TAG_DIWALI;
import static com.andapps.greeting.constants.IConstants.TAG_NEW_YEAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends ChildFragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_english, container, false);
        return view;
    }

    @Override
    protected String[] getMsgArray() {
        String[] msgArray = new String[0];
        switch (from){

            case TAG_DIWALI:
                msgArray = mActivity.getResources().getStringArray(R.array.diwali_english_message_list);
                break;

            case TAG_NEW_YEAR:
                msgArray = mActivity.getResources().getStringArray(R.array.new_year_english_message_list);
                break;

        }

        return msgArray;
    }
}
