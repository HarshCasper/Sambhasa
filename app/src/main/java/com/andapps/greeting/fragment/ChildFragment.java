package com.andapps.greeting.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andapps.greeting.adapter.AdapterMessageList;

import static com.andapps.greeting.constants.IConstants.FROM;


public abstract class  ChildFragment extends Fragment {

    protected Bundle bundle;
    protected String from;
    protected  String[] msgArray;
    protected Activity mActivity;
    private RecyclerView rvMessageLists;
    protected View view;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        from = bundle.getString(FROM);
        msgArray = getMsgArray();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvMessageLists = (RecyclerView) view.findViewById(com.andapps.greeting.R.id.rvMessageLists);
        mLayoutManager = new LinearLayoutManager(mActivity);
        rvMessageLists.setLayoutManager(mLayoutManager);

        setAdapterValues();
    }

    private void setAdapterValues() {
        AdapterMessageList adapter = new AdapterMessageList(mActivity, msgArray);
        rvMessageLists.setAdapter(adapter);
    }

    protected abstract String [] getMsgArray();
}
