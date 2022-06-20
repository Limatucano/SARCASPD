package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View
import android.widget.*
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.view.adapter.SpinnerAdapter

class SelectField(
    private var context: Context,
    private var adapter: SpinnerAdapter,
    private var question: Question
) : Field() {
    override fun getField(): View {
        val view = LinearLayout(context)
        val spinner = SpinnerField(context).getField() as Spinner
        val textView = TextView(context)

        textView.text = question.text

        spinner.adapter = adapter

        view.tag = question.id
        view.orientation = LinearLayout.VERTICAL
        view.addView(textView)
        view.addView(spinner)
        return view
    }
}