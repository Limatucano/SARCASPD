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
    private val context: Context,
    private val question: Question,
) : BaseAdapter() {

    override fun getCount(): Int = question.options.size + 1

    override fun getItem(position: Int): Any {
        return if(position == 0){
            Option(
                id = DEFAULT_ID,
                title = DEFAULT_TITLE
            )
        }else {
            question.options[position - 1]
        }
    }


    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val viewHolder = ViewHolder(view)

        val currentItem = getItem(position) as Option
        viewHolder.label.text = currentItem.title
        return view
    }

    private class ViewHolder(row : View?){
        val label: TextView

        init {
            this.label = row?.findViewById(android.R.id.text1) as TextView
        }
    }

    companion object{
        const val DEFAULT_TITLE = "Selecione uma opção"
        const val DEFAULT_ID = 0
    }
}