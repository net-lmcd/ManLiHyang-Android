package com.sideproject.manlihyang.side.contents.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sideproject.manlihyang.databinding.ItemFollowBinding
import com.sideproject.manlihyang.side.contents.base.BaseRecyclerViewAdapterImpl
import com.sideproject.manlihyang.side.contents.base.MVVMViewHolder
import com.sideproject.manlihyang.side.contents.model.main.Follow

class FollowAdapter : BaseRecyclerViewAdapterImpl<Follow>(
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVVMViewHolder<Follow> {
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
    ) : MVVMViewHolder<Follow>(mBinding) {
        override fun onBind(item: Follow?) {
            super.onBind(item)
        }
    }
}