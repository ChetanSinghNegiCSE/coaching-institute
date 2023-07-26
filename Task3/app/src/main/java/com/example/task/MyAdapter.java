package com.example.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<MyDataModel> dataList;

        public MyAdapter(List<MyDataModel> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MyDataModel data = dataList.get(position);
            holder.textTitle.setText(data.getTitle());

            // Load image using Glide into the ImageView
            Glide.with(holder.itemView)
                    .load(data.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textTitle;
            private ImageView imageView;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.textTitle);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }


