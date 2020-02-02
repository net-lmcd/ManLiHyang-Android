package com.sideproject.manlihyang.side.contents.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.ViewDataBinding
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import java.util.*

abstract class BaseSpinnerAdapter<T>(
    context : Context
) : ArrayAdapter<T>(context, 0, arrayListOf()) {

    private var _layoutInflator: LayoutInflater? = null
    private val layoutInflator: LayoutInflater get() {
            if(_layoutInflator == null)
                _layoutInflator = LayoutInflater.from(context)
            return _layoutInflator!!
        }

    open fun submitList(list : List<T>) {
        this.clear()
        this.addAll(list)
        this.notifyDataSetChanged()
    }

    //스피너를 클릭했을때 DropDown 형식으로 표현되면서 보여지는 view
    @Suppress("UNCHECKED_CAST")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        return if(convertView==null) {
            val viewHolder
                    = onCreateItemDropDownViewHolder(layoutInflator, parent)
            val view = viewHolder.itemView
            view.tag = viewHolder
            viewHolder.onBind(getItem(position))
            view
        } else {
            val viewHolder
                    = convertView.tag as BaseViewHolder<T>
            viewHolder.onBind(getItem(position))
            convertView
        }
    }

    //스피너가 처음 보여질때의 view
    @Suppress("UNCHECKED_CAST")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        return if(convertView==null) {
            val viewHolder
                    = onCreateItemViewHolder(layoutInflator, parent)
            val view = viewHolder.itemView
            view.tag = viewHolder
            viewHolder.onBind(getItem(position))
            view
        } else {
            val viewHolder
                    = convertView.tag as BaseViewHolder<T>
            viewHolder.onBind(getItem(position))
            convertView
        }
    }

    abstract fun onCreateItemDropDownViewHolder(
        inflator: LayoutInflater,
        parent: ViewGroup
    ) : BaseViewHolder<T>

    abstract fun onCreateItemViewHolder(
        inflator: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<T>

    open class BaseViewHolder<T>(
        private val viewDataBinding : ViewDataBinding
    ) {
        val itemView : View = viewDataBinding.root

        open fun onBind(item: T?) {
            if(item == null) return
            viewDataBinding.setVariable(BR.item, item)
            viewDataBinding.executePendingBindings()
        }
    }
}