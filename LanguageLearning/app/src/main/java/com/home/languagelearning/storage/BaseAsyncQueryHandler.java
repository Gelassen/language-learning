package com.home.languagelearning.storage;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;


import com.home.languagelearning.storage.mappers.IDataMapper;

import java.util.List;


/**
 * Created by dmitry.kazakov on 6/4/2015.
 */
public class BaseAsyncQueryHandler extends AsyncQueryHandler {

    public BaseAsyncQueryHandler(ContentResolver cr) {
        super(cr);
    }

    public void update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        startUpdate(0, null, uri, contentValues, selection, selectionArgs);
    }

    public void insert(Uri uri, ContentValues contentValues) {
        startInsert(0, null, uri, contentValues);
    }

    public void delete(Uri uri, String selection, String[] selectionArgs) {
        startDelete(0, null, uri, selection, selectionArgs);
    }

    private IDataMapper dataMapper;
    private QueryListener listener;

    public void setListener(QueryListener listener, IDataMapper dataMapper) {
        this.listener = listener;
        this.dataMapper = dataMapper;
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
        if (listener != null && dataMapper != null) {
            List result = dataMapper.createListFromCursor(cursor);
            listener.onQueryComplete(result);
        }
        cursor.close();
    }

    public interface QueryListener<T> {
        void onQueryComplete(List<T> result);
    }

}
