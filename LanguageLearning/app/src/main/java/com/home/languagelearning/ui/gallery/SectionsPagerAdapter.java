package com.home.languagelearning.ui.gallery;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.home.languagelearning.domain.CardController;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ICard;
import com.home.languagelearning.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_SHIFT = 1; // const to convert position from array to user's page

    private List<ChineseToEnglishCard> datasource = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        ChineseToEnglishCard card = datasource.get(position);
        Page page = new Page();
        page.setCurrent(position + PAGE_SHIFT);
        page.setTotal(getCount());
        return PlaceholderFragment.newInstance(card, page);
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    @Override
    public int getItemPosition(Object object) {
        ChineseToEnglishCard card = ((PlaceholderFragment) object).getCard();
        int position = datasource.indexOf(card);
//        int cardId = card.getId();
        if (position >= 0) {
            return position;
        } else {
            return POSITION_NONE;
        }
//        return super.getItemPosition(object);
    }

    public ChineseToEnglishCard getDataItemForCurrentPosition(final ViewPager viewPager) {
        final int position = viewPager.getCurrentItem();
        if (position >= datasource.size()) {
            throw new IllegalStateException("Position is our of range");
        }
        return datasource.get(position);
    }

    public void updateDatasource(Context context, final List<ChineseToEnglishCard> data) {
        datasource.clear();
        datasource.addAll(data == null ? new ArrayList<ChineseToEnglishCard>() : data);

        CardController.broadcastPageInfo(context, data == null ? 0 : data.size());
        notifyDataSetChanged();
    }

}
