package com.example.soltanayoucan.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Variables;

public class RecyclerAdapterListPosts extends RecyclerView.Adapter<RecyclerAdapterListPosts.RecyclerHolder> {


    static class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView_post_title;
        TextView textView_post_date;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView_post_title = itemView.findViewById(R.id.text_view_item_posts_title);
            textView_post_date = itemView.findViewById(R.id.text_view_item_posts_date);
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_posts_home, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        //Single Post
        holder.textView_post_title.setText(Variables.dataPostModels.get(position).getTitle());
        holder.textView_post_date.setText(changeDateFormat(Variables.dataPostModels.get(position).getDate_gmt()));
    }

    @Override
    public int getItemCount() {
        return Variables.dataPostModels.size();
    }

    private String changeDateFormat(String date) {
        String[] split = date.split("T");
        return split[0];
    }
}