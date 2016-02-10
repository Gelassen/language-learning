package com.home.languagelearning.storage.datasource;

import android.database.Cursor;

import com.home.languagelearning.BuildConfig;
import com.home.languagelearning.storage.mappers.IDataMapper;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public abstract class CursorDataSource<T> implements DataSource {

    private IDataMapper<T> dataMapper;
    private Cursor cursor;

    public CursorDataSource(IDataMapper dataMapper, Cursor cursor) {
        this.dataMapper = dataMapper;
        this.cursor = cursor;

        if (BuildConfig.DEBUG && dataMapper == null) {
            throw new IllegalArgumentException("Did you forget to add DataMapper for this DataSource?");
        }
    }

    @Override
    public int getCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override
    public void update(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public T getForPosition(int pos) {
        return dataMapper.createFromCursor(cursor, pos);
    }
}
