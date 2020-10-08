package com.pratclot.paginglib3demo.recycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pratclot.paginglib3demo.R
import com.pratclot.paginglib3demo.data.CatImage
import com.pratclot.paginglib3demo.recycler.viewholders.CatImageViewHolder

class CatImageAdapter : PagingDataAdapter<CatImage, CatImageViewHolder>(CatComparator) {
    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CatImageViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.cat_view
    }

    companion object {
        object CatComparator : DiffUtil.ItemCallback<CatImage>() {
            override fun areItemsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: CatImage, newItem: CatImage): Boolean {
                return false
            }

        }
    }
}