package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.AutoCompleteTextView
import com.br.faeterj.paracambi.sarcaspd.R
import com.google.android.material.textfield.TextInputLayout

class TextField(private var context: Context, private var fieldDetail: com.br.faeterj.paracambi.sarcaspd.data.model.Field) : Field(){
    override fun getField(): View {
        val newContext = ContextThemeWrapper(context, R.style.TextInputLayout)
        val viewParent = TextInputLayout(newContext)
        val view = AutoCompleteField(context).getField() as AutoCompleteTextView
        view.hint = fieldDetail.hint

        viewParent.addView(view)
        return view
    }

}