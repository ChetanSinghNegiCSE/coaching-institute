package com.example.coachinginstitute.papers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coachinginstitute.R;
import com.example.coachinginstitute.magazine.MagazineActivity;
import com.example.coachinginstitute.magazine.MagazineAdapter;
import com.example.coachinginstitute.magazine.MagazineData;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaperActivity extends AppCompatActivity {
    private RecyclerView ebookRecycler1, ebookRecycler2, ebookRecycler3, ebookRecycler4, ebookRecycler5, ebookRecycler6, ebookRecycler7;
    private DatabaseReference reference;
    private List<MagazineData> list;
    private MagazineAdapter adapter;
    NestedScrollView nestedScrollView;


        ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout shimmerLayout, noData1, noData2, noData3, noData4, noData5, noData6, noData7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back button in action bar
        getSupportActionBar().setTitle("Papers"); //SetTitle in Action bar
        ebookRecycler1 = findViewById(R.id.ebookRecycler1);
        ebookRecycler2 = findViewById(R.id.ebookRecycler2);
        ebookRecycler3 = findViewById(R.id.ebookRecycler3);
        ebookRecycler4 = findViewById(R.id.ebookRecycler4);
        ebookRecycler5 = findViewById(R.id.ebookRecycler5);
        ebookRecycler6 = findViewById(R.id.ebookRecycler6);
        ebookRecycler7 = findViewById(R.id.ebookRecycler7);

        reference = FirebaseDatabase.getInstance().getReference().child("Previous Papers"); //node Name
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerLayout=findViewById(R.id.shimmerLayout);
        noData1 = findViewById(R.id.NoData1);
        noData2 = findViewById(R.id.NoData2);
        noData3 = findViewById(R.id.NoData3);
        noData4 = findViewById(R.id.NoData4);
        noData5 = findViewById(R.id.NoData5);
        noData6 = findViewById(R.id.NoData6);
        noData7 = findViewById(R.id.NoData7);



        nestedScrollView = findViewById(R.id.bookScroll);



        getDataJan();
        getDataFab();
        getDataMar();
        getDataApr();
        getDataMay();
        getDataJun();
        getDataJul();




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

        reference.child("Defence").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler1.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler1.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataFab() {

        reference.child("JEE").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler2.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler2.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }
    private void getDataMar() {

        reference.child("NEET").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler3.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler3.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataApr() {

        reference.child("SSC").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler4.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler4.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataMay() {

        reference.child("Banking").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler5.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler5.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataJun() {

        reference.child("Group C(UK)").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler6.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler6.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void getDataJul() {

        reference.child("Other Exam").addValueEventListener(new ValueEventListener() {
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

                    adapter = new MagazineAdapter(PaperActivity.this, list);
                    ebookRecycler7.setLayoutManager(new LinearLayoutManager(PaperActivity.this));
                    ebookRecycler7.setAdapter(adapter);
                    shimmerFrameLayout.stopShimmer();
                    shimmerLayout.setVisibility(View.GONE);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PaperActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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