package com.br.faeterj.paracambi.sarcaspd.data.repository

import android.content.Context
import com.br.faeterj.paracambi.sarcaspd.data.model.FinalResult
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
        //TODO: validate json structure, if it has option, block, question sections
        return withContext(Dispatchers.IO) {
            val fieldsJson = Json.getJsonFromAssets(context, "fields.json")
            val fields: Form = Gson().fromJson(fieldsJson, Form::class.java)
            fields
        }
    }

    fun getFinalResult(point : Int) : FinalResult? = when{
            point in 0..14->{
                 FinalResult(
                    total = point,
                    risk = "Baixo - Reduzido",
                    action = "Teste a água anualmente para bactérias e nitrato"
                )
            }
            point in 15..25->{
                 FinalResult(
                    total = point,
                    risk = "Atenção -Cuidado",
                    action = "Teste a água. Execute a análise de risco mais detalhada ou aplique outra avaliação mais completa"
                )
            }
            point >= 26 -> {
                 FinalResult(
                    total = point,
                    risk = "Alto - Perigo",
                    action = "Teste imediatamente a água. Desenvolva um"
                )
            }
            else ->  null
    }
}