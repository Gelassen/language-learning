package com.home.languagelearning.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;

import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ICard;
import com.home.languagelearning.storage.Contract;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class AddNewWordController {
    private static final int MAX_PAGE = 2;
    private int page;
    private ICard card;

    public AddNewWordController() {
        card = new ChineseToEnglishCard();
        page = 1;
    }

    public void addOrigin(final String origin) {
        card.setOrigin(origin);
    }

    public void addTranslation(final String translation) {
        card.setTranslation(translation);
    }

    public boolean isDataValid() {
        return !(TextUtils.isEmpty(card.getOrigin()) || TextUtils.isEmpty(card.getTranslation()));
    }

    public boolean nextPage() {
        return ++page <= MAX_PAGE;
    }

    public void save(Context context) {
        ContentResolver cr = context.getContentResolver();
        cr.insert(Contract.contentUri(Contract.CardsTable.class), card.toContentValues());
    }
}
