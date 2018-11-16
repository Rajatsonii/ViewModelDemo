package com.creinnostudio.viewmodeldemo;

import com.creinnostudio.viewmodeldemo.viewmodel.EmployeeViewModel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import model.EmployeeResponse;

public class MainActivity extends BaseActivity {

    EmployeeViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        attachObserver();
    }

    private void attachObserver() {
        model.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

                if (aBoolean) showLoading();
                else hideLoading();
            }
        });
        model.getApiError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                CustomDialog.getInstance().ErrorDialog(MainActivity.this, message);
            }
        });

        model.getEmployees().observe(this, new Observer<EmployeeResponse>() {
            @Override
            public void onChanged(@Nullable EmployeeResponse employeeResponse) {
                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
