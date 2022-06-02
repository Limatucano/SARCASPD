package com.br.faeterj.paracambi.sarcaspd.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.fields.FieldsType
import com.br.faeterj.paracambi.sarcaspd.data.fields.SelectField
import com.br.faeterj.paracambi.sarcaspd.data.fields.TextField
import com.br.faeterj.paracambi.sarcaspd.data.model.Field
import com.br.faeterj.paracambi.sarcaspd.data.model.Form
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentFormBinding
import com.br.faeterj.paracambi.sarcaspd.viewModel.FormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.kotlinFunction

@AndroidEntryPoint
class FormFragment : Fragment() {

    private val TAG = "FormFragment"
    private lateinit var viewBinding: FragmentFormBinding
    private val viewModel: FormViewModel by viewModels()
    private lateinit var form: Form
    private var adapter: ArrayAdapter<*>? = null
    private val fieldsCreated: MutableList<View> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e(TAG, "Erro ao transferir dados")
            return
        }
        val args = FormFragmentArgs.fromBundle(bundle)
        form = args.form
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFormBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        scrollingList(form.block)

    }

    private fun scrollingList(fields: List<Field>){

        for (field in fields) {

            if(field.category != null && !field.fields.isNullOrEmpty()){
                val categoryTitle = TextView(requireContext())
                categoryTitle.text = field.category
                viewBinding.parentLayout.addView(categoryTitle)
                scrollingList(field.fields)
                continue
            }
            buildFields(field)
        }

    }

    private fun buildFields(field : Field){
        val viewTitle = TextView(requireContext())
        lateinit var viewField: View
        viewTitle.text = field.title
        field.method?.let { method ->
            getFunctionByName(method)
        }

        when (field.fieldType) {
            FieldsType.SELECT.toString() -> {
                viewField = SelectField(requireContext(), adapter, field).getField()
            }
            FieldsType.TEXT.toString() -> {
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

    private fun getFunctionByName(name: String) {
        val selfRef = ::getFunctionByName
        val currentClass = selfRef.javaMethod?.declaringClass
        val classFunction = currentClass?.classLoader?.loadClass(this.javaClass.name)
        val method = classFunction?.methods?.find { it.name == name }
        method?.kotlinFunction?.call(this)
    }

    fun buildYearField() {
        val years = viewModel.generateYears()
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, years)

    }

    fun buildTypeField() {
        val artesianWellTypes = listOf<String>(
            getString(R.string.cacimba),
            getString(R.string.escavado),
            getString(R.string.perfurado)
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, artesianWellTypes)
    }

    fun buildNearArtesianWellField(){
        val position = listOf(
            getString(R.string.nao_possui),
            getString(R.string.acima_boca),
            getString(R.string.mesmo_nivel),
            getString(R.string.abaixo_boca)
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, position)
    }

    fun buildTopInclinationField(){
        val inclinations = listOf(
            getString(R.string.inclinada),
            getString(R.string.pouco_inclinada),
            getString(R.string.plana),
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, inclinations)
    }

    fun buildPollutingSourceField(){
        val inclinations = listOf(
            getString(R.string.fonte_um),
            getString(R.string.fonte_dois),
            getString(R.string.fonte_tres),
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, inclinations)
    }

    fun buildSoilField(){
        val inclinations = listOf(
            getString(R.string.argila),
            getString(R.string.silte),
            getString(R.string.areia),
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, inclinations)
    }

    fun buildDepthField(){
        val inclinations = listOf(
            getString(R.string.profundidade_um),
            getString(R.string.profundidade_dois),
            getString(R.string.profundidade_tres),
        )
        adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, inclinations)
    }
}