package com.home.languagelearning.domain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.home.languagelearning.model.Page;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class CardBroadcastReceiver extends BroadcastReceiver {

    private CardBroadcastListener listener;

    public CardBroadcastReceiver(CardBroadcastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null) {
            final Page page = intent.getParcelableExtra(CardController.CardActions.ACTION_PAGE);
            listener.onPageCountChanged(page);
        }
    }

    public interface CardBroadcastListener {
        void onPageCountChanged(final Page page);
    }
}
