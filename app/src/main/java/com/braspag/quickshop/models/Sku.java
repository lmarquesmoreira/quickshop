package com.braspag.quickshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sku implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Sku")
    @Expose
    private String sku;
    @SerializedName("Status")
    @Expose
    private int status;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Images")
    @Expose
    private List<String> images = new ArrayList<String>();
    public final static Parcelable.Creator<Sku> CREATOR = new Creator<Sku>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sku createFromParcel(Parcel in) {
            Sku instance = new Sku();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.sku = ((String) in.readValue((String.class.getClassLoader())));
            instance.status = ((int) in.readValue((int.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.images, (java.lang.String.class.getClassLoader()));
            return instance;
        }

        public Sku[] newArray(int size) {
            return (new Sku[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5634765524463633083L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sku() {
    }

    /**
     *
     * @param id
     * @param status
     * @param description
     * @param images
     * @param sku
     */
    public Sku(int id, String sku, int status, String description, List<String> images) {
        super();
        this.id = id;
        this.sku = sku;
        this.status = status;
        this.description = description;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(sku);
        dest.writeValue(status);
        dest.writeValue(description);
        dest.writeList(images);
    }

    public int describeContents() {
        return 0;
    }

}