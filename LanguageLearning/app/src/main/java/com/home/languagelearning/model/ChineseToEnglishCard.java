package com.home.languagelearning.model;

import android.content.ContentValues;

import com.home.languagelearning.storage.Contract;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class ChineseToEnglishCard implements ICard {
    private String chinesePinyin;
    private String english;

    @Override
    public String getOrigin() {
        return chinesePinyin;
    }

    @Override
    public void setOrigin(String origin) {
        this.chinesePinyin = origin;
    }

    @Override
    public String getTranslation() {
        return english;
    }

    @Override
    public void setTranslation(String translated) {
        this.english = translated;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put(Contract.CardsTable.CHINESE, chinesePinyin);
        contentValues.put(Contract.CardsTable.ENGLISH, english);
        return contentValues;
    }
}
