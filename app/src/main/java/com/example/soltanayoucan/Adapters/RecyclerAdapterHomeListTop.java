package com.example.soltanayoucan.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Constants;
import com.squareup.picasso.Picasso;

public class RecyclerAdapterHomeListTop extends RecyclerView.Adapter<RecyclerAdapterHomeListTop.RecyclerHolder> {

    private OnListClickListener mListener;

    static class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView imageView_top_list;

        public RecyclerHolder(@NonNull View itemView, OnListClickListener mListener) {
            super(itemView);
            imageView_top_list = itemView.findViewById(R.id.image_view_home_top_list);
            //On Click Listener for the View
            imageView_top_list.setOnClickListener(v -> {
                if (mListener != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        mListener.onListClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_top_list, parent, false);
        return new RecyclerHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Picasso.get().load(Constants.home_top_list_images[position]).into(holder.imageView_top_list);
    }

    @Override
    public int getItemCount() {
        return Constants.home_top_list_images.length;
    }

    public void setClickingListener(OnListClickListener mListener) {
        this.mListener = mListener;
    }
}