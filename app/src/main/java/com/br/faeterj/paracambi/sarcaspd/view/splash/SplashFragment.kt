package com.br.faeterj.paracambi.sarcaspd.view.splash

import android.Manifest
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.br.faeterj.paracambi.sarcaspd.R
import com.br.faeterj.paracambi.sarcaspd.data.model.Address
import com.br.faeterj.paracambi.sarcaspd.databinding.FragmentSplashBinding
import com.br.faeterj.paracambi.sarcaspd.util.LocationProvider
import com.br.faeterj.paracambi.sarcaspd.view.BaseFragment
import com.br.faeterj.paracambi.sarcaspd.view.form.FormViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: FormViewModel by viewModels()
    private lateinit var requestMultiplePermissions: ActivityResultLauncher<Array<String>>
    private lateinit var geocoder: Geocoder
    //private lateinit var address: Address
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(
            visibility = false
        )
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        //address = getLocation()
        requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ permission ->
            permission.forEach { actionMap ->
                when(actionMap.key){
                    Manifest.permission.ACCESS_COARSE_LOCATION -> checkPermission(actionMap.value)
                    Manifest.permission.ACCESS_FINE_LOCATION -> checkPermission(actionMap.value)
                }
            }
        }
        if(!hasPermissions(requireContext(), PERMISSIONS)){
            requestMultiplePermissions.launch(PERMISSIONS)
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.loadingMessage.text = getString(R.string.loading_message)

        viewModel.getFields()
        setupObservers()
    }


    //TODO: Passar a captura da localização para a viewModel
//    private fun getLocation(): Address {
//        val locationProvider = LocationProvider(requireContext())
//        val latitude = locationProvider.getLatitude()
//        val longitude = locationProvider.getLongitude()
//
//        val addresses = geocoder.getFromLocation(latitude,longitude,1)
//        addresses[0].apply {
//            return Address(
//                address = getAddressLine(0),
//                street = thoroughfare,
//                city = subAdminArea,
//                number = subThoroughfare,
//                state = adminArea,
//                country = countryName,
//                postalCode = postalCode,
//                knownName = featureName
//            )
//        }
//    }

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