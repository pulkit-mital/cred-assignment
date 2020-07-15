package com.pulkit.cred

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pulkit.cred.databinding.FragmentCreditSelectionBinding
import com.pulkit.cred.listener.ToggleListener
import com.pulkit.cred.utils.MeasurementUtil
import me.tankery.lib.circularseekbar.CircularSeekBar
import me.tankery.lib.circularseekbar.CircularSeekBar.OnCircularSeekBarChangeListener


class CreditSelectionBottomSheetFragment: Fragment(), ToggleListener,
    View.OnClickListener {

    private lateinit var binding: FragmentCreditSelectionBinding
    private var creditValue: String = "Rs. 1,50,000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreditSelectionBinding.inflate(inflater, container, false)
        binding.btnEmiSelection.setOnClickListener(this)
        binding.seekbar.setOnSeekBarChangeListener(object : OnCircularSeekBarChangeListener {
            override fun onProgressChanged(
                circularSeekBar: CircularSeekBar,
                progress: Float,
                fromUser: Boolean
            ) {
                creditValue = "Rs. ${progress.toInt()}"
                binding.tvSeekBarValue.text = creditValue

            }

            override fun onStopTrackingTouch(seekBar: CircularSeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: CircularSeekBar) {
            }
        })
        binding.tvSeekBarValue.text = creditValue
        return binding.root
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_emi_selection -> {
                showEmiSelectionDialog()
            }
        }
    }


    private fun showEmiSelectionDialog() {
        val emiDialogFragment = EmiSelectionBottomSheetFragment(MeasurementUtil.getHeightOfBottomSheetDialog(activity!!, binding.llCreditLayout), this)
        emiDialogFragment.show(childFragmentManager, "show")
    }

    private fun collapseParentView() {
        binding.parentGroup.visibility = View.GONE
        binding.llCreditLayout.visibility = View.VISIBLE
        binding.tvCreditValue.text = creditValue
    }
    private fun expandParentView() {
        binding.parentGroup.visibility = View.VISIBLE
        binding.llCreditLayout.visibility = View.INVISIBLE
    }

    override fun onExpanded() {
        collapseParentView()
    }

    override fun onCollapsed() {
        expandParentView()
    }


}