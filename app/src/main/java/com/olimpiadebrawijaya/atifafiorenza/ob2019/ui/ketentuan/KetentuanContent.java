package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.ketentuan;

import android.os.Parcel;
import android.os.Parcelable;

public class KetentuanContent implements Parcelable {

    private String name;

    public KetentuanContent(String name) {
        this.name = name;
    }

    protected KetentuanContent(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    public static final Creator<KetentuanContent> CREATOR = new Creator<KetentuanContent>() {
        @Override
        public KetentuanContent createFromParcel(Parcel in) {
            return new KetentuanContent(in);
        }

        @Override
        public KetentuanContent[] newArray(int size) {
            return new KetentuanContent[size];
        }
    };

}
