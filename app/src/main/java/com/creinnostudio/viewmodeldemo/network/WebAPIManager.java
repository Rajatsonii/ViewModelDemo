package com.creinnostudio.viewmodeldemo.network;


import model.EmployeeResponse;

/**
 * Created by Developer on 11-09-2018.
 */
public class WebAPIManager {

    private static WebAPIManager INSTANCE;

    private final WebAPIService mService;

    private WebAPIManager() {
        mService = WebAPIServiceFactory.newInstance().makeServiceFactory();
    }

    public static WebAPIManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebAPIManager();
        }
        return INSTANCE;
    }

    public void getEmployeeResponse(RemoteCallback<EmployeeResponse, EmployeeResponse> listener) {
        mService.getgetEmployeeResponse().enqueue(listener);
    }


}