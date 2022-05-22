package com.br.faeterj.paracambi.sarcaspd.data.model

data class Field(
    val title : String,
    val fieldType : String,
    val hint : String?,
    val inputType : String?,
    val id : String,
    val min : Int?,
    val max : Int?,
    val defaultValue : Any?
)