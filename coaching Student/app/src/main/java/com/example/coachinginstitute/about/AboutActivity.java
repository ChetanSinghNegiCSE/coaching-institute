package com.example.coachinginstitute.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.coachinginstitute.R;
import com.example.coachinginstitute.ui.about.courseModal;
import com.example.coachinginstitute.ui.about.coursesAdepter;

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

        list=new ArrayList<>();
        list.add(new courseModal(R.drawable.ic_cse,"Computer Science and Engineering","In computer science you will study and apply your knowledge in understanding what computers are, as how to program them, tools to write a program, the rules when converting the written program understandable by the computers, the interface between the computer and the user, the computer graphics, computer networking, managing the software database, software engineering and testing them efficiently and more. The Department also places emphasis on all the important aspects of computers such as High speed networks, Soft Computing, Algorithm Design, Network Security, Advance database systems, Theory of computation and many more. The Department also takes initiative to improve the soft skills, analytical capabilities and verbal communication of the students so that they can face the competition in the corporate world confidently."));

        list.add(new courseModal(R.drawable.ic_me,"Mechanical Engineering"," The department has well qualified and dedicated faculty. The faculty of the department covers a wide range of Mechanical and Industrial engineering areas such as Applied Mechanics design, Dynamic  Control, Thermo fluid  Processes, Materials  Manufacturing, Production Planning  Control, Automation etc. Our graduates are working in Multinational Companies, Defence services, and Academic institutions in India and Abroad. Last but not least our technical supporting staff is unique in its way. Students, as well as visitors, find them very friendly, polite and highly intelligent. I wish all the students and faculty a great academic career. Thank you for visiting us."));


        adepter = new coursesAdepter(AboutActivity.this,list);

        viewPager =findViewById(R.id.viewPager);
        viewPager.setAdapter(adepter);

        ImageView imageView = findViewById(R.id.teamImage);

       /* Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/exam-app-ad313.appspot.com/o/gallery%2F%5BB%40367710djpg?alt=media&token=c0369867-fde5-472e-90d4-e0cece4538d2")
                .into(imageView);*/


    }
}