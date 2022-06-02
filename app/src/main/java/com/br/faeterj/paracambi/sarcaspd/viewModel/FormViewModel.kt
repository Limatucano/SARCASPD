package com.br.faeterj.paracambi.sarcaspd.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val fieldsRepository: FieldsRepository
) : ViewModel() {
    val fields = MutableLiveData<Form>()

    fun generateYears(): MutableList<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val oneHundredYearAgo = currentYear - 100

        val years = mutableListOf<String>()
        for (year in currentYear downTo oneHundredYearAgo) {
            years.add(year.toString())
        }
        years.add("Menor que ${years.last()}")
        return years
    }

    fun getFields() {
        viewModelScope.launch(Dispatchers.IO) {
            val form = fieldsRepository.readJsonToCreateField()
            fields.postValue(form)
        }
    }

}