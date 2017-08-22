package com.braspag.quickshop.api.models;

/**
 * Created by lmmoreira on 8/22/2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller implements Serializable, Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    public final static Parcelable.Creator<Seller> CREATOR = new Creator<Seller>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Seller createFromParcel(Parcel in) {
            Seller instance = new Seller();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Seller[] newArray(int size) {
            return (new Seller[size]);
        }

    };
    private final static long serialVersionUID = 1962284825955418076L;

    /**
     * No args constructor for use in serialization
     */
    public Seller() {
    }

    /**
     * @param id
     */
    public Seller(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seller withId(int id) {
        this.id = id;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}