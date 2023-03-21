package com.chetan.coachinginstitute.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.chetan.coachinginstitute.R;
import com.chetan.coachinginstitute.ui.about.courseModal;
import com.chetan.coachinginstitute.ui.about.coursesAdepter;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private coursesAdepter adepter;
    private List<courseModal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("About"); //SetTitle in Action bar

        String Defence=getString(R.string.Defence);
        String Bank=getString(R.string.Bank);
        String NEET=getString(R.string.neet);
        String Uttarakhand=getString(R.string.Uttarakhand);



        list=new ArrayList<>();
        list.add(new courseModal(R.drawable.ic_cse,"Defence",Defence));
        list.add(new courseModal(R.drawable.ic_me,"Bank",Bank));
        list.add(new courseModal(R.drawable.ic_cse,"NEET",NEET));
        list.add(new courseModal(R.drawable.ic_me,"Uttarakhand",Uttarakhand));
        list.add(new courseModal(R.drawable.ic_cse,"SSC/GD Agniveer",Uttarakhand));
        list.add(new courseModal(R.drawable.ic_me,"Foundation (9th & 12th )",Uttarakhand));




        adepter = new coursesAdepter(AboutActivity.this,list);

        viewPager =findViewById(R.id.viewPager);
        viewPager.setAdapter(adepter);


        ImageView imageView = findViewById(R.id.teamImage);

       /* Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40367710djpg?alt=media&token=c0369867-fde5-472e-90d4-e0cece4538d2")
                .into(imageView);*/


    }
}