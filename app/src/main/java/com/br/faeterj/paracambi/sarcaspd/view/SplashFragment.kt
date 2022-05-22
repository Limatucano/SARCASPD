package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentSplashBinding
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModelFactory

class SplashFragment : Fragment() {
    private lateinit var viewBinding: FragmentSplashBinding
    private lateinit var viewModel: FormViewModel
    private lateinit var direction: NavDirections
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentSplashBinding.inflate(inflater, container, false)
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, FormViewModelFactory(FirstBlock(requireContext()))).get(
            FormViewModel::class.java
        )


        viewModel.getFields(requireContext())
        viewModel.fields.observe(viewLifecycleOwner) {
            direction = SplashFragmentDirections.actionSplashFragmentToFormFragment(it)
            view.findNavController().navigate(direction)
        }
    }

}