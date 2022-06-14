package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Block(
    @SerializedName("numero") val number : Int? = 0,
    @SerializedName("descricao") val description : String? = null,
    @SerializedName("perguntas") val questions : List<Question>?
): Parcelable
