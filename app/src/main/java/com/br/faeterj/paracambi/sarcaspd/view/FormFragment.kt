package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.fields.CheckBoxField
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.model.*
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.view.adapter.MultipleAnswerAdapter
import com.br.faeterj.paracambi.sarcaspd.view.adapter.OnClickCheckListener
import com.br.faeterj.paracambi.sarcaspd.view.adapter.SpinnerAdapter
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : BaseFragment<FragmentFormBinding>(FragmentFormBinding::inflate), OnClickCheckListener {

    private lateinit var direction: NavDirections
    private val viewModel: FormViewModel by viewModels()
    private lateinit var form: Form
    private lateinit var address: Address
    private val fieldsCreated: MutableList<View> = mutableListOf()
    private val questions: MutableList<Question> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments ?: return
        val args = FormFragmentArgs.fromBundle(bundle)
        form = args.form
        address = args.address
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(
            visibility = true,
            title = getString(R.string.title_text),
            navigationBack = false
        )

        binding.address.text = address.address

        form.blocks?.let { blocks ->
            scrollingList(blocks)
        }
        viewModel.result.observe(viewLifecycleOwner) { result ->
            if(result != null){
                direction = FormFragmentDirections.actionFormFragmentToResultFragment(result)
                view.findNavController().navigate(direction)
            }
            viewModel.result.postValue(null)
        }
        binding.buttonSend.setOnClickListener {
            if(hasPermissions(requireContext())){
                viewModel.calculateFields(form.rules, fieldsCreated)
            }else{
                checkPermission(false)
            }

        }

        viewModel.fieldsError.observe(viewLifecycleOwner) { views ->
            val allTextView = getAllTextViews()
            for (textView in allTextView) {
                textView.error = null
            }
            for (fieldError in views) {
                val textView = (fieldError as TextView)
                textView.error = "error"
            }
        }

    }

    override fun onOptionChecked(item: Option, question: Question, state: Boolean) {
        controlVisibility(item, question, state)
    }

    private fun getAllTextViews(): List<TextView> = fieldsCreated.filter {
        it.visibility == View.VISIBLE
    }.map {
        ((it as LinearLayout).getChildAt(0) as TextView)
    }

    private fun scrollingList(blocks: List<Block>) {

        for (block in blocks) {
            block.questions?.forEach { question ->
                questions.add(question)
                buildFields(question)
            }
        }

    }

    private fun buildFields(question: Question) {

        val viewField: View = if (question.multipleAnswer == true) {
            val adapter = MultipleAnswerAdapter(question, this)
            CheckBoxField(requireContext(), question, adapter).getField()
        } else {
            val adapter = SpinnerAdapter(requireContext(), question)
            SelectField(requireContext(), adapter, question).getField()
        }

        if (viewField.parent != null) {
            (viewField.parent as ViewGroup).removeView(viewField)
        }
        if (question.idOption != null) {
            viewField.visibility = View.GONE
        }

        fieldsCreated.add(viewField)
        binding.parentLayout.addView(viewField)
    }

    private fun controlVisibility(option: Option, question: Question, state: Boolean) {

        if (question.id != null) {
            val childQuestions = findMultipleAnswers(question.id)
            val questionByOption = childQuestions.filter { it.idOption == option.id }
            val field = fieldsCreated.filter { it.tag == questionByOption[0].id }

            field[0].visibility = if (state) View.VISIBLE else View.GONE
        }

    }

    private fun findMultipleAnswers(idQuestion: Int): List<Question> =
        questions.filter { question ->
            question.idQuestion == idQuestion
        }
}

