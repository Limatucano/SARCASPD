package com.br.faeterj.paracambi.sarcaspd.data.fields

import android.content.Context
import android.view.View

abstract class Field(context : Context?) : View(context) {

    abstract fun getField() : View
}