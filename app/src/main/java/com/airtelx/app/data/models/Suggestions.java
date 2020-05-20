package com.airtelx.app.data.models;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class Suggestions implements SearchSuggestion {

    private String addressName;
    //private boolean mIsHistory = false;

    public Suggestions(String suggestion) {
        this.addressName = suggestion.toLowerCase();
    }

    public Suggestions(Parcel source) {
        this.addressName = source.readString();
        //this.mIsHistory = source.readInt() != 0;
    }


    @Override
    public String getBody() {
        return addressName;
    }

    public static final Creator<Suggestions> CREATOR = new Creator<Suggestions>() {
        @Override
        public Suggestions createFromParcel(Parcel in) {
            return new Suggestions(in);
        }

        @Override
        public Suggestions[] newArray(int size) {
            return new Suggestions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressName);
    }
}
