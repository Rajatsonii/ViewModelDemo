package model;

import java.io.Serializable;

/**
 * Created by Developer on 26-09-2018.
 */
public class
BaseResponse<T> implements Serializable {

    private boolean status;

    private String message;

    private T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
