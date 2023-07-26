package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val dataList = mutableListOf<MyDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(dataList)
        recyclerView.adapter = adapter

        // Fetch data from API
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        // Use runBlocking for simplicity. In a real app, use coroutines properly.
        runBlocking(Dispatchers.IO) {
            try {
                val result = ApiClient.fetchMyData()
                dataList.clear()
                dataList.addAll(result)
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
