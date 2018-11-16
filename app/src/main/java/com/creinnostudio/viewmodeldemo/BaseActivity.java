package com.creinnostudio.viewmodeldemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Rajat on 17-02-2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showLoading() {
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog(this);
        }
        if (!customProgressDialog.isShowing()) {
            customProgressDialog.show();
        }
    }

    public void hideLoading() {
        if (customProgressDialog != null
            && customProgressDialog.isShowing()) {
            customProgressDialog.dismiss();
        }
    }

    public void showErrorDialog(String errMessage) {
        CustomDialog.getInstance().ErrorDialog(this, errMessage);
    }

    public void showErrorDialog(@StringRes int resourceId) {
        showErrorDialog(getString(resourceId));
    }

}
