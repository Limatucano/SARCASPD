package com.br.faeterj.paracambi.sarcaspd.viewModel

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import java.util.*

class FormViewModel(val firstBlock : FirstBlock): ViewModel() {
    private val TAG = "FormViewModel"
    val years = MutableLiveData<MutableList<String>>()
    val YEARS_DEFAULT_OPTION = "Não sei"

    fun generateYears(){
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val oneHundredYearAgo = currentYear - 100

        val years = mutableListOf<String>()
        years.add(YEARS_DEFAULT_OPTION)
        for(year in currentYear downTo oneHundredYearAgo){
            years.add(year.toString())
        }
        years.add("Menor que ${years.last()}")
        this.years.postValue(years)
    }

    fun calculateFirstBlock(year: String?, artesianWellType : String?){
        if(year != null && artesianWellType != null){
            val teste = firstBlock.calculateBlock(year, artesianWellType)
            Log.d(TAG,teste.toString())
        }
    }
}