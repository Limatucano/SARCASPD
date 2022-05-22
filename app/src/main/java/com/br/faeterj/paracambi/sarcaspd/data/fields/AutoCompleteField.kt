package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView

class AutoCompleteField(context: Context) : Field(context) {

    override fun getField(): View {
        val view = AutoCompleteTextView(context)
//        view.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
        return view
    }
}