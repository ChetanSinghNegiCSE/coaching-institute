package com.example.coachinginstitute.ui.faculty;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.coachinginstitute.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class TeacherAdepter extends RecyclerView.Adapter<TeacherAdepter.TeacherViewAdepter> {
    private List<TeacherData> list;
    private Context context;


    public TeacherAdepter(List<TeacherData> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public TeacherViewAdepter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout,parent,false);
        return new TeacherViewAdepter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewAdepter holder, int position) {
        TeacherData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());
        try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherViewAdepter extends RecyclerView.ViewHolder {
        private TextView name ,email , post;
        private ImageView imageView;

        public TeacherViewAdepter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teacherName);
            email = itemView.findViewById(R.id.teacherEmail);
            post = itemView.findViewById(R.id.teacherPost);
            imageView = itemView.findViewById(R.id.teacherImage);
        }
    }
}
