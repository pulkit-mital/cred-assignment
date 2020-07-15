package com.pulkit.cred.listener

import com.pulkit.cred.model.EmiOptions

interface ItemClickListener {
    fun itemClicked(emiOptions: EmiOptions)
}