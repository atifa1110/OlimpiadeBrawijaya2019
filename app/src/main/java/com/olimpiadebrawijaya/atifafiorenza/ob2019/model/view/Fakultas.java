package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class Fakultas {

    private int id;
    @DrawableRes
    private int fakultasIconRes;
    @StringRes
    private int fakultasNameRes;

    private String fakultasName;

    public Fakultas(int id, @DrawableRes int fakultasIconRes, @StringRes int fakultasNameRes) {
        this.id = id;
        this.fakultasIconRes = fakultasIconRes;
        this.fakultasNameRes = fakultasNameRes;
    }

    public Fakultas(int id, @DrawableRes int fakultasIconRes, @StringRes int fakultasNameRes, String fakultasName) {
        this.id = id;
        this.fakultasIconRes = fakultasIconRes;
        this.fakultasNameRes = fakultasNameRes;
        this.fakultasName = fakultasName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DrawableRes
    public int getFakultasIconRes() {
        return fakultasIconRes;
    }

    public void setFakultasIconRes(@DrawableRes int fakultasIconRes) {
        this.fakultasIconRes = fakultasIconRes;
    }

    @StringRes
    public int getFakultasNameRes() {
        return fakultasNameRes;
    }

    public void setFakultasNameRes(@StringRes int fakultasNameRes) {
        this.fakultasNameRes = fakultasNameRes;
    }

    public String getFakultasName() {
        return fakultasName;
    }

    public void setFakultasName(String fakultasName) {
        this.fakultasName = fakultasName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fakultas) {
            Fakultas fakultas = (Fakultas) obj;
            return this.fakultasIconRes == fakultas.getFakultasIconRes() &&
                    this.fakultasName.equals(fakultas.getFakultasName()) && //crash krn fakultasName null
                    this.fakultasNameRes == fakultas.getFakultasNameRes();
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
