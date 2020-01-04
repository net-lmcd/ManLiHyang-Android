package com.sideproject.manlihyang.side.contents.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sideproject.manlihyang.BR

abstract class BaseRecyclerViewAdapterImpl<T>(
    diffCallback : DiffUtil.ItemCallback<T>
) : RecyclerView.Adapter<MVVMViewHolder<T>>() {

    var limit : Int? = null
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

    override fun onBindViewHolder(holder: MVVMViewHolder<T>, position: Int) {
        holder.onBind(listDiffer.currentList.getOrNull(position))
    }

    override fun getItemCount(): Int {
        return when {
            limit!=null -> if(listDiffer.currentList.size>limit!!){
                limit!!
            }else{
                listDiffer.currentList.size
            }
            else -> listDiffer.currentList.size
        }
    }
}

open class MVVMViewHolder<T>(private val binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context : Context
        get() = itemView.context

    open fun onBind(item : T?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}