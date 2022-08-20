package com.br.faeterj.paracambi.sarcaspd.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.util.LocationProvider
import java.util.*


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
open class BaseFragment<V: ViewBinding>(private val inflate: Inflate<V>) : Fragment() {

    private lateinit var _binding : V
    val binding get() = _binding
    private lateinit var requestMultiplePermissions: ActivityResultLauncher<Array<String>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permission ->
            permission.forEach { actionMap ->
                when(actionMap.key){
                    Manifest.permission.ACCESS_COARSE_LOCATION -> checkPermission(actionMap.value)
                    Manifest.permission.ACCESS_FINE_LOCATION -> checkPermission(actionMap.value)
                }
            }
        }
        if(!hasPermissions(requireContext(),PERMISSIONS)){
            requestMultiplePermissions.launch(PERMISSIONS)
        }

    }

     fun checkPermission(isGranted: Boolean){
        if(isGranted){
            getLocation()
        }else{
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle(getString(R.string.permission_dialog_title))
            alertDialog.setMessage(getString(R.string.permission_dialog_description))
            alertDialog.setPositiveButton(getString(R.string.permission_dialog_positive_button)){ _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", requireContext().packageName , null)
                intent.data = uri
                startActivity(intent)
            }
            alertDialog.setIcon(R.drawable.ic_baseline_crisis_alert_24)
            alertDialog.show()
        }
    }

    fun hasPermissions(context: Context, permissions: Array<String> = PERMISSIONS): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getLocation(){
        val locationProvider = LocationProvider(requireContext())
        val latitude = locationProvider.getLatitude()
        val longitude = locationProvider.getLongitude()

        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude,longitude,1)
        val address = addresses[0].getAddressLine(0)
    }
    fun setupToolbar(
        title : String = "",
        navigationBack : Boolean = true,
        visibility : Boolean = true
    ){
        (requireActivity() as MainActivity).setupToolbar(
            title = title,
            navigationBack = navigationBack,
            visibility = visibility
        )
    }

    fun getToolbar() : Toolbar = (requireActivity() as MainActivity).getToolbar()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater,container,false)
        return binding.root
    }

    companion object{
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
}