package com.task.utilites;


import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WebServiceExecutor {

    private final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    private final String PROGRESS_MSG = "Please Wait...";
    private String url;
    private boolean isProgressDialogShow = true;
    private int requestCode;
    private OkHttpClient client;
    private ResponseListener listener;
    private CustomProgress progressDialog;
    private Call call;
    private int requestMethod = Method.POST;
    private Context context;
    private HashMap<String, String> queryParam;
    private HashMap<String, String> header;

    public WebServiceExecutor(Context context) {
        this.context = context;
        progressDialog = new CustomProgress(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        queryParam = new HashMap<>();
        header = new HashMap<>();
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }

    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        for (Map.Entry<String, String> entry : header.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void isProgressDialogShow(boolean isProgressDialogShow) {
        this.isProgressDialogShow = isProgressDialogShow;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public void setResponseListener(ResponseListener listener) {
        this.listener = listener;
    }

    public void cancelRequest() {
        if (call != null)
            call.cancel();
    }

    public void setRequestParam(HashMap<String, String> queryParam) {
        this.queryParam = queryParam;
    }

    public void execute() {
        if (url == null || url.isEmpty()) {
            showMessage("Invalid URL");
            return;
        }
        showProgressDialog(true);
        Request request = null;
        if (requestMethod == Method.POST) {

            FormBody body = setPostParam(queryParam);
            request = new Request.Builder()
                    .url(url)
                    .headers(getHeaders())
                    .post(body)
                    .build();
            Log.e("WebRequest", url + "{ " + queryParam + "} ");
        } else if (requestMethod == Method.GET) {

            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }

            /*String query = hashMapToQuery(queryParam);
            url = url + "?" + query;*/
            url = urlBuilder.build().toString();
            Log.e("WebRequest", url);
            request = new Request.Builder()
                    .url(url)
                    .get()
                    .headers(getHeaders())
                    .build();
        } else if (requestMethod == Method.PUT) {

            FormBody body = setPostParam(queryParam);
            request = new Request.Builder()
                    .url(url)
                    .headers(getHeaders())
                    .put(body)
                    .build();
        } else {
            showMessage("Invalid Method Type");
            return;
        }

        try {
            call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String msg = e.getMessage();
                    if (msg == null || msg.isEmpty())
                        msg = "null";
                    Log.e("onFailure", msg);

                    showProgressDialog(false);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onFailed(requestCode);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    showProgressDialog(false);
                    final String responseStr = response.body().string();
                    final int responseCode = response.code();
                    Log.e("RESPONSE:" + responseCode, responseStr);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onResponse(requestCode, responseCode, responseStr);
                        }
                    });
                }
            });

        } catch (Exception e) {
            showProgressDialog(false);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onFailed(requestCode);
                }
            });
            e.printStackTrace();
        }
    }

    private void showProgressDialog(boolean status) {
        if (isProgressDialogShow && status) {
            progressDialog.show();
        } else if (isProgressDialogShow && !status)
            progressDialog.dismiss();
    }

    private void showMessage(final String message) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String hashMapToQuery(HashMap<String, String> hashMap) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        String query;
        if (sb.length() > 1) {
            query = sb.substring(0, sb.length() - 1);
        } else {
            query = sb.toString();
        }
        return Uri.encode(query, ALLOWED_URI_CHARS);
    }

    private FormBody setPostParam(HashMap<String, String> queryParam) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }
}




