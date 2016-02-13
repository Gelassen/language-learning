package com.home.languagelearning.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.home.languagelearning.storage.Contract;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class ChineseToEnglishCard implements ICard, Parcelable {
    private int id;
    private String chinesePinyin;
    private String english;
    private boolean knownCard;

    public ChineseToEnglishCard(){

    }

    protected ChineseToEnglishCard(Parcel in) {
        id = in.readInt();
        chinesePinyin = in.readString();
        english = in.readString();
        knownCard = in.readInt() == 1;// 1 means boolean "true" from storage layer
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getOrigin() {
        return chinesePinyin;
    }

    @Override
    public void setOrigin(String origin) {
        this.chinesePinyin = origin;
    }

    @Override
    public String getTranslation() {
        return english;
    }

    @Override
    public void setTranslation(String translated) {
        this.english = translated;
    }

    public void markAsKnown(boolean known) {
        knownCard = known;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(3);
        contentValues.put(Contract.CardsTable.CHINESE, chinesePinyin);
        contentValues.put(Contract.CardsTable.ENGLISH, english);
        contentValues.put(Contract.CardsTable.LEARNED, knownCard ? 1 : 0);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(chinesePinyin);
        dest.writeString(english);
        dest.writeInt(knownCard ? 1 : 0);
    }


    public static final Creator<ChineseToEnglishCard> CREATOR = new Creator<ChineseToEnglishCard>() {
        @Override
        public ChineseToEnglishCard createFromParcel(Parcel in) {
            return new ChineseToEnglishCard(in);
        }

        @Override
        public ChineseToEnglishCard[] newArray(int size) {
            return new ChineseToEnglishCard[size];
        }
    };
}
