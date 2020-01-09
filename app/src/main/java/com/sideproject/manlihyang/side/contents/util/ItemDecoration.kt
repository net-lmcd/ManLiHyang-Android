package com.sideproject.manlihyang.side.contents.util

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(
    lineSpacing : Float,
    itemSpacing : Float
) : RecyclerView.ItemDecoration() {

    private val mLineSpacing : Float = lineSpacing
    private val mItemSpacing : Float = itemSpacing

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        val lineSpacing = dp2px(view.context, mLineSpacing)
        val itemSpacing = dp2px(view.context, mItemSpacing)

        val orientation =
            (parent.layoutManager as? LinearLayoutManager)?.orientation
                ?: RecyclerView.VERTICAL

        if (position == 0) when (orientation) {
            RecyclerView.VERTICAL -> {
                outRect.top = if(itemCount > 1) lineSpacing else 0
                outRect.bottom = lineSpacing / 2
            }
            RecyclerView.HORIZONTAL -> {
                outRect.left =  lineSpacing / 2
                outRect.right = if(itemCount > 1) itemSpacing else 0
            }
        }
        else if (position == itemCount - 1) when (orientation) {
            RecyclerView.VERTICAL -> {
                outRect.top =  lineSpacing / 2
                outRect.bottom = lineSpacing
            }
            RecyclerView.HORIZONTAL -> {
                outRect.left =  lineSpacing / 2
                outRect.right = lineSpacing
            }
        }
        else if (0 < position && position < itemCount - 1) when (orientation) {
            RecyclerView.VERTICAL -> {
                outRect.top = lineSpacing / 2
                outRect.bottom = lineSpacing / 2
            }
            RecyclerView.HORIZONTAL -> {
                outRect.left = itemSpacing / 2
                outRect.right = itemSpacing / 2
            }
        }
    }
}

fun dp2px( context: Context, dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}