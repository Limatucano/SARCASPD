package com.br.faeterj.paracambi.sarcaspd.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question

class MultipleAnswerAdapter(
    private val question : Question,
    private val clickListener : OnClickCheckListener
) : RecyclerView.Adapter<MultipleAnswerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.multiple_answer_item, parent, false)

        return ViewHolder(view,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = question.options[position]
        holder.initialize(item,clickListener,question, position)
    }

    override fun getItemCount(): Int = question.options.size

    class ViewHolder(itemView: View, parentContext: Context) : RecyclerView.ViewHolder(itemView) {

        val titleOption = itemView.findViewById<CheckBox>(R.id.option)
        fun initialize(option: Option, action: OnClickCheckListener, question: Question, position: Int) {
            titleOption.text = option.title
            val viewGroup = itemView as LinearLayout
            val checkBox = (viewGroup.children.find { it.tag == "checkbox" }) as CheckBox

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                action.onOptionChecked(option, question, isChecked)
            }
//            for(view in viewGroup.children){
//                if(view.tag == "checkbox"){
//                    val checkBox = view as CheckBox
//                    checkBox.setOnCheckedChangeListener { _, isChecked ->
//                    if(isChecked){
//                        action.onOptionChecked(option, question)
//                    }
//            }
//                }
//            }
        }
    }
}


