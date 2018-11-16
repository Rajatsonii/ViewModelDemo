package com.creinnostudio.viewmodeldemo.network;


import model.EmployeeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Developer on 11-09-2018.
 */
public interface WebAPIService {

    @GET("employees")
    Call<EmployeeResponse> getgetEmployeeResponse();
}