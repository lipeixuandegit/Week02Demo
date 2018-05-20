package com.example.administrator.week02demo.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2018/5/19.
 */

public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载...");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
