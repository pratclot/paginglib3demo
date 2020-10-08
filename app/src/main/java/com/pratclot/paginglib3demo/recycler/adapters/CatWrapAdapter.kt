package com.pratclot.paginglib3demo.recycler.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.pratclot.paginglib3demo.R
import com.pratclot.paginglib3demo.data.CatWrap
import com.pratclot.paginglib3demo.recycler.viewholders.CatWrapViewHolder

class CatWrapAdapter : PagingDataAdapter<CatWrap, CatWrapViewHolder>(CatComparator) {
    override fun onBindViewHolder(holder: CatWrapViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatWrapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CatWrapViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.cat_view
    }

    companion object {
        object CatComparator : DiffUtil.ItemCallback<CatWrap>() {
            override fun areItemsTheSame(oldItem: CatWrap, newItem: CatWrap): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: CatWrap, newItem: CatWrap): Boolean {
                return false
            }

        }
    }
}