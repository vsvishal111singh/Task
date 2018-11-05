package com.task;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.task.Model.SongModel;
import com.task.Model.SongModel1;
import com.task.Presenter.SongPresenter;
import com.task.Model.SongPresenterImpl;
import com.task.View.SongView;
import com.task.adapter.DataAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SongView {

    SongPresenter presenter;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<SongModel> songlist;
    Context context;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        songlist = new ArrayList<SongModel>();
        // songlist.add(new SongModel("hello"));
        // songlist.add(new SongModel("hello1"));
        // songlist.add(new SongModel("hello2"));
        presenter = new SongPresenterImpl(this,context);
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new DataAdapter(context,songlist);
        mRecyclerView.setAdapter(adapter);
        presenter.getSongs();
    }

    @Override
    public void onSuccess(ArrayList<SongModel> list) {
        Log.e("Items",list.toString());
        songlist = list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail() {

    }
}
