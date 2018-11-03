package com.task.utilites;

public interface ResponseListener {
    void onResponse(int requestCode, int responseCode, String response);
    void onFailed(int requestCode);
}