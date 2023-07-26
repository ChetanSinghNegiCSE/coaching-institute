package com.example.chetan_task

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    interface MyApiService {
        @GET("photos")
        suspend fun getMyData(): List<DataModel>
    }

    suspend fun fetchMyData(): List<DataModel> {
        val apiService = retrofit.create(MyApiService::class.java)
        return apiService.getMyData()
    }
}
