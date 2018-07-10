package com.hazem.mvbbase.navigation;

import android.os.Parcelable;

public abstract class serializableContract implements Parcelable {

    private final String key;

    public serializableContract(){
        this.key = setKey();
    }

    public abstract String setKey();

    public String getKey() {
        return key;
    }
}
