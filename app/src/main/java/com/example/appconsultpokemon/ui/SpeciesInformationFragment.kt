package com.example.appconsultpokemon.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.appconsultpokemon.data.model.repository.remote.PokemonRepositoryImpl
import com.example.appconsultpokemon.R
import com.example.appconsultpokemon.data.model.Resource
import com.example.appconsultpokemon.RetrofitClient
import com.example.appconsultpokemon.data.model.request.PokemonDataSource
import com.example.appconsultpokemon.databinding.FragmentSpeciesInformationBinding
import com.example.appconsultpokemon.viewmodel.PokemonViewModel
import com.example.appconsultpokemon.viewmodel.PokemonViewModelFactory
import java.util.*

class SpeciesInformationFragment : Fragment(R.layout.fragment_species_information) {
    private lateinit var binding: FragmentSpeciesInformationBinding
    private val args by navArgs<SpeciesInformationFragmentArgs>()
    private val viewModel by viewModels<PokemonViewModel> {
        PokemonViewModelFactory(
            PokemonRepositoryImpl(
                PokemonDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSpeciesInformationBinding.bind(view)

        viewModel.fetchSpecieInformation(args.pokeName)
            .observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resource.Loading -> {
                        Log.d("LiveData, ", "Loading...")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        Log.d("LiveData", "${result.data}")
                        binding.progressBar.visibility = View.GONE
                        binding.tvTitleName.text = args.pokeName.uppercase(Locale.getDefault())
                        binding.tvbaseHappiness.text = "Felicidad base: ${result.data.base_happiness}"
                        binding.tvCaptureRate.text = "Tasa de Captura: ${result.data.capture_rate}"
                        binding.tvColor.text = "Color: : ${result.data.color.name}"

                        var eggGroups = ""
                        for (i in result.data.egg_groups){
                            eggGroups += i.name + ", "
                        }

                        binding.tvEggGroups.text = "Grupos de huevos: ${eggGroups}"
                    }
                    is Resource.Failure -> {
                        Log.d("LiveData", "${result.exception}")
                        binding.progressBar.visibility = View.GONE
                    }
                }
            })
    }
}
