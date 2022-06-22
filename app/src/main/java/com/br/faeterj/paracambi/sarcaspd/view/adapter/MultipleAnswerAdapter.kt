package com.br.faeterj.paracambi.sarcaspd.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.util.ViewUtil

class MultipleAnswerAdapter(
    private val question : Question,
    private val clickListener : OnClickCheckListener
) : RecyclerView.Adapter<MultipleAnswerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.multiple_answer_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = question.options[position]
        holder.initialize(item,clickListener,question)
    }

    override fun getItemCount(): Int = question.options.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleOption: CheckBox = itemView.findViewById(R.id.option)
        fun initialize(option: Option, action: OnClickCheckListener, question: Question) {
            titleOption.text = option.title
            titleOption.tag = option.id
            val viewGroup = itemView as LinearLayout
            val checkBoxes = ViewUtil.findViewsWithType(viewGroup, CheckBox::class.java)
            if(checkBoxes.isNotEmpty()){
                val checkBox = checkBoxes[0]
                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    action.onOptionChecked(option, question, isChecked)
                }
            }
        }
    }
}


