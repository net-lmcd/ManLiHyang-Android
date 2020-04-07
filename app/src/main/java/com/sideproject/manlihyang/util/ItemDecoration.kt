package com.sideproject.manlihyang.util

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

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
        val lineSpacing = dp2px(view.context, mLineSpacing)
        val itemSpacing = dp2px(view.context, mItemSpacing)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        val spanCount =
            (parent.layoutManager as? GridLayoutManager)?.spanCount
                ?: (parent.layoutManager as? StaggeredGridLayoutManager)?.spanCount
                ?: 0

        val orientation =
            (parent.layoutManager as? LinearLayoutManager)?.orientation
                ?: (parent.layoutManager as? GridLayoutManager)?.orientation
                ?: (parent.layoutManager as? StaggeredGridLayoutManager)?.orientation
                ?: RecyclerView.VERTICAL

        if(spanCount!=0) {
            //grid or staggerd
            val col = position / spanCount
            val row = position % spanCount

            //for same column
            when {
                row == 0 ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.left = 0
                            outRect.right = itemSpacing/2
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.top = 0
                            outRect.bottom = lineSpacing/2
                        }
                }
                row > 0 && row < spanCount-1 ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.left = itemSpacing/2
                            outRect.right = itemSpacing/2
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.top = lineSpacing/2
                            outRect.bottom = lineSpacing/2
                        }
                }
                row == spanCount-1 ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.left = itemSpacing/2
                            outRect.right = 0
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.top = lineSpacing/2
                            outRect.bottom = 0
                        }
                }
            }

            //for same row
            when {
                col == 0 ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.top = 0
                            outRect.bottom = lineSpacing/2
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.left = 0
                            outRect.right = itemSpacing/2
                        }
                }
                col > 0 && col < itemCount / spanCount  ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.top = lineSpacing/2
                            outRect.bottom = lineSpacing/2
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.left = itemSpacing/2
                            outRect.right = itemSpacing/2
                        }
                }
                col == itemCount / spanCount ->
                    when (orientation) {
                        RecyclerView.VERTICAL -> {
                            outRect.top = lineSpacing/2
                            outRect.bottom = 0
                        }
                        RecyclerView.HORIZONTAL -> {
                            outRect.left = itemSpacing/2
                            outRect.right = 0
                    }
                }
            }

        } else {
            //linear Only
            if (position == 0) when (orientation) {
                RecyclerView.VERTICAL -> {
                    outRect.top = if(itemCount != 0) lineSpacing else 0
                    outRect.bottom = lineSpacing/2
                }
                RecyclerView.HORIZONTAL -> {
                    outRect.left =  lineSpacing/2
                    outRect.right = if(itemCount != 0) itemSpacing else 0
                }
            }
            else if (0 < position && position < itemCount-1) when (orientation) {
                RecyclerView.VERTICAL -> {
                    outRect.top = lineSpacing/2
                    outRect.bottom = lineSpacing/2
                }
                RecyclerView.HORIZONTAL -> {
                    outRect.left = itemSpacing/2
                    outRect.right = itemSpacing/2
                }
            }
            else if (position == itemCount-1) when (orientation) {
                RecyclerView.VERTICAL -> {
                    outRect.top =  lineSpacing/2
                    outRect.bottom = lineSpacing
                }
                RecyclerView.HORIZONTAL -> {
                    outRect.left =  lineSpacing/2
                    outRect.right = lineSpacing
                }
            }
        }
    }
}

private fun dp2px(context: Context, dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}