package com.task;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.task.Model.SongModel;
import com.task.Presenter.SongPresenter;
import com.task.Model.SongPresenterImpl;
import com.task.View.SongView;
import com.task.adapter.DataAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SongView {

    SongPresenter presenter;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<SongModel> songlist;
    Context context;
    RecyclerView recylerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        songlist = new ArrayList<SongModel>();
        presenter = new SongPresenterImpl(this,context);
        presenter.getSongs();
        recylerview = (RecyclerView)findViewById(R.id.recylerview);
        adapter = new DataAdapter(context,songlist);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(ArrayList<SongModel> list) {

        songlist = list;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFail() {

    }
}
