package com.example.coachinginstitute.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coachinginstitute.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.COLOR);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderViews();

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();

            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Pehli Manzil Coaching Institute Pithoragarh, Link Road, near Lead Bank, Pithoragarh, Uttarakhand    ");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {
            for(int i = 0 ; i<7 ; i++){

                DefaultSliderView sliderViews = new DefaultSliderView(getContext());

                switch (i){
                    case 0:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40fc16c65jpg?alt=media&token=b5f33181-4d63-4c72-b6da-cca8e576e1f7");
                        break;
                    case 1:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40f6ba303jpg?alt=media&token=5a281048-4c18-4f8b-802d-e0d41ec97a19");
                        break;
                    case 2:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40787fejpg?alt=media&token=a265c6a9-cb09-402b-8e60-ba5eb72fbf22");
                        break;
                    case 3:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40d4f72fejpg?alt=media&token=f42b45bf-8aec-43b0-a2b4-447eeddc2e4a");
                        break;
                    case 4:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40826995fjpg?alt=media&token=91dac760-89ab-470d-8f16-bcb14cd5f5ad");
                        break;
                    case 5:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40367710djpg?alt=media&token=c0369867-fde5-472e-90d4-e0cece4538d2");
                        break;
                    case 6:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40101be9cjpg?alt=media&token=1f826f43-6711-4a58-81c8-81bd63aa953c");
                        break;
                }
                sliderViews.setImageScaleType(ImageView.ScaleType.FIT_XY);
                sliderLayout.addSliderView(sliderViews);
            }
        }
    }
