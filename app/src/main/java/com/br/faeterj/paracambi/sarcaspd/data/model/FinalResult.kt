package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FinalResult(
    val total : Int,
    val risk : String,
    val action : String,
) : Parcelable
