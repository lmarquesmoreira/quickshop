package com.braspag.quickshop.api.models;

/**
 * Created by lmmoreira on 8/22/2017.
 */

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Order implements Serializable, Parcelable {

    public final static Parcelable.Creator<Order> CREATOR = new Creator<Order>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Order createFromParcel(Parcel in) {
            Order instance = new Order();
            return instance;
        }

        public Order[] newArray(int size) {
            return (new Order[size]);
        }

    };
    private final static long serialVersionUID = 1246637374328780243L;

    public void writeToParcel(Parcel dest, int flags) {
    }

    public int describeContents() {
        return 0;
    }

}