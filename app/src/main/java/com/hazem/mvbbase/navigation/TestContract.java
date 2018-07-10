package com.hazem.mvbbase.navigation;

import android.os.Parcel;

public class TestContract extends serializableContract {

    int id ;
    String name;

    @Override
    public String setKey() {
        return "testContract";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public TestContract() {
    }

    protected TestContract(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<TestContract> CREATOR = new Creator<TestContract>() {
        @Override
        public TestContract createFromParcel(Parcel source) {
            return new TestContract(source);
        }

        @Override
        public TestContract[] newArray(int size) {
            return new TestContract[size];
        }
    };
}
