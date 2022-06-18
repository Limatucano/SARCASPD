package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.br.faeterj.paracambi.sarcaspd.R

class SpinnerField(private var context: Context) : Field() {

    override fun getField(): View {
        val newContext = ContextThemeWrapper(context, R.style.AutoComplete)

        return MultipleSpinner(newContext)
    }
}