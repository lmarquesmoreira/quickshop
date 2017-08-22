package com.braspag.quickshop.api.async;

import android.os.AsyncTask;
import android.util.Log;

import com.braspag.quickshop.api.RestGenerator;
import com.braspag.quickshop.api.interfaces.IOAuth;
import com.braspag.quickshop.api.interfaces.IOffer;
import com.braspag.quickshop.models.Cart;
import com.braspag.quickshop.models.OAuthModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class OfferWithAuthAsync extends AsyncTask<Void, Void, Cart> {

    private IResultAsync resultAsyncCallback;
    private int cartId;

    public OfferWithAuthAsync(IResultAsync resultAsyncCallback, int cartId) {
        this.resultAsyncCallback = resultAsyncCallback;
        this.cartId = cartId;
        //"Bearer " + authorizationHeader;
    }

    @Override
    protected Cart doInBackground(Void... voids) {
        RestGenerator rest = new RestGenerator(RestGenerator.API_BASE_URL);

        Call<OAuthModel> requestAuthorization = rest.createService(IOAuth.class)
                .getAuthorizationHeader(RestGenerator.ClientId,
                        RestGenerator.ClientSecret,
                        RestGenerator.GrantType);


        Call<Cart> request = rest.createService(IOffer.class)
                .GetOffer("", this.cartId);

        Response<Cart> response = null;
        try {
            response = request.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(RestGenerator.LogAPP, e.getLocalizedMessage());

            resultAsyncCallback.send(new ResultAsyncModel<>().setException(e));

            return null;
        }
    }

    @Override
    protected void onPostExecute(Cart response) {
        super.onPostExecute(response);
        if (response != null) {
            resultAsyncCallback.send(new ResultAsyncModel(response));
        }
    }
}