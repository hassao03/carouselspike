package com.carousel.webview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class Adapter(val context: Context, val rowItems: List<Rows>): RecyclerView.Adapter<RecyclerView.ViewHolder> (){

    interface Rows
    class TextRow(val text: MutableList<String>, val image: ArrayList<Int>): Rows
    class WebViewRow(val url: String): Rows

    class TextViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val imageView2: ImageView = itemView.findViewById(R.id.image_view2)
        val imageView3: ImageView = itemView.findViewById(R.id.image_view3)
    }

    class WebViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val webView: WebView = itemView.findViewById(R.id.web_view)
    }

    override fun getItemViewType(position: Int): Int {
        return when (rowItems[position]) {
            is TextRow -> TYPE_TEXT
            is WebViewRow -> TYPE_WEBVIEW
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType){
            TYPE_TEXT -> TextViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel, parent, false))
            TYPE_WEBVIEW -> WebViewViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.webview_item, parent, false))
            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(holder.itemViewType){
            TYPE_TEXT -> onBindTextHolder(holder, rowItems[position] as Adapter.TextRow )
            TYPE_WEBVIEW -> onBindWebViewHolder(holder, rowItems[position] as Adapter.WebViewRow )
            else -> throw IllegalArgumentException()
        }

    private fun onBindTextHolder(holder: RecyclerView.ViewHolder, row: TextRow){
        val textHolder = holder as TextViewHolder
        textHolder.textView.text = row.text.toString()
        textHolder.imageView.setImageDrawable(ContextCompat.getDrawable(context, row.image[0]))
        textHolder.imageView2.setImageDrawable(ContextCompat.getDrawable(context, row.image[1]))
        textHolder.imageView3.setImageDrawable(ContextCompat.getDrawable(context, row.image[2]))
    }

    private fun onBindWebViewHolder(holder: RecyclerView.ViewHolder, row: WebViewRow){
        (holder as WebViewViewHolder).webView.loadUrl(row.url)
    }

    override fun getItemCount(): Int {
        return rowItems.size
    }

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_WEBVIEW = 1
    }

}