package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Field(
    val title: String = "",
    val fieldType: String = "",
    val hint: String? = null,
    val inputType: String? = null,
    val id: String = "",
    val min: Int? = null,
    val max: Int? = null,
    val defaultValue: @RawValue Any? = null,
    val method: String? = null,
    val category: String? = null,
    val fields : List<Field>? = null
) : Parcelable