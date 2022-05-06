package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private lateinit var viewBinding : FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFormBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
}