package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View
import android.widget.*

class SelectField(
    private var context: Context,
    private var adapter: ArrayAdapter<*>?,
) : Field() {
    override fun getField(): View {
        val view = SpinnerField(context).getField() as Spinner
        view.adapter = adapter

        return view
    }
}