package com.br.faeterj.paracambi.sarcaspd.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock

class FormViewModelFactory(val firstBlock : FirstBlock) : ViewModelProvider.Factory{

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FormViewModel::class.java)){
            FormViewModel(firstBlock) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}