package com.carousel.webview

import android.content.Context

class DataSource(val context: Context) {
    fun getItems(): MutableList<String>{
        return context.resources.getStringArray(R.array.text_items).toMutableList()
    }
}