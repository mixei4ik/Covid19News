package com.example.covid19news.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        holder.bind(title, urlToImage)
    }

    fun addItems(newsItems: List<NewsModel>) {
        items.addAll(newsItems)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.findViewById<TextView>(R.id.textView)
    private val imageView = view.findViewById<ImageView>(R.id.imageView)

    fun bind(title: String, urlToImage: String) {
        textView.text = title
        imageView.load(urlToImage)
    }
}
