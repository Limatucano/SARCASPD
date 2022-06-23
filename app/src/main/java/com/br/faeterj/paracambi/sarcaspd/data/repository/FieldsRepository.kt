package com.br.faeterj.paracambi.sarcaspd.data.repository

import android.content.Context
import com.br.faeterj.paracambi.sarcaspd.data.model.*
import com.br.faeterj.paracambi.sarcaspd.util.Json
import com.br.faeterj.paracambi.sarcaspd.util.ResultUtil.ALTO
import com.br.faeterj.paracambi.sarcaspd.util.ResultUtil.ATENCAO
import com.br.faeterj.paracambi.sarcaspd.util.ResultUtil.BAIXO
import com.br.faeterj.paracambi.sarcaspd.util.ResultUtil.finalResults
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

    fun getFinalResult(point : Int) : FinalResult? {
        lateinit var finalResult: FinalResult
        return when{
            point in 0..14->{
                finalResult = finalResults[BAIXO]!!
                finalResult.total = point
                finalResult
            }
            point in 15..25->{
                finalResult = finalResults[ATENCAO]!!
                finalResult.total = point
                finalResult
            }
            point >= 26 -> {
                finalResult = finalResults[ALTO]!!
                finalResult.total = point
                finalResult
            }
            else ->  null
        }
    }

    suspend fun calculateFields(rules: List<Rule>, answersSelected : List<OptionsRule>) : Int{
        return withContext(Dispatchers.IO){

            var points = 0
            for(rule in rules){
                if(rule.options != null && rule.value != null){
                    var counter = 0
                    for (option in rule.options){
                        for(answers in answersSelected){
                            if(answers == option){
                                counter++
                                break
                            }
                        }
                        if(counter > 1){
                            counter = 0
                            points += rule.value
                        }
                    }
                }
            }
            points
        }
    }
}