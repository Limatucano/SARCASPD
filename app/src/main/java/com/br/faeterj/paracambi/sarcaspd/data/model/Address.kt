package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val address: String,
    val street: String?,
    val number: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postalCode: String?,
    val knownName: String?
) : Parcelable
