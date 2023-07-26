package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<MyDataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        adapter = new MyAdapter(dataList);
        recyclerView.setAdapter(adapter);

        // Fetch data from API
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        ApiClient.ApiService apiService = ApiClient.getApiService();
        Call<List<MyDataModel>> call = apiService.getMyData();

        call.enqueue(new Callback<List<MyDataModel>>() {
            @Override
            public void onResponse(Call<List<MyDataModel>> call, Response<List<MyDataModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dataList.clear();
                    dataList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    // Handle API call error
                }
            }

            @Override
            public void onFailure(Call<List<MyDataModel>> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
}
