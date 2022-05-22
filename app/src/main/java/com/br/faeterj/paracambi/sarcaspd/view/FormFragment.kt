package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.fields.TextField
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.domain.FirstBlock
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModelFactory
import java.lang.reflect.Modifier
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.kotlinFunction

class FormFragment : Fragment() {

    private val TAG = "FormFragment"
    private lateinit var viewBinding : FragmentFormBinding
    private lateinit var viewModel : FormViewModel

    private var adapter : ArrayAdapter<*>? = null
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

        viewModel = ViewModelProvider(this, FormViewModelFactory(FirstBlock(requireContext()))).get(FormViewModel::class.java)


        viewModel.getFields(requireContext())

        viewModel.fields.observe(viewLifecycleOwner){ fields ->
            for(field in fields.block){

                val viewTitle = TextView(requireContext())
                lateinit var viewField : View
                viewTitle.text = field.title
                field.method?.let { method ->
                    getFunctionByName(method)
                }

                when(field.fieldType){
                    "SELECT" -> {
                        viewField = SelectField(requireContext(), adapter, field).getField()
                    }
                    "TEXT" -> {
                        viewField = TextField(requireContext(), field).getField()
                    }
                }

                if (viewField.parent != null) {
                    (viewField.parent as ViewGroup).removeView(viewField)
                }

                adapter = null
                fieldsCreated.add(viewTitle)
                fieldsCreated.add(viewField)
                viewBinding.parentLayout.addView(viewTitle)
                viewBinding.parentLayout.addView(viewField)
            }
        }
    }

    fun getFunctionByName(name: String){
        val selfRef = ::getFunctionByName
        val currentClass = selfRef.javaMethod?.declaringClass
        val classFunction = currentClass?.classLoader?.loadClass(this.javaClass.name)
        val method = classFunction?.methods?.find { it.name == name }
        method?.kotlinFunction?.call(this)
    }

    fun buildYearField(){
        val years = viewModel.generateYears()
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)

    }

    fun buildTypeField(){
        val artesianWellTypes = listOf<String>(
            getString(R.string.cacimba),
            getString(R.string.escavado),
            getString(R.string.perfurado)
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, artesianWellTypes)
    }
}