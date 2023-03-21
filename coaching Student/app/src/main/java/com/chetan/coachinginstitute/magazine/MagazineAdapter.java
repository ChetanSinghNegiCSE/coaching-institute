package com.chetan.coachinginstitute.magazine;

import static android.media.CamcorderProfile.get;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chetan.coachinginstitute.R;


import java.util.ArrayList;
import java.util.List;

public class MagazineAdapter extends RecyclerView.Adapter<MagazineAdapter.EbookViewHolder> {

    private Context context;
    private List<MagazineData> list;

    public MagazineAdapter(Context context, List<MagazineData> list) {
        this.context = context;
        this.list = list;
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
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, list.get(position)+"Coming Soon....", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
                context.startActivity(intent);
            }
        });

       /* holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPdfUrl()));
                context.startActivity(intent);
            }
        });*/
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
//        private ImageView ebookDownload;

        public EbookViewHolder(@NonNull View itemView) {

            super(itemView);

            ebookName = itemView.findViewById(R.id.ebookName);
//            ebookDownload = itemView.findViewById(R.id.ebookDownload);
        }
    }
}
