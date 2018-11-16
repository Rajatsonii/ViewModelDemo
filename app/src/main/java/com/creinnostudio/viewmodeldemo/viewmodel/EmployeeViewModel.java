package com.creinnostudio.viewmodeldemo.viewmodel;


import com.creinnostudio.viewmodeldemo.network.RemoteCallback;
import com.creinnostudio.viewmodeldemo.network.WebAPIManager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import model.EmployeeResponse;

public class EmployeeViewModel extends ViewModel {

    private MutableLiveData<EmployeeResponse> employeeList;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();

    private MutableLiveData<Boolean> isNoInternet = new MutableLiveData<Boolean>();

    private MutableLiveData<String> apiError = new MutableLiveData<String>();


    public LiveData<EmployeeResponse> getEmployees() {
        if (employeeList == null) {
            employeeList = new MutableLiveData<EmployeeResponse>();
            callEmployeeAPI();
        }
        return employeeList;
    }


    public void callEmployeeAPI() {
        isLoading.setValue(true);
        WebAPIManager.getInstance()
            .getEmployeeResponse(new RemoteCallback<EmployeeResponse, EmployeeResponse>() {
                @Override
                public void onSuccess(EmployeeResponse response) {
                    isLoading.setValue(false);
                    employeeList.setValue(response);
                }

                @Override
                public void onFailed(Throwable throwable) {
                    isLoading.setValue(false);
                    apiError.setValue(throwable.getLocalizedMessage());
                }

                @Override
                public void onInternetFailed() {
                    isLoading.setValue(false);
                    isNoInternet.setValue(false);
                }

                @Override
                public void onEmptyResponse(String message) {
                    isLoading.setValue(false);
                    apiError.setValue(message);
                }
            });
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    public MutableLiveData<Boolean> getIsNoInternet() {
        return isNoInternet;
    }

    public void setIsNoInternet(MutableLiveData<Boolean> isNoInternet) {
        this.isNoInternet = isNoInternet;
    }

    public MutableLiveData<String> getApiError() {
        return apiError;
    }

    public void setApiError(MutableLiveData<String> apiError) {
        this.apiError = apiError;
    }
}
