package com.braspag.quickshop.api.async;

import android.os.AsyncTask;
import android.util.Log;

import com.braspag.quickshop.api.RestGenerator;
import com.braspag.quickshop.api.interfaces.IOffer;
import com.braspag.quickshop.api.interfaces.ITransaction;
import com.braspag.quickshop.api.models.TransactionModel;
import com.braspag.quickshop.models.Cart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lmmoreira on 8/22/2017.
 */

public class TransactionAsync extends AsyncTask<TransactionModel, Object, Void> {
    private IResultAsync resultAsyncCallback;

    public TransactionAsync(IResultAsync resultAsyncCallback) {
        this.resultAsyncCallback = resultAsyncCallback;
    }

    @Override
    protected Void doInBackground(TransactionModel... transactionModels) {
        RestGenerator rest = new RestGenerator(RestGenerator.API_SPLIT_BASE_URL);
        Call<String> request = rest.createService(ITransaction.class).SendTransaction(transactionModels[0]);

        request.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    resultAsyncCallback.send(new ResultAsyncModel(response.body()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Log.d(RestGenerator.LogAPP, t.getLocalizedMessage());
                resultAsyncCallback.send(new ResultAsyncModel<>());
            }
        });
        return null;
    }
}
