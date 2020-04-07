package com.sideproject.manlihyang.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sideproject.manlihyang.base.BaseRecyclerViewAdapter
import com.sideproject.manlihyang.base.BaseViewHolder
import com.sideproject.manlihyang.databinding.ItemFollowBinding
import com.sideproject.manlihyang.model.main.Follow

class FollowAdapter : BaseRecyclerViewAdapter<Follow>(
    object : DiffUtil.ItemCallback<Follow>() {
        override fun areItemsTheSame(oldItem: Follow, newItem: Follow): Boolean {
            return oldItem.senderId == newItem.senderId
        }

        override fun areContentsTheSame(oldItem: Follow, newItem: Follow): Boolean {
            return oldItem.senderId == newItem.senderId &&
                    oldItem.senderName == newItem.senderName &&
                    oldItem.phone == newItem.phone
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Follow> {
        return FollowViewHolder(
            ItemFollowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class FollowViewHolder(
        private val mBinding : ItemFollowBinding
    ) : BaseViewHolder<Follow>(mBinding) {
        override fun onBind(item: Follow?) {
            super.onBind(item)
        }
    }
}