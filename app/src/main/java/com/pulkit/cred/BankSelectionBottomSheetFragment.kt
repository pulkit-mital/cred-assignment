package com.pulkit.cred

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pulkit.cred.databinding.DialogBankSelectionBinding
import com.pulkit.cred.listener.ToggleListener

class BankSelectionBottomSheetFragment(
    private val bottomSheetHeight: Int,
    private val listener: ToggleListener
) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogBankSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        binding = DialogBankSelectionBinding.inflate(LayoutInflater.from(requireContext()))
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.height = bottomSheetHeight
        binding.coordinatorLayout.layoutParams = layoutParams
        dialog.setContentView(binding.root)

        return dialog

    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet)
            val mBehavior = BottomSheetBehavior.from(bottomSheet!!)
            mBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    Log.e("behavior", newState.toString())
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }
            })
            mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

    }

    override fun onResume() {
        super.onResume()
        listener.onExpanded()
    }

    override fun onPause() {
        super.onPause()
        listener.onCollapsed()
    }

}