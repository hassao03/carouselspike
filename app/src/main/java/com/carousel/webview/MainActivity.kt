package com.carousel.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = Adapter(addItems())
    }

    private fun addItems(): List<Adapter.Rows>{
        val rows = mutableListOf<Adapter.Rows>()
        val items = DataSource(this).getItems()
        rows.add(Adapter.TextRow(items))
        return rows
    }
}