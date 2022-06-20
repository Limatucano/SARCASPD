package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.br.faeterj.paracambi.sarcaspd.data.fields.CheckBoxField
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.model.Block
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.view.adapter.MultipleAnswerAdapter
import com.br.faeterj.paracambi.sarcaspd.view.adapter.OnClickCheckListener
import com.br.faeterj.paracambi.sarcaspd.view.adapter.SpinnerAdapter
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment(), OnClickCheckListener{

    private val TAG = "FormFragment"
    private lateinit var viewBinding: FragmentFormBinding
    private val viewModel: FormViewModel by viewModels()
    private lateinit var form: Form
    private val fieldsCreated: MutableList<View> = mutableListOf()
    private val questions : MutableList<Question> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e(TAG, "Erro ao transferir dados")
            return
        }
        val args = FormFragmentArgs.fromBundle(bundle)
        form = args.form
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFormBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        form.blocks?.let { blocks ->
            scrollingList(blocks)
        }
        viewBinding.buttonSend.setOnClickListener {
            val rules = form.rules
            for(fieldCreated in fieldsCreated){
                val field = fieldCreated as Spinner
                val optionSelected = field.selectedItem as Option
                val optionId = optionSelected.id

                if(optionId == 0){
                    continue
                }

            }
        }

    }

    private fun scrollingList(blocks: List<Block>){

        for (block in blocks){
            block.questions?.forEach { question ->
                questions.add(question)
                buildFields(question)
            }
        }

    }

    private fun buildFields(question : Question){

        val viewField : View = if(question.multipleAnswer == true){
            val adapter =  MultipleAnswerAdapter(question, this)
            CheckBoxField(requireContext(), question, adapter).getField()
        }else{
            val adapter = SpinnerAdapter(requireContext(), question)
            SelectField(requireContext(), adapter, question).getField()
        }

        if (viewField.parent != null) {
            (viewField.parent as ViewGroup).removeView(viewField)
        }
        if(question.idOption != null){
            viewField.visibility = View.GONE
        }

        fieldsCreated.add(viewField)
        viewBinding.parentLayout.addView(viewField)
    }

    private fun controlVisibility(option : Option, question : Question, state: Boolean) {

        if (question.id != null) {
            val childQuestions = findMultipleAnswers(question.id)
            val questionByOption = childQuestions.filter { it.idOption == option.id }
            val field = fieldsCreated.filter { it.tag == questionByOption[0].id }

            field[0].visibility = if (state) View.VISIBLE else View.GONE
        }

    }

    private fun findMultipleAnswers(idQuestion : Int): List<Question> = questions.filter { question ->
        question.idQuestion == idQuestion
    }

    override fun onOptionChecked(item: Option, question: Question, state: Boolean) {
        controlVisibility(item, question, state)
    }
}
