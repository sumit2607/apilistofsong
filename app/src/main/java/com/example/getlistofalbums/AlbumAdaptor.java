package com.example.getlistofalbums;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private List<ResponseModel> responseModellist;
    public AlbumAdapter(List<ResponseModel> responseModel) {
        this.responseModellist = responseModel;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        ResponseModel responseModel = responseModellist.get(position);
        holder.setData(responseModel);
    }

    @Override
    public int getItemCount() {
        return responseModellist.size();
    }

    public void updateData(List<ResponseModel> responseModel){
        this.responseModellist = responseModel;
        notifyDataSetChanged();
    }
}
