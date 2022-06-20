package com.br.faeterj.paracambi.sarcaspd.view.adapter

import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question

interface OnClickCheckListener{
    fun onOptionChecked(item: Option, question : Question, state : Boolean)
}
