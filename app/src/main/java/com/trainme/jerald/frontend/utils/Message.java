package com.trainme.jerald.frontend.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;

public class Message {
    public static void showSnackBar(String message, Context context, View view) {
        Snackbar snackbar = Snackbar.make(view.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, R.color.white_message));
        snackbar.show();
    }
}
