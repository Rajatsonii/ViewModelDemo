package com.creinnostudio.viewmodeldemo.network;

import com.creinnostudio.viewmodeldemo.BuildConfig;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Developer on 11-09-2018.
 */

public class WebAPIServiceFactory {

    private static final int HTTP_READ_TIMEOUT = 10000;

    private static final int HTTP_CONNECT_TIMEOUT = 6000;

    private static WebAPIServiceFactory INSTANCE;

    public static WebAPIServiceFactory newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebAPIServiceFactory();
        }
        return INSTANCE;
    }

    public WebAPIService makeServiceFactory() {
        return makeServiceFactory(makeOkHttpClient());
    }

    private WebAPIService makeServiceFactory(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("API_URL")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

        return retrofit.create(WebAPIService.class);
    }

    private OkHttpClient makeOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.interceptors().add(new Interceptor() {
                                                 @Override
                                                 public okhttp3.Response intercept(@NonNull Chain
                                                     chain) throws IOException {
                                                     Request original = chain.request();
                                                     // Customize the request
                                                     Request.Builder request = original.newBuilder()
                                                         .header("Content-Type",
                                                             "application/x-www-form-urlencoded");
                                                     request = request.method(original.method(),
                                                         original.body());

                                                     // Customize or return the response
                                                     return chain.proceed(request.build());
                                                 }
                                             }
        );
        httpClientBuilder.addInterceptor(loggingInterceptor());
        return httpClientBuilder.build();
    }

    private HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
            : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

}