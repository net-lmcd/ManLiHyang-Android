package com.sideproject.manlihyang.side.contents.view.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.side.contents.util.Dialog
import com.sideproject.manlihyang.side.contents.util.MessageDialogClickListener
import com.sideproject.manlihyang.side.contents.view.onboarding.LoginActivity
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //https://stackoverflow.com/questions/43852562/round-corner-for-bottomsheetdialogfragment
        val view : View = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logout.setOnClickListener {
            Dialog.showMessageConfirm(it.context, childFragmentManager, "로그아웃 하시겠습니까?")
                .setMessageDialogClickListener(object : MessageDialogClickListener {
                    override fun confirmClick() {
                        // + login logic 추가
                        dismiss()
                        startActivity(Intent(it?.context, LoginActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    }
                    override fun cancelClick() {}
                })
        }

        edit_profile.setOnClickListener {
            startActivity(Intent(it.context, EditMypageActivity::class.java))
        }

        switch_noti.setOnCheckedChangeListener(
            object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    when(isChecked) {
                        true -> Toast.makeText(view.context, "noti on", Toast.LENGTH_SHORT).show()
                        false -> Toast.makeText(view.context, "noti off", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )
    }
}