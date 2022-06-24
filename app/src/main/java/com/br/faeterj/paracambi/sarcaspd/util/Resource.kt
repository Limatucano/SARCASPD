package com.br.faeterj.paracambi.sarcaspd.util

data class Resource(
    val message : String
){
    companion object{

        fun errorJson() : Resource = Resource("Erro ao carregar o json fornecido, favor verificar e tentar novamente!")
    }
}
