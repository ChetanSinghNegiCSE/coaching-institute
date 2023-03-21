package com.chetan.coachinginstitute.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetan.coachinginstitute.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    private TextView PhoneTextView;

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

        PhoneTextView= view.findViewById(R.id.phoneNo);
        PhoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = PhoneTextView.getText().toString();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialIntent);
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
            for(int i = 0 ; i<4 ; i++){

                DefaultSliderView sliderViews = new DefaultSliderView(getContext());

                switch (i){
                    case 0:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%40896ee91jpg?alt=media&token=9de996f5-c3bd-47fd-946b-4746f8dbe946");
                        break;
                    case 1:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%4087bba29jpg?alt=media&token=fae71eda-0826-4d20-908b-befb52b08b93");
                        break;
                    case 2:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%402d3dc6bjpg?alt=media&token=c8b8483d-e745-409c-8e17-f20497ea18cf");
                        break;
                    case 3:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%407017a5fjpg?alt=media&token=4278c643-8158-458b-96ab-055843459309");
                        break;

                    }
                sliderViews.setImageScaleType(ImageView.ScaleType.FIT_XY);
                sliderLayout.addSliderView(sliderViews);
            }
        }
    }
