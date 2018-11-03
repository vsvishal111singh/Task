package com.task.utilites;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.sayexpert.MyApplication;

public class ApiUtils  {

     private ApiUtils() {}

     public static final String deviceid =  Settings.Secure.getString(MyApplication.getAppContext().getContentResolver(), Settings.Secure.ANDROID_ID);
     // public static final String fcmid =  FirebaseInstanceId.getInstance().getToken();
     public static final String authkey =  "54b4c75c1209a4eb2f71933651570591";
     public static final String KEY_LOCATION1= "location1";
     public static final String KEY_LOCATION2= "location2";
     public static final String filterarray = "filterdata";
     public static final String serviceoffercount = "offercount";
     public static Snackbar snackbar;

     public static boolean isNetworkAvalilable(){

        ConnectivityManager cm = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkinfo = cm.getActiveNetworkInfo();
        return activeNetworkinfo!=null&& activeNetworkinfo.isConnected();
     }


     public static void showSnackbar(final DrawerLayout loginlayout, String msg)
     {
          snackbar = Snackbar.make(loginlayout, msg, Snackbar.LENGTH_INDEFINITE)
                  .setAction("Close", new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                            snackbar.dismiss();
                       }
                  });
          snackbar.setActionTextColor(Color.RED);
          snackbar.show();
     }

     public static void showSnackbar(final RelativeLayout loginlayout, String msg)
     {
          snackbar = Snackbar.make(loginlayout, msg, Snackbar.LENGTH_INDEFINITE)
                  .setAction("Close", new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                            snackbar.dismiss();
                       }
                  });
          snackbar.setActionTextColor(Color.RED);
          snackbar.show();
     }

}
