package com.home.languagelearning.ui.gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.languagelearning.R;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.model.ICard;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_CARD = ".CARD";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(ChineseToEnglishCard card) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD, card);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        ChineseToEnglishCard card = args.getParcelable(ARG_CARD);

        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(card.getOrigin());
        return rootView;
    }
}
