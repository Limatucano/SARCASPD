package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Form(
    val block : List<Field>
) : Parcelable