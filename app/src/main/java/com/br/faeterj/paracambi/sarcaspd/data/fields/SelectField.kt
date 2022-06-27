package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.br.faeterj.paracambi.sarcaspd.R
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
        val textView = TextView(context,null,0,R.style.questionTitleStyle)

        textView.setPadding(0,40,0,15)
        textView.text = question.text

        spinner.adapter = adapter
        spinner.tag = question.id
        spinner.background = ContextCompat.getDrawable(context, R.drawable.spinner_background)

        view.tag = question.id
        view.orientation = LinearLayout.VERTICAL
        view.addView(textView)
        view.addView(spinner)
        return view
    }
}