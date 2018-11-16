package com.creinnostudio.viewmodeldemo.network;


import android.support.annotation.NonNull;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import model.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Developer on 11-09-2018.
 */
public abstract class RemoteCallback<T extends BaseResponse<M>, M> implements Callback<T> {

    private static final String TAG = "RemoteCallback";

    // Default error message
    private static final String DEFAULT_ERROR_MSG = "Sorry we are unable to reach server at this " +
        "time.";

    /**
     * Overrides onReponse method and handles response of servers and reacts accordingly.
     */
    @Override
    public final void onResponse(@NonNull Call<T> call, retrofit2.Response<T> response) {
        switch (response.code()) {
            case HttpsURLConnection.HTTP_OK:
            case HttpsURLConnection.HTTP_CREATED:
            case HttpsURLConnection.HTTP_ACCEPTED:
            case HttpsURLConnection.HTTP_NOT_AUTHORITATIVE:
                if (response.body() != null
                    && response.body().getData() != null) {
                    if (response.body().isStatus()) {
                        onSuccess(response.body().getData());
                    } else {
                        onFailed(new Throwable(response.body().getMessage()));
                    }
                } else {
                    if (response.body() != null) {
                        if (response.body().isStatus()) {
                            onEmptyResponse(response.body().getMessage());
                        } else {
                            onFailed(new Throwable(response.body().getMessage()));
                        }
                    } else {
                        onFailed(new Throwable(response.body().getMessage()));
                    }
                }
                break;
            case HttpURLConnection.HTTP_NO_CONTENT:
                onEmptyResponse(response.body().getMessage());
                break;
            default:
                onFailed(new Throwable(response.message()));
                break;
        }
    }

    /**
     * Overriding default onFailure method
     * this method will trigger onInternetFailed()
     */
    @Override
    public final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t instanceof NoConnectivityException) {
            //Add your code for displaying no network connection error
            onInternetFailed();
        } else {
            onFailed(new Throwable(DEFAULT_ERROR_MSG));
        }
    }

    /**
     * onSuccess will be called when response contains body
     */
    public abstract void onSuccess(M response);

    /**
     * onFailed will be called when error generated from server
     *
     * @param throwable message value will be dependend on servers error message
     *                  if message is not available from server than default error message will
     *                  be displayed.
     */
    public abstract void onFailed(Throwable throwable);

    /**
     * onInternetFailed() method will be called when
     * network connection is not available in device.
     */
    public abstract void onInternetFailed();

    /**
     * onEmptyResponse() method will be called when response from server is blank or
     * error code is 404 generated.
     */
    public abstract void onEmptyResponse(String message);

}
