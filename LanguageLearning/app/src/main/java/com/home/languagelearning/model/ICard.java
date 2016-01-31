package com.home.languagelearning.model;

import android.content.ContentValues;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public interface ICard {
    String getOrigin();
    void setOrigin(final String origin);
    String getTranslation();
    void setTranslation(final String translated);
    ContentValues toContentValues();
}
