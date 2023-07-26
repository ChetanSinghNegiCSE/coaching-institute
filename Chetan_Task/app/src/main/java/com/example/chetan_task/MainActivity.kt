package com.example.chetan_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.withContext
import java.io.IOException



import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private val dataList = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(dataList)
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
            } catch (e: IOException) {
                // Handle the error (e.g., show a toast or error message)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
