package com.task.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.task.MainActivity;
import com.task.Model.SongModel;
import com.task.Model.SongModel1;
import com.task.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    ArrayList<SongModel> songlist;

    public DataAdapter(Context context, ArrayList<SongModel> songlist) {
        this.context = context;
        this.songlist = songlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songrow,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        SongModel item = songlist.get(position);
        Log.e("Data",item.getArtistId());
        //Glide.with(context).load(item.getImg()).into(holder.imageView);
        holder.title.setText(item.getKind());
    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.photo);
            title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
