package com.braspag.quickshop.api.async;

import android.os.AsyncTask;
import android.util.Log;

import com.braspag.quickshop.api.RestGenerator;
import com.braspag.quickshop.api.interfaces.IOAuth;
import com.braspag.quickshop.models.OAuthModel;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class OAuthAsync extends AsyncTask<Void, Void, OAuthModel> {

    private IResultAsync<OAuthModel> resultAsyncCallback;

    public OAuthAsync(IResultAsync<OAuthModel> resultAsyncCallback) {
        this.resultAsyncCallback = resultAsyncCallback;
    }

    @Override
    protected OAuthModel doInBackground(Void... voids) {
        RestGenerator rest = new RestGenerator();
        Call<OAuthModel> request = rest.createService(IOAuth.class)
                .getAuthorizationHeader(RestGenerator.ClientId,
                        RestGenerator.ClientSecret,
                        RestGenerator.GrantType);

        Response<OAuthModel> response = null;
        try {
            response = request.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(RestGenerator.LogAPP, e.getLocalizedMessage());

            resultAsyncCallback.send(new ResultAsyncModel<OAuthModel>().setException(e));

            return null;
        }
    }

    @Override
    protected void onPostExecute(OAuthModel response) {
        super.onPostExecute(response);
        if (response != null) {
            resultAsyncCallback.send(new ResultAsyncModel<>(response));
        }
    }
}
