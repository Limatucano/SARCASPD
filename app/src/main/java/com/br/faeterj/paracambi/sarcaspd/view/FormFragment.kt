package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.model.Block
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.data.model.Question
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormFragment : Fragment() {

    private val TAG = "FormFragment"
    private lateinit var viewBinding: FragmentFormBinding
    private val viewModel: FormViewModel by viewModels()
    private lateinit var form: Form
    private val fieldsCreated: MutableList<View> = mutableListOf()

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

    }

    private fun scrollingList(blocks: List<Block>){

        for (block in blocks){
            block.questions?.forEach { question ->
                buildFields(question)
            }
        }

    }

    private fun buildFields(question : Question){
        val viewTitle = TextView(requireContext())
        viewTitle.text = question.text
        lateinit var viewField : View

        val options = question.options?.map { it.title }
        if (options != null) {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, options.toList())
            viewField = SelectField(requireContext(), adapter).getField()
        }

        if (viewField.parent != null) {
            (viewField.parent as ViewGroup).removeView(viewField)
        }

        fieldsCreated.add(viewField)
        viewBinding.parentLayout.addView(viewTitle)
        viewBinding.parentLayout.addView(viewField)
    }


}