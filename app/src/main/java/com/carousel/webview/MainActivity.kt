package com.carousel.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main_without_layout)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = Adapter(this, addItems())
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun addItems(): List<Adapter.Rows>{
        val rows = mutableListOf<Adapter.Rows>()
        val items = DataSource(this).getItems()
        val webUrl = DataSource(this).getWebUrl()
        val imageArray = resources.obtainTypedArray(R.array.icons)
        rows.add(Adapter.TextRow(items, imageArray))
        rows.add(Adapter.WebViewRow(webUrl))
        return rows
    }

}