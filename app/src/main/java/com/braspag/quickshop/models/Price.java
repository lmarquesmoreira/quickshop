package com.braspag.quickshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Price implements Serializable, Parcelable
{

    @SerializedName("Amount")
    @Expose
    private int amount;
    @SerializedName("Currency")
    @Expose
    private String currency;
    public final static Parcelable.Creator<Price> CREATOR = new Creator<Price>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Price createFromParcel(Parcel in) {
            Price instance = new Price();
            instance.amount = ((int) in.readValue((int.class.getClassLoader())));
            instance.currency = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Price[] newArray(int size) {
            return (new Price[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4181583269486383929L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Price() {
    }

    /**
     *
     * @param amount
     * @param currency
     */
    public Price(int amount, String currency) {
        super();
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amount);
        dest.writeValue(currency);
    }

    public int describeContents() {
        return 0;
    }

}