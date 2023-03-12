package com.example.coachinginstitute.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coachinginstitute.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {
    private ViewPager viewPager;
    private coursesAdepter adepter;
    private List<courseModal> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list=new ArrayList<>();
        list.add(new courseModal(R.drawable.ic_cse,"Defence",""));

        list.add(new courseModal(R.drawable.ic_me,"Mechanical Engineering"," The department has well qualified and dedicated faculty. The faculty of the department covers a wide range of Mechanical and Industrial engineering areas such as Applied Mechanics design, Dynamic  Control, Thermo fluid  Processes, Materials  Manufacturing, Production Planning  Control, Automation etc. Our graduates are working in Multinational Companies, Defence services, and Academic institutions in India and Abroad. Last but not least our technical supporting staff is unique in its way. Students, as well as visitors, find them very friendly, polite and highly intelligent. I wish all the students and faculty a great academic career. Thank you for visiting us."));


        adepter = new coursesAdepter(getContext(),list);

        viewPager =view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adepter);

        ImageView imageView = view.findViewById(R.id.teamImage);

       /* Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40367710djpg?alt=media&token=c0369867-fde5-472e-90d4-e0cece4538d2")
                .into(imageView);*/

        return view;
    }
}