package com.bonge.tintint.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bonge.tintint.databinding.ItemPhotosBinding
import com.bonge.tintint.web.PhotosResponse
import com.bumptech.glide.Glide

class PhotosAdapter : PagingDataAdapter<PhotosResponse, PhotosAdapter.ViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object :
            DiffUtil.ItemCallback<PhotosResponse>() {
            override fun areItemsTheSame(
                oldItem: PhotosResponse,
                newItem: PhotosResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PhotosResponse,
                newItem: PhotosResponse
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private lateinit var context: Context

    inner class ViewHolder(val binding: ItemPhotosBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = getItem(position)
            item?.let {
                idTextView.text = it.id
                titleTextView.text = it.title
                Glide.with(context)
                    .load(item.thumbnailUrl)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ItemPhotosBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }
}