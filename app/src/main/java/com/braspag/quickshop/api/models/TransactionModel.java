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

import com.braspag.quickshop.models.Cart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionModel implements Serializable, Parcelable {


    @SerializedName("amount")
    @Expose
    private long amount;
    @SerializedName("sellers")
    @Expose
    private List<Seller> sellers = new ArrayList<Seller>();
    @SerializedName("order")
    @Expose
    private cielo.orders.domain.Order order;

    @SerializedName("offer")
    @Expose
    private Cart offer;

    public final static Parcelable.Creator<TransactionModel> CREATOR = new Creator<TransactionModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TransactionModel createFromParcel(Parcel in) {
            TransactionModel instance = new TransactionModel();
            instance.amount = ((long) in.readValue((long.class.getClassLoader())));
            in.readList(instance.sellers, (Seller.class.getClassLoader()));
            instance.order = ((cielo.orders.domain.Order) in.readValue((cielo.orders.domain.Order.class.getClassLoader())));
            instance.offer = ((Cart) in.readValue(Cart.class.getClassLoader()));
            return instance;
        }

        public TransactionModel[] newArray(int size) {
            return (new TransactionModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1855912649348483710L;

    /**
     * No args constructor for use in serialization
     *
     */
    public TransactionModel() {
    }

    /**
     *
     * @param sellers
     * @param amount
     * @param order
     */
    public TransactionModel(long amount, List<Seller> sellers, cielo.orders.domain.Order order, Cart offer) {
        super();
        this.amount = amount;
        this.sellers = sellers;
        this.order = order;
        this.offer = offer;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public TransactionModel withAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public TransactionModel withSellers(List<Seller> sellers) {
        this.sellers = sellers;
        return this;
    }

    public cielo.orders.domain.Order getOrder() {
        return order;
    }

    public void setOrder(cielo.orders.domain.Order order) {
        this.order = order;
    }

    public TransactionModel withOrder(cielo.orders.domain.Order order) {
        this.order = order;
        return this;
    }

    public Cart getOffer() {
        return offer;
    }

    public void setOffer(Cart offer) {
        this.offer = offer;
    }

    public TransactionModel withOffer(Cart offer) {
        this.offer = offer;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(amount);
        dest.writeList(sellers);
        dest.writeValue(order);
        dest.writeValue(offer);
    }

    public int describeContents() {
        return 0;
    }

}