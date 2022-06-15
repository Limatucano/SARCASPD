package com.br.faeterj.paracambi.sarcaspd.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.br.faeterj.paracambi.sarcaspd.data.model.Option

class SpinnerAdapter(
    val context : Context,
    val options : List<Option>) : BaseAdapter() {

    override fun getCount(): Int = options.size + 1

    override fun getItem(position: Int): Any = options[position]

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