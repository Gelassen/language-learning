package com.home.languagelearning.ui.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.languagelearning.R;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.Page;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_CARD = ".CARD";
    private static final String ARG_PAGE = ".PAGE";

    private static final String FMT_PAGE = "%s/%s";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(ChineseToEnglishCard card, Page page) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD, card);
        args.putParcelable(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        ChineseToEnglishCard card = args.getParcelable(ARG_CARD);
        Page page = args.getParcelable(ARG_PAGE);

        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        TextView origin = (TextView) rootView.findViewById(R.id.origin);
        origin.setText(card.getOrigin());

        TextView translation = (TextView) rootView.findViewById(R.id.translation);
        translation.setText(card.getTranslation());

        TextView pageView = (TextView) rootView.findViewById(R.id.page);
        pageView.setText(String.format(FMT_PAGE, page.getCurrent(), page.getTotal()));
        return rootView;
    }
}
