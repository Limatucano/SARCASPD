package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.br.faeterj.paracambi.sarcaspd.R
import java.text.AttributedCharacterIterator

class AutoCompleteField(private var context: Context) : Field() {

    override fun getField(): View {
        val newContext = ContextThemeWrapper(context, R.style.AutoComplete)
        val view = AutoCompleteTextView(newContext)

        return view
    }
}