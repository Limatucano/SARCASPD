package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OptionsRule(
    @SerializedName("idPergunta") val idQuestion : Int?,
    @SerializedName("idOpcao") val idOption : Int?
) : Parcelable
