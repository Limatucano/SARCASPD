package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.faeterj.paracambi.sarcaspd.data.model.FinalResult
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private val TAG = "ResultFragment"
    private var result : FinalResult? = null
    private lateinit var viewBinding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e(TAG, "Erro ao transferir dados")
            return
        }
        val args = ResultFragmentArgs.fromBundle(bundle)
        result = args.result
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       viewBinding = FragmentResultBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        result?.let {
            viewBinding.riskValue.text = it.risk
            viewBinding.actionValue.text = it.action
            viewBinding.riskTotalValue.text = it.total.toString()
        }
        viewBinding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}