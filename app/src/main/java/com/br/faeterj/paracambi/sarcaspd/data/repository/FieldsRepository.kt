package com.br.faeterj.paracambi.sarcaspd.data.repository

import android.content.Context
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.util.Json
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FieldsRepository @Inject constructor(
    private val context: Context
    ) {
    suspend fun readJsonToCreateField() : Form {
        return withContext(Dispatchers.IO) {
            val fieldsJson = Json.getJsonFromAssets(context, "fields.json")
            val fields: Form = Gson().fromJson(fieldsJson, Form::class.java)
            fields
        }
    }
}