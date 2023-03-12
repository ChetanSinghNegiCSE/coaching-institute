package com.example.coachinginstitute.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coachinginstitute.R;
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
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%408119804jpg?alt=media&token=531de711-d576-4b99-9fd1-acdd4f03eaac");
                        break;
                    case 2:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%401588a82jpg?alt=media&token=c771febc-5f8d-40ab-8179-135cb87cf457");
                        break;
                    case 3:
                        sliderViews.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pehli-manzil-app.appspot.com/o/gallery%2F%5BB%40b47f01jpg?alt=media&token=826dc95d-7dee-4a74-a35a-3d82d7484f53");
                        break;

                    }
                sliderViews.setImageScaleType(ImageView.ScaleType.FIT_XY);
                sliderLayout.addSliderView(sliderViews);
            }
        }
    }
