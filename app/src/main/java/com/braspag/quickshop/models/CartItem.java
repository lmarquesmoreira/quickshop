package com.braspag.quickshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CartItem implements Serializable, Parcelable {

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("IsActive")
    @Expose
    private boolean isActive;
    @SerializedName("ProductId")
    @Expose
    private int productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ManufacturerName")
    @Expose
    private String manufacturerName;
    @SerializedName("MerchantId")
    @Expose
    private String merchantId;
    @SerializedName("MerchantName")
    @Expose
    private String merchantName;
    @SerializedName("Quantity")
    @Expose
    private int quantity;
    @SerializedName("Price")
    @Expose
    private Price price;
    @SerializedName("Sku")
    @Expose
    private Sku sku;
    @SerializedName("Caption")
    @Expose
    private String caption;
    public final static Parcelable.Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @SuppressWarnings({
                "unchecked"
        })
        public CartItem createFromParcel(Parcel in) {
            CartItem instance = new CartItem();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.isActive = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.productId = ((int) in.readValue((int.class.getClassLoader())));
            instance.productName = ((String) in.readValue((String.class.getClassLoader())));
            instance.manufacturerName = ((String) in.readValue((String.class.getClassLoader())));
            instance.merchantId = ((String) in.readValue((String.class.getClassLoader())));
            instance.merchantName = ((String) in.readValue((String.class.getClassLoader())));
            instance.quantity = ((int) in.readValue((int.class.getClassLoader())));
            instance.price = ((Price) in.readValue((Price.class.getClassLoader())));
            instance.sku = ((Sku) in.readValue((Sku.class.getClassLoader())));
            instance.caption = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CartItem[] newArray(int size) {
            return (new CartItem[size]);
        }

    };
    private final static long serialVersionUID = -193087021977864849L;

    /**
     * No args constructor for use in serialization
     */
    public CartItem() {
    }

    /**
     * @param isActive
     * @param id
     * @param price
     * @param merchantName
     * @param caption
     * @param quantity
     * @param merchantId
     * @param manufacturerName
     * @param sku
     * @param productName
     * @param productId
     */
    public CartItem(int id, boolean isActive, int productId, String productName, String manufacturerName, String merchantId, String merchantName, int quantity, Price price, Sku sku, String caption) {
        super();
        this.id = id;
        this.isActive = isActive;
        this.productId = productId;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.quantity = quantity;
        this.price = price;
        this.sku = sku;
        this.caption = caption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(isActive);
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(manufacturerName);
        dest.writeValue(merchantId);
        dest.writeValue(merchantName);
        dest.writeValue(quantity);
        dest.writeValue(price);
        dest.writeValue(sku);
        dest.writeValue(caption);
    }

    public int describeContents() {
        return 0;
    }
}
