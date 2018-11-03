package com.task.View;

import com.task.Model.SongModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal on 11/2/2018.
 */

public interface SongView{
    void onSuccess(ArrayList<SongModel> arraylist);
    void onFail();
}
