package com.epicodus.wordgame.models;

/**
 * Created by noahkittleson on 7/22/16.
 */

import org.parceler.Parcel;

@Parcel
public class Pokemon {
    private String name;
    private String typePrimary;
    private String typeSecondary;
    private String imgURL;

    private int HP;
    private int attack;
    private int defense;
    private String pushId;

    public Pokemon () {}

    public Pokemon (String name, String lang1, String lang2, String imgURL, int hp, int attack, int defense) {
        this.name = name;
        this.HP = hp;
        this.imgURL = imgURL;
        this.typePrimary = lang1;
        this.typeSecondary = lang2;
    }

    public Pokemon (Repo repo) {
        this.name = repo.getName();
        this.typePrimary = repo.getLangPrimary();
        this.typeSecondary = repo.getLangSecondary();
        this.imgURL = "http://media-cerulean.cursecdn.com/avatars/238/642/25.png.png";
        this.HP = repo.getSize();
        this.attack = 50;
        this.defense = 50;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypePrimary() {
        return typePrimary;
    }

    public void setTypePrimary(String typePrimary) {
        this.typePrimary = typePrimary;
    }

    public String getTypeSecondary() {
        return typeSecondary;
    }

    public void setTypeSecondary(String typeSecondary) {
        this.typeSecondary = typeSecondary;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
