package com.br.faeterj.paracambi.sarcaspd.view.result

import android.os.Bundle
import android.view.View
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.FinalResult
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentResultBinding
import com.br.faeterj.paracambi.sarcaspd.view.BaseFragment


class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private var result : FinalResult? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments ?: return
        val args = ResultFragmentArgs.fromBundle(bundle)
        result = args.result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        setupViews()
        setupToolbar(
            visibility = true,
            navigationBack = true,
            title = getString(R.string.result_title)
        )
    }

    private fun setupViews() = binding.run{
        result?.let {
            riskValue.text = it.risk
            actionValue.text = it.action
            riskTotalValue.text = it.total.toString()
        }
    }

}