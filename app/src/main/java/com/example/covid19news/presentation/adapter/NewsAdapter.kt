package com.example.covid19news.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.covid19news.R
import com.example.covid19news.domain.models.NewsModel

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.nid == newItem.nid
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val title = diff.currentList[position].title ?: ""
        val urlToImage = diff.currentList[position].urlToImage ?: ""
        val time = diff.currentList[position].publishedAt ?: ""
        holder.bind(title, urlToImage, time)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(diff.currentList[position]) }
        }
    }

    private var onItemClickListener: ((NewsModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (NewsModel) -> Unit) {
        onItemClickListener = listener
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
