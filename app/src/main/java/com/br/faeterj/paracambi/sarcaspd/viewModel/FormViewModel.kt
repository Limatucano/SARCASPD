package com.br.faeterj.paracambi.sarcaspd.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import com.br.faeterj.paracambi.sarcaspd.util.Json
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.coroutines.coroutineContext

class FormViewModel(val firstBlock: FirstBlock) : ViewModel() {
    private val TAG = "FormViewModel"
    val years = MutableLiveData<MutableList<String>>()
    val fields = MutableLiveData<Form>()

    fun generateYears(){
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val oneHundredYearAgo = currentYear - 100

        val years = mutableListOf<String>()
        for(year in currentYear downTo oneHundredYearAgo){
            years.add(year.toString())
        }
        years.add("Menor que ${years.last()}")
        this.years.postValue(years)
    }

    fun calculateFirstBlock(year: String?, artesianWellType : String?){
        if(year != null && artesianWellType != null){
            val firstBlockValue = firstBlock.calculateBlock(year, artesianWellType)
            Log.d(TAG,firstBlockValue.toString())
        }
    }
    fun getFields(context: Context){
        val fieldsRepository = FieldsRepository()
        viewModelScope.launch(Dispatchers.IO) {
            val form = fieldsRepository.readJsonToCreateField(context)
            fields.postValue(form)
        }
    }

}