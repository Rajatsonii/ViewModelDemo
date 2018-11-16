package com.creinnostudio.viewmodeldemo;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;


public class CustomDialog {

    private static CustomDialog INSTANCE;

    static {
        INSTANCE = new CustomDialog();
    }

    AlertDialog alertDialog;

    public static CustomDialog getInstance() {
        return INSTANCE;
    }

    public void ErrorDialog(Context mContext, String mMessage) {
        final AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(mContext,
            R.style.InvitationDialog);
        mAlertBuilder.setMessage(mMessage);
        mAlertBuilder.setPositiveButton(mContext.getString(android.R.string.ok), null);
        alertDialog = mAlertBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });
    }

    public interface DismissListenerWithStatus {

        public void onDismissed(String message);
    }

}
