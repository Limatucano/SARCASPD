package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import androidx.appcompat.widget.AppCompatSpinner


class MultipleSpinner(context: Context) : AppCompatSpinner(context) {


    override fun setSelection(position: Int) {
        val sameSelected = position == selectedItemPosition
        super.setSelection(position)
        if (sameSelected) {
            onItemSelectedListener!!.onItemSelected(this, selectedView, position, selectedItemId)
        }
    }

    override fun setSelection(position: Int, animate: Boolean) {
        val sameSelected = position == selectedItemPosition
        super.setSelection(position, animate)
        if (sameSelected) {
            onItemSelectedListener!!.onItemSelected(this, selectedView, position, selectedItemId)
        }
    }
}