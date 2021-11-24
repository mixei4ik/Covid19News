package com.example.covid19news.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.covid19news.R
import com.example.covid19news.domain.NewsModel

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    private val items = mutableListOf<NewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val title = items[position].title ?: ""
        val urlToImage = items[position].urlToImage ?: ""
        val time = items[position].publishedAt ?: ""
        holder.bind(title, urlToImage, time)
    }

    fun addItems(newsItems: List<NewsModel>) {
        items.addAll(newsItems)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.findViewById<TextView>(R.id.title_text_view)
    private val imageView = view.findViewById<ImageView>(R.id.image_view)
    private val timeView = view.findViewById<TextView>(R.id.time_text_view)

    fun bind(title: String, urlToImage: String, time: String) {
        textView.text = title
        timeView.text = time
        imageView.load(urlToImage){
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_image)
            crossfade(true)
        }
    }
}
