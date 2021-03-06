package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentSplashBinding
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: FormViewModel by viewModels()
    private lateinit var viewBinding : FragmentSplashBinding
    private lateinit var direction: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.progressBar.visibility = View.VISIBLE
        viewBinding.loadingMessage.text = getString(R.string.loading_message)

        viewModel.getFields()
        viewModel.fields.observe(viewLifecycleOwner) {
            direction = SplashFragmentDirections.actionSplashFragmentToFormFragment(it)
            view.findNavController().navigate(direction)
        }

        viewModel.error.observe(viewLifecycleOwner){
            viewBinding.progressBar.visibility = View.GONE
            viewBinding.loadingMessage.text = it.message
        }
    }

}