package com.example.coachinginstitute.ui.notice;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coachinginstitute.R;


import java.util.ArrayList;


public class NoticeAdepter extends RecyclerView.Adapter<NoticeAdepter.NoticeViewAdepter> {
    private Context context;
    private ArrayList<NoticeData> list;


    public NoticeAdepter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdepter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout, parent, false);
        return new NoticeViewAdepter(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdepter holder, int position) {

        NoticeData currentItem = list.get(position);

        holder.NoticeTitle.setText(currentItem.getTitle());
        holder.NoticeBody.setText(currentItem.getBody());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());

        try {
            if (currentItem.getImage() != null)
                Glide.with(context).load(currentItem.getImage()).into(holder.NoticeImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* holder.NoticeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image",currentItem.getImage());
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdepter extends RecyclerView.ViewHolder {
        private TextView  NoticeTitle,NoticeBody,date, time;
        private ImageView NoticeImg;

        public NoticeViewAdepter(@NonNull View itemView) {
            super(itemView);

            NoticeTitle = itemView.findViewById(R.id.NoticeTitle);
            NoticeBody = itemView.findViewById(R.id.NoticeBody);
            NoticeImg = itemView.findViewById(R.id.NoticeImg);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }
}


