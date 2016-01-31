package com.home.languagelearning.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.home.languagelearning.storage.Contract;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class ChineseToEnglishCard implements ICard, Parcelable {
    private String chinesePinyin;
    private String english;

    public ChineseToEnglishCard(){

    }

    protected ChineseToEnglishCard(Parcel in) {
        chinesePinyin = in.readString();
        english = in.readString();
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

    @Override
    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put(Contract.CardsTable.CHINESE, chinesePinyin);
        contentValues.put(Contract.CardsTable.ENGLISH, english);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chinesePinyin);
        dest.writeString(english);
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
