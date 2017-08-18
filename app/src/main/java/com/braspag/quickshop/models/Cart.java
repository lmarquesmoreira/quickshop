package com.braspag.quickshop.models;

/**
 * Created by lmmoreira on 8/18/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;
    @SerializedName("UpdatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Type")
    @Expose
    private int type;
    @SerializedName("MerchantId")
    @Expose
    private String merchantId;
    @SerializedName("MerchantName")
    @Expose
    private String merchantName;
    @SerializedName("Label")
    @Expose
    private String label;
    @SerializedName("CartItems")
    @Expose
    private List<CartItem> cartItems = new ArrayList<CartItem>();
    public final static Parcelable.Creator<Cart> CREATOR = new Creator<Cart>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cart createFromParcel(Parcel in) {
            Cart instance = new Cart();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.createdOn = ((String) in.readValue((String.class.getClassLoader())));
            instance.updatedOn = ((String) in.readValue((String.class.getClassLoader())));
            instance.userId = ((String) in.readValue((String.class.getClassLoader())));
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((int) in.readValue((int.class.getClassLoader())));
            instance.merchantId = ((String) in.readValue((String.class.getClassLoader())));
            instance.merchantName = ((String) in.readValue((String.class.getClassLoader())));
            instance.label = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.cartItems, (com.braspag.quickshop.models.CartItem.class.getClassLoader()));
            return instance;
        }

        public Cart[] newArray(int size) {
            return (new Cart[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8580547208958180473L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Cart() {
    }

    /**
     *
     * @param cartItems
     * @param createdOn
     * @param id
     * @param userId
     * @param merchantName
     * @param userName
     * @param label
     * @param merchantId
     * @param type
     * @param updatedOn
     */
    public Cart(int id, String createdOn, String updatedOn, String userId, String userName, int type, String merchantId, String merchantName, String label, List<CartItem> cartItems) {
        super();
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.userId = userId;
        this.userName = userName;
        this.type = type;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.label = label;
        this.cartItems = cartItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(createdOn);
        dest.writeValue(updatedOn);
        dest.writeValue(userId);
        dest.writeValue(userName);
        dest.writeValue(type);
        dest.writeValue(merchantId);
        dest.writeValue(merchantName);
        dest.writeValue(label);
        dest.writeList(cartItems);
    }

    public int describeContents() {
        return 0;
    }

}
