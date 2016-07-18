package com.epicodus.wordgame.models;

/**
 * Created by noahkittleson on 7/8/16.
 */

import org.parceler.Parcel;

@Parcel
public class Repo {
    private String Name;
    private int Size;
    private String LangPrimary;
    private String LangSecondary;
    private String UrlAddress;
    private String pushId;

    public Repo () {}

    public Repo (String name, int size, String lang1, String lang2, String url) {
        this.Name = name;
        this.Size = size;
        this.LangPrimary = lang1;
        this.LangSecondary = lang2;
        this.UrlAddress = url;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public String getLangPrimary() {
        return LangPrimary;
    }

    public void setLangPrimary(String langPrimary) {
        LangPrimary = langPrimary;
    }

    public String getLangSecondary() {
        return LangSecondary;
    }

    public void setLangSecondary(String langSecondary) {
        LangSecondary = langSecondary;
    }

    public String getUrlAddress() {
        return UrlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        UrlAddress = urlAddress;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}