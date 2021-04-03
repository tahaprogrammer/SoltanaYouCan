package com.example.soltanayoucan.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Variables;

public class RecyclerAdapterListPosts extends RecyclerView.Adapter<RecyclerAdapterListPosts.RecyclerHolder> {

    private OnListClickListenerView lickingListener;
    private int listSize;

    public RecyclerAdapterListPosts(int listSize) {
        this.listSize = listSize;
    }

    static class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView_post_title;
        TextView textView_post_date;
        ImageView imageView_shared;

        public RecyclerHolder(@NonNull View itemView, OnListClickListenerView lickingListener) {
            super(itemView);

            //Views initialization
            textView_post_title = itemView.findViewById(R.id.text_view_item_posts_title);
            textView_post_date = itemView.findViewById(R.id.text_view_item_posts_date);
            imageView_shared = itemView.findViewById(R.id.image_view_item_post_header);

            //OnClickListener
            itemView.findViewById(R.id.layout_post_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lickingListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            lickingListener.onClicking(getAdapterPosition(), imageView_shared);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_posts_home, parent, false);

        return new RecyclerHolder(view, lickingListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        //Single Post
        holder.textView_post_title.setText(Variables.dataPostModels.get(position).getTitle());
        holder.textView_post_date.setText(changeDateFormat(Variables.dataPostModels.get(position).getDate_gmt()));
    }

    @Override
    public int getItemCount() {
        return listSize;
    }

    private String changeDateFormat(String date) {
        String[] split = date.split("T");
        return split[0];
    }

    public void setLickingListener(OnListClickListenerView lickingListener) {
        this.lickingListener = lickingListener;
    }

    public boolean addMorePosts(int listSize) {
        //This code add More items to the RecyclerView
        boolean ending = false;
        this.listSize = listSize;
        if (this.listSize >= Variables.dataPostModels.size()) {
            this.listSize = Variables.dataPostModels.size();
            ending = true;
        }
        notifyDataSetChanged();
        return ending;
    }
}