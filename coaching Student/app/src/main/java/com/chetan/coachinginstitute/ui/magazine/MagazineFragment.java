package com.chetan.coachinginstitute.ui.magazine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chetan.coachinginstitute.R;
import com.chetan.coachinginstitute.magazine.MagazineAdapter;
import com.chetan.coachinginstitute.magazine.MagazineData;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MagazineFragment extends Fragment {

    private RecyclerView ebookRecycler1, ebookRecycler2, ebookRecycler3, ebookRecycler4, ebookRecycler5, ebookRecycler6, ebookRecycler7, ebookRecycler8, ebookRecycler9, ebookRecycler10, ebookRecycler11, ebookRecycler12;
    private DatabaseReference reference;
    private List<MagazineData> list;
    private MagazineAdapter adapter;
    NestedScrollView nestedScrollView;


    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout, noData1, noData2, noData3, noData4, noData5, noData6, noData7, noData8, noData9, noData10, noData11, noData12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magazine, container, false);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("Monthly Magazine"); //SetTitle in Action bar*/
        ebookRecycler1 = view.findViewById(R.id.ebookRecycler1);
        ebookRecycler2 = view.findViewById(R.id.ebookRecycler2);
        ebookRecycler3 = view.findViewById(R.id.ebookRecycler3);
        ebookRecycler4 = view.findViewById(R.id.ebookRecycler4);
        ebookRecycler5 = view.findViewById(R.id.ebookRecycler5);
        ebookRecycler6 = view.findViewById(R.id.ebookRecycler6);
        ebookRecycler7 = view.findViewById(R.id.ebookRecycler7);
        ebookRecycler8 = view.findViewById(R.id.ebookRecycler8);
        ebookRecycler9 = view.findViewById(R.id.ebookRecycler9);
        ebookRecycler10 = view.findViewById(R.id.ebookRecycler10);
        ebookRecycler11 = view.findViewById(R.id.ebookRecycler11);
        ebookRecycler12 = view.findViewById(R.id.ebookRecycler12);
        reference = FirebaseDatabase.getInstance().getReference().child("Monthly Magazine"); //node Name
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        shimmerLayout=view.findViewById(R.id.shimmerLayout);
        noData1 = view.findViewById(R.id.NoData1);
        noData2 = view.findViewById(R.id.NoData2);
        noData3 = view.findViewById(R.id.NoData3);
        noData4 = view.findViewById(R.id.NoData4);
        noData5 = view.findViewById(R.id.NoData5);
        noData6 = view.findViewById(R.id.NoData6);
        noData7 = view.findViewById(R.id.NoData7);
        noData8 = view.findViewById(R.id.NoData8);
        noData9 = view.findViewById(R.id.NoData9);
        noData12 = view.findViewById(R.id.NoData12);
        noData10 = view.findViewById(R.id.NoData10);
        noData11 = view.findViewById(R.id.NoData11);


        nestedScrollView = view.findViewById(R.id.bookScroll);



        getDataJan();
        getDataFab();
        getDataMar();
        getDataApr();
        getDataMay();
        getDataJun();
        getDataJul();
        getDataAug();
        getDataSept();
        getDataOct();
        getDataNov();
        getDataDec();





        return view;
    }


    private void filterList(String newText) {
        ArrayList<MagazineData> filterList = new ArrayList<>();
        for (MagazineData item : list) {
            if (item.getPdfTitle().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(item);
            }
        }

        if (filterList.isEmpty()) {
            Toast.makeText(getContext(), "No Data ", Toast.LENGTH_SHORT).show();

        } else {
            adapter.FilteredList(filterList);
        }
    }

    private void getDataJan() {

        reference.child("January").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData1.setVisibility(View.VISIBLE);
                    ebookRecycler1.setVisibility(View.GONE);
                } else {

                    noData1.setVisibility(View.GONE);
                    ebookRecycler1.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler1.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler1.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataFab() {

        reference.child("February").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData2.setVisibility(View.VISIBLE);
                    ebookRecycler2.setVisibility(View.GONE);
                } else {

                    noData2.setVisibility(View.GONE);
                    ebookRecycler2.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler2.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler2.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void getDataMar() {

        reference.child("March").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData3.setVisibility(View.VISIBLE);
                    ebookRecycler3.setVisibility(View.GONE);
                } else {

                    noData1.setVisibility(View.GONE);
                    ebookRecycler3.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler3.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler3.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataApr() {

        reference.child("April").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData4.setVisibility(View.VISIBLE);
                    ebookRecycler4.setVisibility(View.GONE);
                } else {

                    noData4.setVisibility(View.GONE);
                    ebookRecycler4.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler4.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler4.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataMay() {

        reference.child("May").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData5.setVisibility(View.VISIBLE);
                    ebookRecycler5.setVisibility(View.GONE);
                } else {

                    noData5.setVisibility(View.GONE);
                    ebookRecycler5.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler5.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler5.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataJun() {

        reference.child("June").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData6.setVisibility(View.VISIBLE);
                    ebookRecycler6.setVisibility(View.GONE);
                } else {

                    noData6.setVisibility(View.GONE);
                    ebookRecycler6.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler6.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler6.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataJul() {

        reference.child("July").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData7.setVisibility(View.VISIBLE);
                    ebookRecycler7.setVisibility(View.GONE);
                } else {

                    noData7.setVisibility(View.GONE);
                    ebookRecycler7.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler7.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler7.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataAug() {

        reference.child("August").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData8.setVisibility(View.VISIBLE);
                    ebookRecycler8.setVisibility(View.GONE);
                } else {

                    noData8.setVisibility(View.GONE);
                    ebookRecycler8.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler8.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler8.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataSept() {

        reference.child("September").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData9.setVisibility(View.VISIBLE);
                    ebookRecycler9.setVisibility(View.GONE);
                } else {

                    noData9.setVisibility(View.GONE);
                    ebookRecycler9.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler9.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler9.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataOct() {

        reference.child("October").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData10.setVisibility(View.VISIBLE);
                    ebookRecycler10.setVisibility(View.GONE);
                } else {

                    noData10.setVisibility(View.GONE);
                    ebookRecycler10.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler10.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler10.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataNov() {

        reference.child("November").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData11.setVisibility(View.VISIBLE);
                    ebookRecycler11.setVisibility(View.GONE);
                } else {

                    noData1.setVisibility(View.GONE);
                    ebookRecycler11.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler11.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler11.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataDec() {

        reference.child("December").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                nestedScrollView.setVisibility(View.VISIBLE); // view scroll view all data
                if (!dataSnapshot.exists()) {
                    noData12.setVisibility(View.VISIBLE);
                    ebookRecycler12.setVisibility(View.GONE);
                } else {

                    noData12.setVisibility(View.GONE);
                    ebookRecycler12.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MagazineData data = snapshot.getValue(MagazineData.class);
                        list.add(data);

                    }

                    adapter = new MagazineAdapter(getContext(), list);
                    ebookRecycler12.setLayoutManager(new LinearLayoutManager(getContext()));
                    ebookRecycler12.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    public void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }


}
