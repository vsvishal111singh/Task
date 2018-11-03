package com.task.View;

import com.task.Model.SongModel;
import com.task.Model.SongModel1;

import java.util.ArrayList;
import java.util.List;

public interface SongView{
    void onSuccess(ArrayList<SongModel1> arraylist);
    void onFail();
}
