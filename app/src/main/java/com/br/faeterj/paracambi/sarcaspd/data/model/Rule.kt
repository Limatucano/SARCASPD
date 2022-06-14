package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rule(
    @SerializedName("opcoes") val options : List<OptionsRule>?,
    @SerializedName("valor") val value : Int?
) : Parcelable
