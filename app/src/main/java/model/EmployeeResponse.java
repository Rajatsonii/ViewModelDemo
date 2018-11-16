package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 26-09-2018.
 */
public class EmployeeResponse extends BaseResponse<EmployeeResponse> {

    @SerializedName("splash")
    private SplashResponse splashResponse;

    public SplashResponse getSplashResponse() {
        return splashResponse;
    }

    public void setSplashResponse(SplashResponse splashResponse) {
        this.splashResponse = splashResponse;
    }
}
