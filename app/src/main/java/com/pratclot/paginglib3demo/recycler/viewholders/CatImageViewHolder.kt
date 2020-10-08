package com.pratclot.paginglib3demo.recycler.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratclot.paginglib3demo.R
import com.pratclot.paginglib3demo.data.CatImage
import kotlinx.android.synthetic.main.cat_view.view.*

class CatImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: CatImage?) {
        itemView.apply {
            item?.let {
                Glide.with(this)
                    .load(it.url)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(catImage)

                catText.text = context.resources.getString(R.string.catText, it.id)
            }
        }
    }
}
