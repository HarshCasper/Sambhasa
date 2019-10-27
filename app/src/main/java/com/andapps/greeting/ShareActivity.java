package com.andapps.greeting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.andapps.greeting.adapter.AdapterMessageList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ShareActivity extends AppCompatActivity {

    private RecyclerView rvMessageLists;
    private LinearLayoutManager mLayoutManager;
    private String[] msgArray;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.andapps.greeting.R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(com.andapps.greeting.R.id.toolbar);
        setSupportActionBar(toolbar);
        msgArray = getResources().getStringArray(com.andapps.greeting.R.array.diwali_english_message_list);
        //msgArray = new String[]{"3","2","1"};
        setLayoutViews();
        setAdapterValues();
        loadAd();
    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.andapps.greeting.R.menu.activity_share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case com.andapps.greeting.R.id.share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBodyText = "દિવાળીના હાર્દિક શુભકામના";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    private void setLayoutViews() {
        mAdView = (AdView) findViewById(R.id.adView);
        rvMessageLists = (RecyclerView) findViewById(com.andapps.greeting.R.id.rvMessageLists);
        mLayoutManager = new LinearLayoutManager(this);
        rvMessageLists.setLayoutManager(mLayoutManager);
        //rvMessageLists.addItemDecoration(new SimpleDividerItemDecoration(this));
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void setAdapterValues() {
        AdapterMessageList adapter = new AdapterMessageList(this, msgArray);
        rvMessageLists.setAdapter(adapter);
    }

    private void loadAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
