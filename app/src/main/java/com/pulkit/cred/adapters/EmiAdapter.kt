package com.pulkit.cred.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pulkit.cred.databinding.ItemEmiOptionBinding
import com.pulkit.cred.listener.ItemClickListener
import com.pulkit.cred.model.EmiOptions
import java.util.*

class EmiAdapter(
    private val emiOptionsList: ArrayList<EmiOptions>?,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<EmiAdapter.ViewHolder>() {
    private var lastIndexSelected = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemEmiOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val emiOptions = emiOptionsList!![position]
        holder.binding.tvEmiValue.text = "${emiOptions.emi} /mo"
        holder.binding.tvEmiMonth.text = "for ${emiOptions.months}"
        holder.binding.radioButton.isChecked = lastIndexSelected == position
        holder.binding.cardView.setBackgroundColor(emiOptions.backgroundColor)
    }

    override fun getItemCount(): Int {
        return emiOptionsList?.size ?: 0
    }

    inner class ViewHolder(
        var binding: ItemEmiOptionBinding,
        listener: ItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                val emiOptions = emiOptionsList!![adapterPosition]
                lastIndexSelected = adapterPosition
                notifyDataSetChanged()
                listener.itemClicked(emiOptions)
            }
        }
    }

}