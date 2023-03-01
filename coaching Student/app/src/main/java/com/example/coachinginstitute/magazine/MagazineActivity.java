package com.example.coachinginstitute.magazine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coachinginstitute.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MagazineActivity extends AppCompatActivity {
    private RecyclerView ebookRecycler1, ebookRecycler2, ebookRecycler3, ebookRecycler4, ebookRecycler5, ebookRecycler6, ebookRecycler7, ebookRecycler8, ebookRecycler9, ebookRecycler10, ebookRecycler11, ebookRecycler12;
    private DatabaseReference reference;
    private List<MagazineData> list;
    private MagazineAdapter adapter;
    NestedScrollView nestedScrollView;


        ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout, noData1, noData2, noData3, noData4, noData5, noData6, noData7, noData8, noData9, noData10, noData11, noData12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("Monthly Magazine"); //SetTitle in Action bar
        ebookRecycler1 = findViewById(R.id.ebookRecycler1);
        ebookRecycler2 = findViewById(R.id.ebookRecycler2);
        ebookRecycler3 = findViewById(R.id.ebookRecycler3);
        ebookRecycler4 = findViewById(R.id.ebookRecycler4);
        ebookRecycler5 = findViewById(R.id.ebookRecycler5);
        ebookRecycler6 = findViewById(R.id.ebookRecycler6);
        ebookRecycler7 = findViewById(R.id.ebookRecycler7);
        ebookRecycler8 = findViewById(R.id.ebookRecycler8);
        ebookRecycler9 = findViewById(R.id.ebookRecycler9);
        ebookRecycler10 = findViewById(R.id.ebookRecycler10);
        ebookRecycler11 = findViewById(R.id.ebookRecycler11);
        ebookRecycler12 = findViewById(R.id.ebookRecycler12);
        reference = FirebaseDatabase.getInstance().getReference().child("Monthly Magazine"); //node Name
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout=findViewById(R.id.shimmerLayout);
        noData1 = findViewById(R.id.NoData1);
        noData2 = findViewById(R.id.NoData2);
        noData3 = findViewById(R.id.NoData3);
        noData4 = findViewById(R.id.NoData4);
        noData5 = findViewById(R.id.NoData5);
        noData6 = findViewById(R.id.NoData6);
        noData7 = findViewById(R.id.NoData7);
        noData8 = findViewById(R.id.NoData8);
        noData9 = findViewById(R.id.NoData9);
        noData12 = findViewById(R.id.NoData12);
        noData10 = findViewById(R.id.NoData10);
        noData11 = findViewById(R.id.NoData11);


        nestedScrollView = findViewById(R.id.bookScroll);



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




    }

    private void filterList(String newText) {
        ArrayList<MagazineData> filterList = new ArrayList<>();
        for (MagazineData item : list) {
            if (item.getPdfTitle().toLowerCase().contains(newText.toLowerCase())) {
                filterList.add(item);
            }
        }

        if (filterList.isEmpty()) {
            Toast.makeText(this, "No Data ", Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler1.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler1.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler2.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler2.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler3.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler3.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler4.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler4.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler5.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler5.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler6.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler6.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler7.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler7.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler8.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler8.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler9.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler9.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler10.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler10.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler11.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler11.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

                    adapter = new MagazineAdapter(MagazineActivity.this, list);
                    ebookRecycler12.setLayoutManager(new LinearLayoutManager(MagazineActivity.this));
                    ebookRecycler12.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagazineActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



        @Override
        protected void onPause () {
            shimmerFrameLayout.stopShimmer();
            super.onPause();
        }

        @Override
        protected void onResume () {
            shimmerFrameLayout.startShimmer();
            super.onResume();
        }


    }
