package com.example.pdfreader;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.pdfreader.domain.Document;

public class Utils {
    ProgressDialog showProgressDialog(Context context){
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        return progress;
    }

}
