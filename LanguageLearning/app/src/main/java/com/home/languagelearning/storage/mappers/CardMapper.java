package com.home.languagelearning.storage.mappers;

import android.database.Cursor;

import com.home.languagelearning.BuildConfig;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.storage.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class CardMapper implements IDataMapper<ChineseToEnglishCard> {

    private int idIdx = NOT_INITIALIZED;
    private int originIdx = NOT_INITIALIZED;
    private int translationIdx = NOT_INITIALIZED;

    @Override
    public ChineseToEnglishCard createFromCursor(Cursor cursor, int pos) {
        if (BuildConfig.DEBUG && pos >= cursor.getCount()) {
            final String exception = "This position is out of range: " + pos + " vs " + cursor.getCount();
            throw new IllegalArgumentException(exception);
        }

        if (!inputIsValid(cursor)) return new ChineseToEnglishCard();
        initializeIndexes(cursor);
        cursor.moveToPosition(pos);
        return createFromCursor(cursor);
    }

    @Override
    public ChineseToEnglishCard createFromCursor(Cursor cursor) {
        if (!inputIsValid(cursor)) return new ChineseToEnglishCard();
        initializeIndexes(cursor);

        ChineseToEnglishCard card = new ChineseToEnglishCard();
        card.setId(cursor.getInt(idIdx));
        card.setOrigin(cursor.getString(originIdx));
        card.setTranslation(cursor.getString(translationIdx));
        return card;
    }

    @Override
    public List<ChineseToEnglishCard> createListFromCursor(Cursor cursor) {
        if (!inputIsValid(cursor)) return new ArrayList<>();
        initializeIndexes(cursor);

        List<ChineseToEnglishCard> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            result.add(createFromCursor(cursor));
        }
        return result;
    }

    @Override
    public void initializeIndexes(Cursor cursor) {
        if (inputIsValid(cursor) && originIdx == NOT_INITIALIZED) {
            idIdx = cursor.getColumnIndex(Contract.CardsTable.ID);
            originIdx = cursor.getColumnIndex(Contract.CardsTable.CHINESE);
            translationIdx = cursor.getColumnIndex(Contract.CardsTable.ENGLISH);
        }
    }

    @Override
    public boolean inputIsValid(Cursor cursor) {
        return cursor != null;
    }
}
