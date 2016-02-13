package com.home.languagelearning.ui.gallery;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.home.languagelearning.App;
import com.home.languagelearning.R;
import com.home.languagelearning.domain.CardController;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.storage.Contract;
import com.home.languagelearning.storage.mappers.CardMapper;
import com.home.languagelearning.ui.knownword.LearnedWordsActivity;
import com.home.languagelearning.ui.newworld.AppDialogFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;

    private CardController cardController;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(0);

        cardController = new CardController(null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChineseToEnglishCard card = sectionsPagerAdapter.getDataItemForCurrentPosition(viewPager);
                cardController.updateCard(MainActivity.this, card);

                // update user with status
                Snackbar.make(view, "Word has been marked as learned and moved out from exercise", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportLoaderManager().initLoader(0, Bundle.EMPTY, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_new_word:
                AppDialogFragment.newInstance(Bundle.EMPTY)
                        .show(getSupportFragmentManager(), AppDialogFragment.TAG);
                break;
            case R.id.action_known_words:
                Intent intent = new Intent(this, LearnedWordsActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Contract.contentUri(Contract.CardsTable.class);
        final String selection = Contract.CardsTable.LEARNED + "=? OR " + Contract.CardsTable.LEARNED + " IS NULL";
        final String[] selectionArgs = new String[] { "0" };
        return new CursorLoader(this, uri, null, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(App.TAG, "Data size: " + data.getCount());
        CardMapper cardMapper = new CardMapper();
        List<ChineseToEnglishCard> cards = cardMapper.createListFromCursor(data);
        sectionsPagerAdapter.updateDatasource(this, cards);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        sectionsPagerAdapter.updateDatasource(this, null);
    }
}
