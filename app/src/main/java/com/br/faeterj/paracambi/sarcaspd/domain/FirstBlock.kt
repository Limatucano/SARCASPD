package com.br.faeterj.paracambi.sarcaspd.domain

import android.content.Context
import com.br.faeterj.paracambi.sarcaspd.R

class FirstBlock(val context: Context) : BlockForm() {
    override fun calculateBlock(firstValue: String, secondValue: String) : Int {
        val year = firstValue.toInt()

        return 0
//        return when {
//            year <= 2000 -> {
//                if(secondValue == context.getString(R.string.cacimba)){
//                    return 5
//                }else if(secondValue == context.getString(R.string.escavado)){
//                    return 3
//                }else if(secondValue == context.getString(R.string.perfurado)){
//                    return 1
//                }else{
//                    return 0
//                }
//            }
//            year in 2001..2010 -> {
//                if(secondValue == context.getString(R.string.cacimba)){
//                    return 5
//                }else if(secondValue == context.getString(R.string.escavado)){
//                    return 4
//                }else if(secondValue == context.getString(R.string.perfurado)){
//                    return 3
//                }else{
//                    return 0
//                }
//            }
//            year > 2010 -> {
//                if(secondValue == context.getString(R.string.cacimba)){
//                    return 5
//                }else if(secondValue == context.getString(R.string.escavado)){
//                    return 5
//                }else if(secondValue == context.getString(R.string.perfurado)){
//                    return 5
//                }else{
//                    return 0
//                }
//            }
//
//            else -> 0
//        }
    }


}