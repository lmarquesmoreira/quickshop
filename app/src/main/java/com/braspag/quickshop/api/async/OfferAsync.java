package com.braspag.quickshop.api.async;

import android.os.AsyncTask;
import android.util.Log;

import com.braspag.quickshop.api.RestGenerator;
import com.braspag.quickshop.api.interfaces.IOffer;
import com.braspag.quickshop.models.Cart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class OfferAsync extends AsyncTask<Object, Object, Void> {

    private IResultAsync resultAsyncCallback;
    private int cartId;
    private String authorizationHeader;

    public OfferAsync(IResultAsync resultAsyncCallback, String authorizationHeader, int cartId) {
        this.resultAsyncCallback = resultAsyncCallback;
        this.cartId = cartId;
        this.authorizationHeader = "Bearer " + authorizationHeader;
    }

    @Override
    protected Void doInBackground(Object... objects) {
        RestGenerator rest = new RestGenerator();
        Call<Cart> request = rest.createService(IOffer.class)
                .GetOffer(this.authorizationHeader, this.cartId);

        request.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    resultAsyncCallback.send(new ResultAsyncModel(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                t.printStackTrace();
                Log.d(RestGenerator.LogAPP, t.getLocalizedMessage());

                resultAsyncCallback.send(new ResultAsyncModel<>());

            }
        });
        return null;
    }
}
