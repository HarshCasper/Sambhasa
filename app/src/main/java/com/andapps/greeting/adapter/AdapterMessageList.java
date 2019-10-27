package com.andapps.greeting.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andapps.greeting.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class AdapterMessageList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private String msgList[];
    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    public AdapterMessageList(Activity activity, String msgList[]) {
        this.activity = activity;
        this.msgList = msgList;
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(activity.getString(R.string.interstitial_full_screen));
        adRequest = new AdRequest.Builder()
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_row_message, parent, false);
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MessageViewHolder myHolder = (MessageViewHolder) holder;
        String text = msgList[position];
        myHolder.tvMessage.setText(text);
        myHolder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = position%2;

                switch(i){

                    case 0:
                        shareIntent(msgList[position]);
                        break;
                    case 1:
                        shareIntent(msgList[position]);
                        // Load ads into Interstitial Ads
                        mInterstitialAd.loadAd(adRequest);
                        mInterstitialAd.setAdListener(new AdListener() {
                            public void onAdLoaded() {
                                showInterstitial();
                            }

                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                // Code to be executed when an ad request fails.
                                Log.d("Ads", "onAdFailedToLoad");
                                //shareIntent(msgList[position]);
                            }

                            @Override
                            public void onAdOpened() {
                                // Code to be executed when an ad opens an overlay that
                                // covers the screen.
                                Log.i("Ads", "onAdOpened");
                            }

                            @Override
                            public void onAdLeftApplication() {
                                // Code to be executed when the user has left the app.
                                Log.i("Ads", "onAdLeftApplication");
                            }

                            @Override
                            public void onAdClosed() {
                                // Code to be executed when when the user is about to return
                                // to the app after tapping on an ad.
                                Log.d("Ads", "onAdClosed");
                                //shareIntent(msgList[position]);
                            }
                        });
                        break;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return msgList.length;
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        final TextView tvMessage;
        final View rootView;
        final ImageView ivShare;


        MessageViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            tvMessage = (TextView) itemView.findViewById(R.id.tvMessage);
            ivShare = (ImageView) itemView.findViewById(R.id.ivShare);

        }
    }

    private void shareIntent(String message) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBodyText = message;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Greetings");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText+"\n\uD83D\uDE4F");
        activity.startActivity(Intent.createChooser(sharingIntent, "Send"));
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
