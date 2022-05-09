package com.br.faeterj.paracambi.sarcaspd.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormViewModelFactory : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FormViewModel::class.java)){
            FormViewModel() as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}