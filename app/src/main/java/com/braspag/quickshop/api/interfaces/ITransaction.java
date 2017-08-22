package com.braspag.quickshop.api.interfaces;

import com.braspag.quickshop.api.models.TransactionModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lmmoreira on 8/22/2017.
 */

public interface ITransaction {
    @POST("api/split")
    Call<String> SendTransaction(@Body TransactionModel model);

}
