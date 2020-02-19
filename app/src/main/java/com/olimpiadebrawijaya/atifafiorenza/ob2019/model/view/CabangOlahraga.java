package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class CabangOlahraga {

    private int id;

    @DrawableRes
    private int caborIconRes;

    @StringRes
    private int caborNameRes;

    private String caborName;


    /**
     * @param isSelected : untuk multiple select di
     */

    private boolean isSelected;


    public CabangOlahraga(int id, @DrawableRes int caborIconRes, @StringRes int caborNameRes) {
        this.id = id;
        this.caborIconRes = caborIconRes;
        this.caborNameRes = caborNameRes;
    }

    public CabangOlahraga(int id, int caborIconRes, int caborNameRes, String caborName, boolean isSelected) {
        this.id = id;
        this.caborIconRes = caborIconRes;
        this.caborNameRes = caborNameRes;
        this.caborName = caborName;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DrawableRes
    public int getCaborIconRes() {
        return caborIconRes;
    }

    public void setCaborIconRes(@DrawableRes int caborIconRes) {
        this.caborIconRes = caborIconRes;
    }

    @StringRes
    public int getCaborNameRes() {
        return caborNameRes;
    }

    public void setCaborNameRes(@StringRes int caborNameRes) {
        this.caborNameRes = caborNameRes;
    }

    public String getCaborName() {
        return caborName;
    }

    public void setCaborName(String caborName) {
        this.caborName = caborName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CabangOlahraga) {
            CabangOlahraga cabor = (CabangOlahraga) obj;
            return this.caborIconRes == cabor.getCaborIconRes()
                    && this.caborName.equals(cabor.getCaborName())
                    && this.caborNameRes == cabor.getCaborNameRes();
        }
        return false;
    }
}
