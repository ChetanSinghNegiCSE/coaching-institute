package com.chetan.coachinginstitute.ui.faculty;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class FacultyFragment extends Fragment {

    RecyclerView jeeTeacher,neetTeacher,defenceTeacher,sscTeacher,bakingTeacher,groupC;
    private LinearLayout jeeNoData,neetNodata,defenceNoData,sscNoData,bankingNoData,cNoData;
    private List<TeacherData> list1,list2,list3,list4,list5,list6;
    private DatabaseReference reference,dbRef;
    private TeacherAdepter adepter;
    private ProgressBar progressBar;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        jeeTeacher = view.findViewById(R.id.jeeTeachers);
        neetTeacher = view.findViewById(R.id.neetTeachers);
        defenceTeacher = view.findViewById(R.id.defenceTeachers);
        sscTeacher = view.findViewById(R.id.sscTeachers);
        bakingTeacher = view.findViewById(R.id.bankingTeachers);
        groupC=view.findViewById(R.id.groupC);

        progressBar = view.findViewById(R.id.progressBar);


        jeeNoData = view.findViewById(R.id.jeeNoData);
        neetNodata = view.findViewById(R.id.neetNoData);
        defenceNoData = view.findViewById(R.id.defenceNoData);
        sscNoData = view.findViewById(R.id.sscNoData);
        bankingNoData = view.findViewById(R.id.bankingNoData);
        cNoData=view.findViewById(R.id.groupCNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        jeeTeach();
        neetTeach();
        defenceTeach();
        sscTeach();
        bankingTeach();
        GroupC();



        return view;
    }

    private void jeeTeach() {
        dbRef = reference.child("JEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if(!dataSnapshot.exists()){

                    jeeNoData.setVisibility(View.VISIBLE);
                    jeeTeacher.setVisibility(View.GONE);
                }else {
                    jeeNoData.setVisibility(View.GONE);
                    jeeTeacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    jeeTeacher.setHasFixedSize(true);
                    jeeTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list1,getContext());
                    progressBar.setVisibility(View.GONE);
                    jeeTeacher.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void neetTeach() {
        dbRef = reference.child("NEET");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    neetNodata.setVisibility(View.VISIBLE);
                    neetTeacher.setVisibility(View.GONE);
                }else {
                    neetNodata.setVisibility(View.GONE);
                    neetTeacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    neetTeacher.setHasFixedSize(true);
                    neetTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list4,getContext());
                    progressBar.setVisibility(View.GONE);
                    neetTeacher.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void defenceTeach() {
        dbRef = reference.child("Defence");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    defenceNoData.setVisibility(View.VISIBLE);
                    defenceTeacher.setVisibility(View.GONE);
                }else {
                    defenceNoData.setVisibility(View.GONE);
                    defenceTeacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    defenceTeacher.setHasFixedSize(true);
                    defenceTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list2,getContext());
                    progressBar.setVisibility(View.GONE);
                    defenceTeacher.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sscTeach() {
        dbRef = reference.child("SSC");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    sscNoData.setVisibility(View.VISIBLE);
                    sscTeacher.setVisibility(View.GONE);
                }else {
                    sscNoData.setVisibility(View.GONE);
                    sscTeacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    sscTeacher.setHasFixedSize(true);
                    sscTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list3,getContext());
                    progressBar.setVisibility(View.GONE);
                    sscTeacher.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bankingTeach() {
        dbRef = reference.child("Banking");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    bankingNoData.setVisibility(View.VISIBLE);
                    bakingTeacher.setVisibility(View.GONE);
                }else {
                    bankingNoData.setVisibility(View.GONE);
                    bakingTeacher.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    bakingTeacher.setHasFixedSize(true);
                    bakingTeacher.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list5,getContext());
                    progressBar.setVisibility(View.GONE);
                    bakingTeacher.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GroupC() {
        dbRef = reference.child("Group C(UK)");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    cNoData.setVisibility(View.VISIBLE);
                    groupC.setVisibility(View.GONE);
                }else {
                    cNoData.setVisibility(View.GONE);
                    groupC.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren() ){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    groupC.setHasFixedSize(true);
                    groupC.setLayoutManager(new LinearLayoutManager(getContext()));
                    adepter = new TeacherAdepter(list6,getContext());
                    progressBar.setVisibility(View.GONE);
                    groupC.setAdapter(adepter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}