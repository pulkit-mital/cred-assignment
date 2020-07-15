package com.pulkit.cred

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pulkit.cred.adapters.EmiAdapter
import com.pulkit.cred.databinding.DialogEmiSelectionBinding
import com.pulkit.cred.listener.ItemClickListener
import com.pulkit.cred.listener.ToggleListener
import com.pulkit.cred.model.EmiOptions
import com.pulkit.cred.utils.MeasurementUtil

class EmiSelectionBottomSheetFragment(
    private val bottomSheetHeight: Int,
    private val listener: ToggleListener
) : BottomSheetDialogFragment(), View.OnClickListener, ToggleListener, ItemClickListener {

    private lateinit var binding: DialogEmiSelectionBinding
    private val emiOptions = ArrayList<EmiOptions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme)
        initializeData();
    }

    private fun initializeData() {
        emiOptions.add(EmiOptions("Rs 4000", "12 months", resources.getColor(R.color.brown), true))
        emiOptions.add(EmiOptions("Rs 5000", "9 months", resources.getColor(R.color.light_purple), false))
        emiOptions.add(EmiOptions("Rs 6000", "7 months", resources.getColor(R.color.blueish), false))
        emiOptions.add(EmiOptions("Rs 8000", "6 months", resources.getColor(R.color.brown), false))

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        binding = DialogEmiSelectionBinding.inflate(LayoutInflater.from(requireContext()))
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.height = bottomSheetHeight
        binding.coordinatorLayout.layoutParams = layoutParams
        binding.btnBankSelection.setOnClickListener(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val adapter = EmiAdapter(emiOptions,this)
        binding.recyclerView.adapter = adapter
        dialog.setContentView(binding.root)

        return dialog

    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<ViewGroup>(com.google.android.material.R.id.design_bottom_sheet)
            val mBehavior = BottomSheetBehavior.from(bottomSheet!!)
            mBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_bank_selection -> {

                showPaymentBankSelectionDialog();
            }
        }
    }

    override fun onExpanded() {
        binding.emiGroup.visibility = View.GONE
        binding.llEmiLayout.visibility = View.VISIBLE
    }

    override fun onCollapsed() {
        binding.emiGroup.visibility = View.VISIBLE
        binding.llEmiLayout.visibility = View.INVISIBLE
    }

    private fun showPaymentBankSelectionDialog() {

        val backSelectionDialog = BankSelectionBottomSheetFragment(
            MeasurementUtil.getHeightOfBottomSheetDialog(
                activity!!,
                binding.llEmiLayout
            ), this
        )
        backSelectionDialog.show(childFragmentManager, "show")

    }

    override fun itemClicked(emiOptions: EmiOptions) {
        binding.emiValue.text = emiOptions.emi
        binding.emiMonth.text = emiOptions.months
    }
}