package com.br.faeterj.paracambi.sarcaspd.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question

class SpinnerAdapter(
    val context: Context,
    val question: Question,
) : BaseAdapter() {

    override fun getCount(): Int {
        if(question.options != null){
            return question.options.size + 1
        }
        return 0
    }

    override fun getItem(position: Int): Any {
        if(question.options != null){
            return question.options[position]
        }
        return 0
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val viewHolder = ViewHolder(view)

        val currentItem = if(position == 0){
            Option(
                id = 0,
                title = "Selecione uma opção"
            )
        }else{
            if(getItem(position - 1) == 0) return view
            getItem(position - 1) as Option
        }
        viewHolder.label.text = currentItem.title
        return view
    }

    private class ViewHolder(row : View?){
        val label: TextView

        init {
            this.label = row?.findViewById(android.R.id.text1) as TextView
        }
    }
}