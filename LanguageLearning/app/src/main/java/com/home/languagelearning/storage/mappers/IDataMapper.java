package com.home.languagelearning.storage.mappers;

import android.database.Cursor;

import java.util.List;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public interface IDataMapper<T> {
    int NOT_INITIALIZED = -1;
    T createFromCursor(final Cursor cursor);
    List<T> createListFromCursor(final Cursor cursor);
    void initializeIndexes(final Cursor cursor);
    boolean inputIsValid(final Cursor cursor);
}
