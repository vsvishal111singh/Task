package com.task.Model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.task.Model.SongModel;
import com.task.Presenter.SongPresenter;
import com.task.View.SongView;
import com.task.utilites.Method;
import com.task.utilites.ResponseListener;
import com.task.utilites.WebServiceExecutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vishal on 11/2/2018.
 */

public class SongPresenterImpl implements SongPresenter {

    Context context;
    final int ORDER_REQUEST = 101;
    SongView view;

    public SongPresenterImpl(SongView view, Context context){

        this.context = context;
        this.view = view;
    }

    @Override
    public void getSongs() {

        sendDataOnServer();
    }

    private void sendDataOnServer() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Your Token");
        headers.put("cache-control", "no-cache");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("app_key", "test");
        WebServiceExecutor executor = new WebServiceExecutor(context);
        executor.setRequestParam(params);
        executor.setHeader(headers);
        executor.isProgressDialogShow(false);
        executor.setUrl("https://itunes.apple.com/search?term=Michael+jackson");
        executor.setRequestMethod(Method.POST);
        executor.setRequestCode(ORDER_REQUEST);
        executor.setResponseListener(new ResponseListener() {
            @Override
            public void onResponse(int requestCode, int responseCode, String response) {
                switch (requestCode) {
                    case ORDER_REQUEST:
                        try {
                            JSONObject mainObject = new JSONObject(response);
                            JSONArray jsonArray = mainObject.getJSONArray("results");
                            Log.e("list",""+jsonArray.length());
                            if(jsonArray.length()>0) {

                                ArrayList<SongModel> items = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<SongModel>>() {
                                }.getType());
                                Log.e("list",items.toString());
                                view.onSuccess(items);

                            }else{

                                view.onFail();
                            }

                        } catch (JSONException e) {
                           e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onFailed(int requestCode) {

            }
        });
        executor.execute();
    }

}
