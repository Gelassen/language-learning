package com.home.languagelearning.ui.knownword;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.home.languagelearning.R;
import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.storage.datasource.KnownWordsDataSource;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class KnownWordsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private KnownWordsDataSource datasource;

    public KnownWordsAdapter() {
        datasource = new KnownWordsDataSource(null);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            default:
                View view = parent.inflate(parent.getContext(), R.layout.component_item_known_word, parent);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChineseToEnglishCard card = datasource.getForPosition(position);
        holder.update(card);
    }

    @Override
    public int getItemCount() {
        return datasource.getCount();
    }

    public void update(Cursor cursor) {
        datasource.update(cursor);
    }
}
