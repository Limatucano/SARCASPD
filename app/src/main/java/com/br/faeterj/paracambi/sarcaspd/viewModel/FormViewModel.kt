package com.br.faeterj.paracambi.sarcaspd.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val fieldsRepository: FieldsRepository
) : ViewModel() {
    val fields = MutableLiveData<Form>()

    fun getFields() {
        viewModelScope.launch(Dispatchers.IO) {
            val form = fieldsRepository.readJsonToCreateField()
            fields.postValue(form)
        }
    }

    fun calculateFields(){

    }

}