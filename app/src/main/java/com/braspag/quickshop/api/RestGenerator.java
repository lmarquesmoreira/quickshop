package com.braspag.quickshop.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lmmoreira on 8/18/2017.
 */

public class RestGenerator {
    public final static String LogAPP = "CIELO_SHOP";

    public final static String ClientId ="8EC30900-65CD-4D4F-AD95-B724E0DEE5B1";
    public final static String ClientSecret = "Jz9yHFCF-eAP1-fwXKKpTGcUvpC5V7opXXfm0dxQ0kg";
    public final static String GrantType = "client_credentials";

    private final String API_BASE_URL = "https://api.brasp.ag/";
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;

    public RestGenerator() {
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S createService(Class<S> service) {
        try {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {

                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.header("Accept", "application/json");
                    requestBuilder.method(original.method(), original.body());

                    Request request = requestBuilder.build();

                    return chain.proceed(request);
                }
            });
        } catch (Exception e) {
            Log.d(LogAPP, e.getLocalizedMessage());
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(service);
    }
}
