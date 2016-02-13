package com.home.languagelearning.domain;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.home.languagelearning.App;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ICard;
import com.home.languagelearning.model.Page;
import com.home.languagelearning.storage.Contract;

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

    public void updateCard(Context context, ICard card) {
        if (!(card instanceof ChineseToEnglishCard)) {
            throw new IllegalArgumentException("Did you forget to add support of the new card?");
        }

        ((ChineseToEnglishCard) card).markAsKnown(true);
        ContentValues values = card.toContentValues();
        Uri uri = Contract.contentUri(Contract.CardsTable.class);
        // TODO replace insert by update
        final String where = Contract.CardsTable.ID + "=?";
        final String[] selectionArgs = new String[] {String.valueOf(card.getId())};
        final int updated = context.getContentResolver()
                .update(uri, values, where, selectionArgs);
                //.insert(uri, values);
        Log.d(App.TAG, "Updated card: " + updated);
    }

    /*package*/ static class CardActions {
        public static final String ACTION_PAGE = "ACTION_PAGE";
    }
}
