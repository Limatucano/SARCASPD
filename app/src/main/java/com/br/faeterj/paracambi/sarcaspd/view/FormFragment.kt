package com.br.faeterj.paracambi.sarcaspd.view

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModelFactory
import android.widget.ArrayAdapter

class FormFragment : Fragment() {

    private val TAG = "FormFragment"
    private lateinit var viewBinding : FragmentFormBinding
    private lateinit var viewModel : FormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFormBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, FormViewModelFactory()).get(FormViewModel::class.java)
        viewModel.generateYears()

        viewModel.years.observe(viewLifecycleOwner, { years ->
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)
            viewBinding.yearBuilt.setAdapter(adapter)
        })

        viewBinding.yearBuilt.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedYear = viewBinding.yearBuilt.adapter.getItem(position).toString()
            Log.d(TAG, selectedYear)
        }
    }
}