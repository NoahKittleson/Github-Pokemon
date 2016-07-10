package com.epicodus.wordgame;

/**
 * Created by noahkittleson on 7/8/16.
 */

import org.parceler.Parcel;

@Parcel
public class Repo {
    private String mName;
    private int mSize;
    private String mLangPrimary;
    private String mLangSecondary;

    public Repo () {}

    public Repo (String name, int size, String lang1, String lang2) {
        this.mName = name;
        this.mSize = size;
        this.mLangPrimary = lang1;
        this.mLangSecondary = lang2;
    }

    public String getLanguageTwo() {
        return mLangSecondary;
    }

    public String getName() {
        return mName;
    }

    public int getSize() {
        return mSize;
    }

    public String getLanguageOne() {
        return mLangPrimary;
    }


}