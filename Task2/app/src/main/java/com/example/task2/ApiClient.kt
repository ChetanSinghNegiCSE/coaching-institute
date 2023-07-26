package com.example.task2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/photos/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    interface ApiService {
        @GET("photos")
        suspend fun getMyData(): List<MyDataModel>
    }

    suspend fun fetchMyData(): List<MyDataModel> {
        val apiService = retrofit.create(ApiService::class.java)
        return apiService.getMyData()
    }
}
