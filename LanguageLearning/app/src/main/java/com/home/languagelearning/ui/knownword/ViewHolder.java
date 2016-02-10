package com.home.languagelearning.ui.knownword;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.home.languagelearning.R;
import com.home.languagelearning.model.ChineseToEnglishCard;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
/*package*/ class ViewHolder extends RecyclerView.ViewHolder {

    private TextView originTextView;
    private TextView translationTextView;

    public ViewHolder(View itemView) {
        super(itemView);
        originTextView = (TextView) itemView.findViewById(R.id.known_word_origin);
        translationTextView = (TextView) itemView.findViewById(R.id.known_word_translation);
    }

    public void setOriginWord(final String word) {
        originTextView.setText(word);
    }

    public void setTranslationWord(final String word) {
        translationTextView.setText(word);
    }

    public void update(ChineseToEnglishCard card) {
        setOriginWord(card.getOrigin());
        setTranslationWord(card.getTranslation());
    }
}
