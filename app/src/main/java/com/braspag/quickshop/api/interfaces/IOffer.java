package com.braspag.quickshop.api.interfaces;

/**
 * Created by lmmoreira on 8/18/2017.
 */
import com.braspag.quickshop.models.Cart;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IOffer {

    @GET("api/public/cart/{id}")
    Call<Cart> GetOffer(@Header("Authorization") String authorization, @Path("id") int cartId);
}
