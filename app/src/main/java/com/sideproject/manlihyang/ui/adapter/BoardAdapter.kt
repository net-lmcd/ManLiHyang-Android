package com.sideproject.manlihyang.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sideproject.manlihyang.base.BaseRecyclerViewAdapter
import com.sideproject.manlihyang.base.BaseViewHolder
import com.sideproject.manlihyang.databinding.ItemBoardsBinding
import com.sideproject.manlihyang.model.main.Board
import com.sideproject.manlihyang.util.OnItemSelectedListener

class BoardAdapter : BaseRecyclerViewAdapter<Board>(
    object : DiffUtil.ItemCallback<Board>() {
        override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
            return oldItem.write_id == newItem.write_id
        }

        override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
            return oldItem.write_id == newItem.write_id &&
                    oldItem.title == newItem.title &&
                    oldItem.content == newItem.content
        }
    }
) {
    private var onItemSelectedListener : OnItemSelectedListener<Board>? = null
    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener<Board>) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Board> {
        return BoardViewHolder(
            ItemBoardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class BoardViewHolder(
        private val mBinding : ItemBoardsBinding
    ) : BaseViewHolder<Board>(mBinding) {
        override fun onBind(item: Board?) {
            super.onBind(item)
            if(item==null) return
            itemView.setOnClickListener {
                onItemSelectedListener?.invoke(item, adapterPosition)
            }
            //Glide.with(itemView.context).load(R.drawable.board_sample_t).into(mBinding.boardImage)
        }
    }
}