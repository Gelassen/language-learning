package com.home.languagelearning.domain;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.home.languagelearning.model.Page;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class CardController {

    private CardBroadcastReceiver cardBroadcastReceiver;
    private IntentFilter intentFilter;

    public CardController(CardBroadcastReceiver.CardBroadcastListener listener) {
        cardBroadcastReceiver = new CardBroadcastReceiver(listener);
        intentFilter = new IntentFilter();
        intentFilter.addAction(CardActions.ACTION_PAGE);
    }

    public static void broadcastPageInfo(Context context, final int amountOfPages) {
        Page page = new Page();
        page.setTotal(amountOfPages);

        Intent intent = new Intent();
        intent.setAction(CardActions.ACTION_PAGE);
        intent.putExtra(CardActions.ACTION_PAGE, page);
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(intent);
    }

    public void registerBroadcastReceiver(Context context) {
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(cardBroadcastReceiver, intentFilter);
    }

    public void unregisterBroadcastReceiver(Context context) {
        LocalBroadcastManager.getInstance(context)
                .unregisterReceiver(cardBroadcastReceiver);
    }

    /*package*/ static class CardActions {
        public static final String ACTION_PAGE = "ACTION_PAGE";
    }
}
