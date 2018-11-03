package com.task.utilites;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sayexpert.MyApplication;


public class NetConnection {

//    Context context;
//
//    NetConnection(Context context){
//        this.context = context;
//    }

    public static boolean isNetworkAvalilable(){

        ConnectivityManager cm = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkinfo = cm.getActiveNetworkInfo();
        return activeNetworkinfo!=null&& activeNetworkinfo.isConnected();
    }



}
