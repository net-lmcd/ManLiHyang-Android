package com.sideproject.manlihyang.side.contents.util.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sideproject.manlihyang.side.contents.base.BaseRecyclerViewAdapterImpl

@BindingAdapter("adapter")
fun<T> RecyclerView.binding(adapter: RecyclerView.Adapter<*>? = null) {
    this.adapter = adapter
}


@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun<T> RecyclerView.binding(list: List<T>? = null){
    (adapter as? BaseRecyclerViewAdapterImpl<T>)?.run {
        submitList(list)
    }
}
