package com.br.faeterj.paracambi.sarcaspd.view

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.databinding.ActivityMainBinding
import com.br.faeterj.paracambi.sarcaspd.databinding.CustomToolbarBinding
import com.br.faeterj.paracambi.sarcaspd.util.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val customToolbar: CustomToolbarBinding by lazy {
        CustomToolbarBinding.bind(findViewById(R.id.toolbarLayout))
    }

    fun setupToolbar(
        title : String = "",
        navigationBack : Boolean = false,
        visibility : Boolean = true
    ) = customToolbar.run{

        toolbarLayout.setVisible(visibility)
        toolbar.title = title
        backIv.setVisible(navigationBack)
        backIv.setOnClickListener {
            onBackPressed()
        }
    }

    fun getToolbar() : Toolbar = customToolbar.toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}