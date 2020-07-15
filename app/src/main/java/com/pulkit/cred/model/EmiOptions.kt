package com.pulkit.cred.model

data class EmiOptions(
    val emi: String,
    val months: String,
    val backgroundColor: Int,
    var isChecked: Boolean
)