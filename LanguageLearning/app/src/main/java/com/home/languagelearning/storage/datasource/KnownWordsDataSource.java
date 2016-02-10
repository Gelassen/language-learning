package com.home.languagelearning.storage.datasource;

import android.database.Cursor;

import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.storage.mappers.CardMapper;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class KnownWordsDataSource extends CursorDataSource<ChineseToEnglishCard> {

    public KnownWordsDataSource(Cursor cursor) {
        super(new CardMapper(), cursor);
    }

    @Override
    public ChineseToEnglishCard getForPosition(int pos) {
        return super.getForPosition(pos);
    }
}
