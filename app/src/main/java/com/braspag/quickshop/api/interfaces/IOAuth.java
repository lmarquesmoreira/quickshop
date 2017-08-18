package com.braspag.quickshop.api.interfaces;

import com.braspag.quickshop.models.OAuthModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public interface IOAuth {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<OAuthModel> getAuthorizationHeader(@Field("client_id") String clientId,
                                            @Field("client_secret") String clientSecret,
                                            @Field("grant_type") String grantType);
}
