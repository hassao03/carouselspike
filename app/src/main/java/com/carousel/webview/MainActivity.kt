package com.carousel.webview

import android.graphics.drawable.Drawable
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
        recyclerView.adapter = Adapter(recyclerViewSetup())
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun recyclerViewSetup(): List<Adapter.Rows>{

        val rows = mutableListOf<Adapter.Rows>()

        val teams = DataSource(this).getItems()
        val icons = ArrayList<Int>()
//        val arr = resources.obtainTypedArray(R.array.icons)
//        (0 until arr.length()).forEach{
//            val icon = arr.getResourceId(it, -1)
//            icons.add(icon)
//        }
//        arr.recycle()

        var teamImages = arrayOf(
            R.drawable.arsenal_logo,
            R.drawable.leeds_logo,
            R.drawable.liverpool_logo,
            R.drawable.everton_logo,
            R.drawable.astonvilla_logo,
            R.drawable.westham_logo,
            R.drawable.chelsea_logo,
            R.drawable.west_brom_logo);

        val webUrl = DataSource(this).getWebUrl()

        rows.add(Adapter.CarouselRow(teams, teamImages))
        rows.add(Adapter.WebViewRow(webUrl))
        return rows
    }

}