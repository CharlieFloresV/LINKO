package com.example.appconsultpokemon.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appconsultpokemon.data.model.repository.remote.PokemonRepositoryImpl
import com.example.appconsultpokemon.R
import com.example.appconsultpokemon.data.model.Resource
import com.example.appconsultpokemon.RetrofitClient
import com.example.appconsultpokemon.adapter.PokeInfoAdapter
import com.example.appconsultpokemon.data.model.Pokemon
import com.example.appconsultpokemon.data.model.request.PokemonDataSource
import com.example.appconsultpokemon.databinding.FragmentPokeInfoBinding
import com.example.appconsultpokemon.viewmodel.PokemonViewModel
import com.example.appconsultpokemon.viewmodel.PokemonViewModelFactory

class PokeInfoFragment : Fragment(R.layout.fragment_poke_info),
    PokeInfoAdapter.OnPokemonClickListener {

    private lateinit var binding: FragmentPokeInfoBinding
    private val viewModel by viewModels<PokemonViewModel> {
        PokemonViewModelFactory(
            PokemonRepositoryImpl(
                PokemonDataSource(RetrofitClient.webservice)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokeInfoBinding.bind(view)

        viewModel.fetchPokemons().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData, ", "Loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data}")
                    binding.progressBar.visibility = View.GONE
                    binding.rvPokeList.layoutManager = LinearLayoutManager(this.context)
                    binding.rvPokeList.adapter =
                        PokeInfoAdapter(result.data.results, this@PokeInfoFragment)
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    override fun onPokemonClick(pokemon: Pokemon) {
        Log.d("LiveData", "onPokemonClick: $pokemon")
        val action =
            PokeInfoFragmentDirections.actionPokeInfoToSpeciesInformationFragment(pokemon.name)

        findNavController().navigate(action)
    }

}