package com.carousel.webview

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import kotlin.collections.ArrayList

class Adapter(val rowItems: List<Rows>): RecyclerView.Adapter<RecyclerView.ViewHolder> (){

    interface Rows
    class CarouselRow(val text: MutableList<String>, val image: Array<Int>): Rows
    class WebViewRow(val url: String): Rows

    class CarouselViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       // val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.carousel_linear_layout)

    }

    class WebViewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val webView: WebView = itemView.findViewById(R.id.web_view)
    }

    override fun getItemViewType(position: Int): Int {
        return when (rowItems[position]) {
            is CarouselRow -> TYPE_CAROUSEL
            is WebViewRow -> TYPE_WEBVIEW
            else -> throw IllegalArgumentException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when(viewType){

            TYPE_CAROUSEL -> CarouselViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel, parent, false))

            TYPE_WEBVIEW -> WebViewViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.webview_item, parent, false))

            else -> throw IllegalArgumentException()
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(holder.itemViewType){
            TYPE_CAROUSEL -> onBindCarouselHolder(holder, rowItems[position] as Adapter.CarouselRow )
            TYPE_WEBVIEW -> onBindWebViewHolder(holder, rowItems[position] as Adapter.WebViewRow )
            else -> throw IllegalArgumentException()
        }

    private fun onBindCarouselHolder(holder: RecyclerView.ViewHolder, row: CarouselRow){

        val carouselHolder = holder as CarouselViewHolder
        val layoutInflater = LayoutInflater.from(carouselHolder.linearLayout.context)

        //for each string create a new carousel_item and assign the textView and ImageView values
        for((i,team) in row.text.withIndex()){
            val carouselItem = layoutInflater.inflate(R.layout.carousel_item, carouselHolder.linearLayout, false)

            carouselItem.setOnClickListener {
                Toast.makeText(carouselHolder.linearLayout.context, team, Toast.LENGTH_SHORT).show()

            }
            carouselItem.findViewById<TextView>(R.id.topicTitle).text = team
            carouselItem.findViewById<ImageView>(R.id.topicImage).setImageResource(row.image[i])

            carouselHolder.linearLayout.addView(carouselItem)

        }
    }

    private fun onBindWebViewHolder(holder: RecyclerView.ViewHolder, row: WebViewRow){
        (holder as WebViewViewHolder).webView.loadUrl(row.url)
    }

    override fun getItemCount(): Int {
        return rowItems.size
    }

    companion object {
        private const val TYPE_CAROUSEL = 0
        private const val TYPE_WEBVIEW = 1
    }

}