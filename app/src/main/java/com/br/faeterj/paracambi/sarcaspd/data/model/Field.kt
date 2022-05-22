package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Field(
    val title: String,
    val fieldType: String,
    val hint: String?,
    val inputType: String?,
    val id: String,
    val min: Int?,
    val max: Int?,
    val defaultValue: @RawValue Any? = null,
    val method: String?
) : Parcelable