package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.model.Block
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.model.Option
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.view.adapter.SpinnerAdapter
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment() {

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
        lateinit var viewField : View

        question.options?.let {
            val adapter = SpinnerAdapter(requireContext(), question)
            viewField = SelectField(requireContext(), adapter, question).getField()
            listenerSpinner(viewField)
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

    private fun listenerSpinner(view : View){
        val spinner = (view as LinearLayout).getChildAt(1) as Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0){
                    controlVisibility(position, parent)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG,"nothing selected")
            }

        }
    }

    private fun controlVisibility(position : Int, parent : AdapterView<*>?){
        if(parent != null){
            val adapter = parent.adapter as SpinnerAdapter
            val optionSelected = adapter.getItem(position - 1) as Option
            val question = adapter.question

            if(question.multipleAnswer == true && question.id != null){
                val childQuestions = findMultipleAnswers(question.id)
                val questionByOption = childQuestions.filter { it.idOption ==  optionSelected.id}
                val field = fieldsCreated.filter { it.tag == questionByOption[0].id}

                field[0].visibility = if(field[0].visibility == View.GONE) View.VISIBLE else View.GONE
            }

        }
    }

    private fun findMultipleAnswers(idQuestion : Int): List<Question> = questions.filter { question ->
            question.idQuestion == idQuestion
    }


}