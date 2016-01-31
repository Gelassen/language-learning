package com.home.languagelearning.ui.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ICard;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private List<ChineseToEnglishCard> datasource = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        ChineseToEnglishCard card = datasource.get(position);
        return PlaceholderFragment.newInstance(card);
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    public void updateDatasource(final List<ChineseToEnglishCard> data) {
        datasource.clear();
        datasource.addAll(data == null ? new ArrayList<ChineseToEnglishCard>() : data);
        notifyDataSetChanged();
    }

}
