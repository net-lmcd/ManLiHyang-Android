package com.sideproject.manlihyang.side.contents.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sideproject.manlihyang.databinding.ItemQuestionBinding
import com.sideproject.manlihyang.databinding.ItemQuestionDropdownBinding
import com.sideproject.manlihyang.side.contents.base.BaseSpinnerAdapter

class QuestionAdapter(
    context : Context
) : BaseSpinnerAdapter<String>(context) {

    override fun onCreateItemDropDownViewHolder(
        inflator: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<String> {
        return QuestionDropDownViewHolder(
            ItemQuestionDropdownBinding.inflate(
                inflator,
                parent,
                false
            )
        )
    }

    override fun onCreateItemViewHolder(
        inflator: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<String> {
        return QuestionViewHolder(
            ItemQuestionBinding.inflate(
                inflator,
                parent,
                false
            )
        )
    }

    class QuestionDropDownViewHolder(
        private val mBinding : ItemQuestionDropdownBinding
    ) : BaseViewHolder<String>(mBinding) {
        override fun onBind(item: String?) {
            super.onBind(item)
        }
    }

    class QuestionViewHolder(
        private val mBinding : ItemQuestionBinding
    ) : BaseViewHolder<String>(mBinding) {
        override fun onBind(item: String?) {
            super.onBind(item)
        }
    }
}