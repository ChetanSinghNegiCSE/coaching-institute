package com.chetan.coachinginstitute.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chetan.coachinginstitute.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    private RecyclerView galleryRecycler ;
    GalleryAdapter adapter;

    DatabaseReference reference;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryRecycler = view.findViewById(R.id.GRecyclerView);

        progressBar = view.findViewById(R.id.progressBar);

        reference= FirebaseDatabase.getInstance().getReference().child("gallery");

        getGalleryImage();



        return view;
    }

    private void getGalleryImage() {
        reference.addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String data = String.valueOf(snapshot.getValue());
                    imageList.add(data);

                }
                adapter = new GalleryAdapter(getContext(), imageList);
                galleryRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                progressBar.setVisibility(View.GONE);
                galleryRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}