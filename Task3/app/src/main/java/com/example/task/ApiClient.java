package com.example.task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface ApiService {
        @GET("photos")
        Call<List<MyDataModel>> getMyData();
    }

    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}

