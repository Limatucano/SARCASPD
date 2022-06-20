package com.br.faeterj.paracambi.sarcaspd.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    @SerializedName("id") val id : Int?,
    @SerializedName("texto") val text : String?,
    @SerializedName("multiplaResposta") val multipleAnswer : Boolean?,
    @SerializedName("idPergunta") val idQuestion : Int?,
    @SerializedName("idOpcao") val idOption : Int?,
    @SerializedName("opcoes") val options : List<Option>
) : Parcelable
