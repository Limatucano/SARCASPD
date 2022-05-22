package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModelFactory

class FormFragment : Fragment() {

    private val TAG = "FormFragment"
    private lateinit var viewBinding : FragmentFormBinding
    private lateinit var viewModel : FormViewModel

    private var yearBuilt : String? = null
    private var artesianWellType : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFormBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //TODO: Inject blocks inside factory
        val fieldsCreated : MutableList<View> = mutableListOf()
        val years : MutableList<String> = mutableListOf("1","2","3")
        viewModel = ViewModelProvider(this, FormViewModelFactory(FirstBlock(requireContext()))).get(FormViewModel::class.java)
        viewModel.generateYears()

        viewModel.getFields(requireContext())

        viewModel.fields.observe(viewLifecycleOwner){ fields ->
            for(field in fields.block){

                val viewTitle = TextView(requireContext())
                val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)
                val viewField = SelectField(requireContext(), adapter).getField()
                viewTitle.text = field.title

                if (viewField.parent != null) {
                    (viewField.parent as ViewGroup).removeView(viewField) // <- fix
                }
                fieldsCreated.add(viewTitle)
                fieldsCreated.add(viewField)
                viewBinding.parentLayout.addView(viewTitle)
                viewBinding.parentLayout.addView(viewField)
            }
        }


//        viewModel.years.observe(viewLifecycleOwner) { years ->
//            val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)
//            viewBinding.yearBuilt.setAdapter(adapter)
//        }
//
//        viewBinding.yearBuilt.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//            yearBuilt = viewBinding.yearBuilt.adapter.getItem(position).toString()
//            viewModel.calculateFirstBlock(yearBuilt, artesianWellType)
//        }
//
//        val artesianWellTypes = listOf<String>(
//            getString(R.string.cacimba),
//            getString(R.string.escavado),
//            getString(R.string.perfurado)
//        )
//
//        val artesianWellTypeAdapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, artesianWellTypes)
//        viewBinding.artesianWellType.setAdapter(artesianWellTypeAdapter)
//
//        viewBinding.artesianWellType.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
//            artesianWellType = viewBinding.artesianWellType.adapter.getItem(position).toString()
//            viewModel.calculateFirstBlock(yearBuilt, artesianWellType)
//        }

    }
}