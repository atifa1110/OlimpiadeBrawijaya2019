package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.ketentuan;

import android.os.Parcel;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Ketentuan extends ExpandableGroup<KetentuanContent> {

    public Ketentuan(String title, List<KetentuanContent> items) {
        super(title, items);
    }

    protected Ketentuan(Parcel in) {
        super(in);
    }
}