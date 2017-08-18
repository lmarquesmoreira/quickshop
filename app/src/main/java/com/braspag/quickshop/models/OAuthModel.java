package com.braspag.quickshop.models;

/**
 * Created by lmmoreira on 8/18/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OAuthModel implements Serializable, Parcelable
{

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    public final static Parcelable.Creator<OAuthModel> CREATOR = new Creator<OAuthModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OAuthModel createFromParcel(Parcel in) {
            OAuthModel instance = new OAuthModel();
            instance.accessToken = ((String) in.readValue((String.class.getClassLoader())));
            instance.tokenType = ((String) in.readValue((String.class.getClassLoader())));
            instance.expiresIn = ((int) in.readValue((int.class.getClassLoader())));
            instance.clientId = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public OAuthModel[] newArray(int size) {
            return (new OAuthModel[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4879377965728826171L;

    /**
     * No args constructor for use in serialization
     *
     */
    public OAuthModel() {
    }

    /**
     *
     * @param tokenType
     * @param accessToken
     * @param expiresIn
     * @param clientId
     */
    public OAuthModel(String accessToken, String tokenType, int expiresIn, String clientId) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accessToken);
        dest.writeValue(tokenType);
        dest.writeValue(expiresIn);
        dest.writeValue(clientId);
    }

    public int describeContents() {
        return 0;
    }

}