package com.br.faeterj.paracambi.sarcaspd.viewModel

import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.br.faeterj.paracambi.sarcaspd.data.model.*
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import com.br.faeterj.paracambi.sarcaspd.util.ViewUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val fieldsRepository: FieldsRepository
) : ViewModel() {
    val fields = MutableLiveData<Form>()
    val result = MutableLiveData<FinalResult?>()

    fun getFields() {
        viewModelScope.launch(Dispatchers.IO) {
            val form = fieldsRepository.readJsonToCreateField()
            fields.postValue(form)
        }
    }

    fun calculateFields(rules: List<Rule>, fieldsCreated : List<View>){
        val answersSelected : ArrayList<OptionsRule> = arrayListOf()

        for(fieldCreated in fieldsCreated){
            val viewGroup = fieldCreated as LinearLayout
            val viewField = viewGroup.getChildAt(1)

            when(viewField.javaClass.simpleName){
                "Spinner" -> {
                    val field = viewField as Spinner
                    val optionSelected = field.selectedItem as Option
                    val optionId = optionSelected.id
                    val questionId = field.tag.toString().toInt()

                    if(optionId != 0) {
                        answersSelected.add(OptionsRule(questionId, optionId))
                    }
                }
                "RecyclerView" -> {
                    val parent = viewField as RecyclerView
                    val checkBoxes = ViewUtil.findViewsWithType(parent, CheckBox::class.java)
                    val checkBoxesChecked = checkBoxes.filter { it.isChecked }

                    for(checkBox in checkBoxesChecked){
                        val optionId = checkBox.tag.toString().toInt()
                        val questionId = parent.tag.toString().toInt()
                        answersSelected.add(OptionsRule(questionId, optionId))
                    }
                }
            }
        }
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

        result.postValue(fieldsRepository.getFinalResult(points))
    }

}