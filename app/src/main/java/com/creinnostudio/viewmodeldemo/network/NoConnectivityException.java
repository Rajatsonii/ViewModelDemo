package com.creinnostudio.viewmodeldemo.network;

import java.io.IOException;

/**
 * Created by Developer on 11-09-2018.
 */
public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
