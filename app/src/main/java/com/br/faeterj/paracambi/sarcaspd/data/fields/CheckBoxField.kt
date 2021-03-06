package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.view.adapter.MultipleAnswerAdapter

class CheckBoxField(
    private val context: Context,
    private val question: Question,
    private val adapter : MultipleAnswerAdapter
) : Field() {

    override fun getField(): View {
        val view = LinearLayout(context)
        val recyclerview = RecyclerView(context)
        val textView = TextView(context,null,0, R.style.questionTitleStyle)

        textView.setPadding(0,40,0,0)
        textView.text = question.text

        recyclerview.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        recyclerview.adapter = adapter
        recyclerview.tag = question.id
        recyclerview.isNestedScrollingEnabled = false

        view.tag = question.id
        view.orientation = LinearLayout.VERTICAL
        view.addView(textView)
        view.addView(recyclerview)
        return view
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}