package com.task;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.task.Model.SongModel;
import com.task.Presenter.SongPresenter;
import com.task.Presenter.SongPresenterImpl;
import com.task.View.SongView;
import com.task.adapter.DataAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongView {

    SongPresenter presenter;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<SongModel> songlist;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new SongPresenterImpl();
        presenter.getSongs();
        context = MainActivity.this;
        songlist = new ArrayList<SongModel>();
        adapter = new DataAdapter(context,songlist);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }
}
