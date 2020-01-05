package com.sideproject.manlihyang.side.contents.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ItemBoardsBinding
import com.sideproject.manlihyang.side.contents.base.BaseRecyclerViewAdapterImpl
import com.sideproject.manlihyang.side.contents.base.MVVMViewHolder
import com.sideproject.manlihyang.side.contents.model.main.Board
import com.sideproject.manlihyang.side.contents.util.OnItemSelectedListener

class BoardAdapter : BaseRecyclerViewAdapterImpl<Board>(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVVMViewHolder<Board> {
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
    ) : MVVMViewHolder<Board>(mBinding) {
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