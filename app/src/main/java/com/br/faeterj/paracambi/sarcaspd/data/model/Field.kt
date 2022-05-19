package com.br.faeterj.paracambi.sarcaspd.data.model

data class Field(
    val name : String,
    val type : String,
    val min : Int?,
    val max : Int?,
    val defaultValue : Any?
)