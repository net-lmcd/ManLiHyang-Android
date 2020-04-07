package com.sideproject.manlihyang.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sideproject.manlihyang.BR

abstract class BaseRecyclerViewAdapter<T>(
    diffCallback : DiffUtil.ItemCallback<T>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    var limitedItemCount : Int? = null
    private var listDiffer : AsyncListDiffer<T> = AsyncListDiffer(this, diffCallback)

    fun submitList(list : List<T>?) {
        listDiffer.submitList(list)
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewType(position, listDiffer.currentList.getOrNull(position))
    }

    open fun getItemViewType(position : Int, item: T?) : Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(listDiffer.currentList.getOrNull(position))
    }

    override fun getItemCount(): Int =
        limitedItemCount
            ?.coerceAtMost(limitedItemCount!!)
            ?: listDiffer.currentList.size
}

open class BaseViewHolder<T>(private val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context : Context
        get() = itemView.context

    open fun onBind(item : T?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}