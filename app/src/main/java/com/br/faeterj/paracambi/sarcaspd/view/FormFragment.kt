package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import com.br.faeterj.paracambi.sarcaspd.util.Json.getJsonFromAssets
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModelFactory
import com.google.gson.Gson

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

        viewModel = ViewModelProvider(this, FormViewModelFactory(FirstBlock(requireContext()))).get(FormViewModel::class.java)
        viewModel.generateYears()

        viewModel.years.observe(viewLifecycleOwner) { years ->
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)
            viewBinding.yearBuilt.setAdapter(adapter)
        }

        viewBinding.yearBuilt.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            yearBuilt = viewBinding.yearBuilt.adapter.getItem(position).toString()
            viewModel.calculateFirstBlock(yearBuilt, artesianWellType)
        }

        val artesianWellTypes = listOf<String>(
            getString(R.string.cacimba),
            getString(R.string.escavado),
            getString(R.string.perfurado)
        )

        val artesianWellTypeAdapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, artesianWellTypes)
        viewBinding.artesianWellType.setAdapter(artesianWellTypeAdapter)

        viewBinding.artesianWellType.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            artesianWellType = viewBinding.artesianWellType.adapter.getItem(position).toString()
            viewModel.calculateFirstBlock(yearBuilt, artesianWellType)
        }

    }
}