package com.home.languagelearning.ui.knownword;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.home.languagelearning.App;
import com.home.languagelearning.R;
import com.home.languagelearning.storage.Contract;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class LearnedWordsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private KnownWordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learned_words);

        adapter = new KnownWordsAdapter();

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(0, Bundle.EMPTY, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final Uri uri = Contract.contentUri(Contract.CardsTable.class);
        final String selection = Contract.CardsTable.LEARNED + "=?";
        final String[] selectionArgs = new String[] { "1" };
        return new CursorLoader(this, uri, null, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(App.TAG, "Known words: " + data.getCount());
        adapter.update(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.update(null);
    }
}
