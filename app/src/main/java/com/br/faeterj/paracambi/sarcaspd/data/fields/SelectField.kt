package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import com.br.faeterj.paracambi.sarcaspd.R
import com.google.android.material.textfield.TextInputLayout

class SelectField(private var context: Context, private var adapter: ArrayAdapter<*>) : Field() {
    override fun getField(): View {
        val newContext = ContextThemeWrapper(context, R.style.TextInputLayout)
        val viewParent = TextInputLayout(newContext)
        val view = AutoCompleteField(context).getField() as AutoCompleteTextView

        view.setAdapter(adapter)

        viewParent.addView(view)
        return view
    }
}