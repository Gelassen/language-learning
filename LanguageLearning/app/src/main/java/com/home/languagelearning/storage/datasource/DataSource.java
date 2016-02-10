package com.home.languagelearning.storage.datasource;

import android.database.Cursor;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public interface DataSource<T> {
    int getCount();
    void update(final Cursor cursor);
    T getForPosition(final int pos);
}
