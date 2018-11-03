package com.task;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

  private static Context mContext;


  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);

  }

  public void onCreate() {
    super.onCreate();
    mContext = getApplicationContext();
  }

  public static Context getAppContext() {
    return mContext;
  }


}
