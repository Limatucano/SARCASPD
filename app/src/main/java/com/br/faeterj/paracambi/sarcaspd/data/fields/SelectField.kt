package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.text.InputType
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.*
import com.br.faeterj.paracambi.sarcaspd.R
import com.google.android.material.textfield.TextInputLayout
import com.br.faeterj.paracambi.sarcaspd.data.model.Field as DetailField
import com.br.faeterj.paracambi.sarcaspd.data.fields.Field

class SelectField(
    private var context: Context,
    private var adapter: ArrayAdapter<*>?,
    private var fieldDetail: DetailField
) : Field() {
    override fun getField(): View {
        val newContext = ContextThemeWrapper(context, R.style.TextInputLayout)
        val viewParent = TextInputLayout(newContext)
        val view = AutoCompleteField(context).getField() as AutoCompleteTextView
        view.hint = fieldDetail.hint
        view.setAdapter(adapter)

        viewParent.addView(view)
        return view
    }
}