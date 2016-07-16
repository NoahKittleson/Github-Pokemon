package com.epicodus.wordgame.models;

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
    private String mUrlAddress;
    private String pushId;

    public Repo () {}

    public Repo (String name, int size, String lang1, String lang2, String url) {
        this.mName = name;
        this.mSize = size;
        this.mLangPrimary = lang1;
        this.mLangSecondary = lang2;
        this.mUrlAddress = url;
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

    public String getUrlAddress() { return  mUrlAddress; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


}