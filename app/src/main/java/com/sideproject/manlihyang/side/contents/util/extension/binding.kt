package com.sideproject.manlihyang.side.contents.util.extension

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sideproject.manlihyang.side.contents.base.BaseRecyclerViewAdapterImpl
import com.sideproject.manlihyang.side.contents.util.ItemDecoration

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

@BindingAdapter(value = ["lineSpacing","itemSpacing"], requireAll = false)
fun<T> RecyclerView.binding(lineSpacing : Float = 0f, itemSpacing : Float = 0f) {
    if(this.itemDecorationCount == 0) {
        this.addItemDecoration(ItemDecoration(lineSpacing, itemSpacing))
    }
}