package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentSplashBinding
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: FormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(
            visibility = false
        )
        binding.progressBar.visibility = View.VISIBLE
        binding.loadingMessage.text = getString(R.string.loading_message)

        viewModel.getFields()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.error.observe(viewLifecycleOwner){
            binding.progressBar.visibility = View.GONE
            binding.loadingMessage.text = it.message
        }
        viewModel.fields.observe(viewLifecycleOwner) {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToFormFragment(it))
        }
    }

}