package com.home.languagelearning.storage.migration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.home.languagelearning.model.ChineseToEnglishCard;
import com.home.languagelearning.storage.Contract;
import com.home.languagelearning.storage.mappers.CardMapper;

import java.util.List;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class Migration {

    private List<ChineseToEnglishCard> cards;

    public void initByBackup(SQLiteDatabase db) {
        for (ChineseToEnglishCard card : cards) {
            ContentValues values = card.toContentValues();
            db.insert(Contract.CardsTable.class.getSimpleName(), null, values);
        }
    }

    public void backupInRuntimeMemory(SQLiteDatabase db) {
        Cursor cursor = db.query(Contract.CardsTable.class.getSimpleName(), null, null, null, null, null, null);
        CardMapper cardMapper = new CardMapper();
        cards = cardMapper.createListFromCursor(cursor);
        cursor.close();
    }

    public void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= 1) {
            StringBuilder sql = new StringBuilder();
            sql.append("ALTER ");
            sql.append(Contract.CardsTable.class.getSimpleName());
            sql.append(" ADD COLUMN ");
            sql.append(Contract.CardsTable.LEARNED);
            sql.append(" INTEGER");
            db.execSQL(sql.toString());
        }
    }
}
