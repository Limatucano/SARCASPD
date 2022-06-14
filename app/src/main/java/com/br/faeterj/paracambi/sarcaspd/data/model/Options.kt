package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Options(
    @SerializedName("id") val id: Int?,
    @SerializedName("titulo") val title: String?,
) : Parcelable
