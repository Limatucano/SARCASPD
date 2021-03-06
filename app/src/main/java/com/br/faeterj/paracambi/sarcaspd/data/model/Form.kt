package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Form(
    @SerializedName("blocos") val blocks: List<Block>?,
    @SerializedName("regrasCalculo") val rules : List<Rule>
) : Parcelable