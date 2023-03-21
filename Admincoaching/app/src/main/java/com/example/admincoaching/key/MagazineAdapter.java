package com.example.admincoaching.key;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.admincoaching.R;
import com.example.admincoaching.faculty.UpdateTeacherActivity;

import java.util.ArrayList;
import java.util.List;

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.EbookViewHolder> {

    private Context context;
    private List<MagazineData> list;

    private String category;

    public MagazineAdapter(Context context, List<MagazineData> list,String category) {
        this.context = context;
        this.list = list;
        this.category = category;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.magazine_item_layout,parent,false);
        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, @SuppressLint("RecyclerView") int position) {
        
        holder.ebookName.setText(list.get(position).getPdfTitle());
        
      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, list.get(position)+"Coming Soon....", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });*/

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAnsKey.class);
                intent.putExtra("category",category);
                intent.putExtra("ansTitle",list.get(position).getPdfTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void FilteredList(ArrayList<MagazineData> filterList) {
        this.list = filterList;
        notifyDataSetChanged();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {

        private TextView ebookName;
        private ImageView upload;
//        private ImageView ebookDownload;

        public EbookViewHolder(@NonNull View itemView) {

            super(itemView);

            ebookName = itemView.findViewById(R.id.ebookName);
            upload = itemView.findViewById(R.id.ansUpload);
        }
    }
}
