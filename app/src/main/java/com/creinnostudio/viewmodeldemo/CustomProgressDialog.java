package com.creinnostudio.viewmodeldemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.ProgressBar;


/**
 * Created by Rajat on 01-03-2018.
 */

public class CustomProgressDialog extends ProgressDialog {

    public CustomProgressDialog(Context context) {
        super(context);

    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
            .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setIndeterminate(true);
        setCancelable(false);
        Drawable drawable = new ProgressBar(getContext()).getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent),
            PorterDuff.Mode.SRC_IN);
        setIndeterminateDrawable(drawable);
        setCanceledOnTouchOutside(false);
        setMessage(getContext().getString(R.string.msg_please_wait));
    }

}

