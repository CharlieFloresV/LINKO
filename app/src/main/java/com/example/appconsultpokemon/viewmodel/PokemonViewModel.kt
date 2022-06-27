package com.example.appconsultpokemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.appconsultpokemon.data.model.repository.remote.PokemonRepository
import com.example.appconsultpokemon.data.model.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class PokemonViewModel(private val repo: PokemonRepository): ViewModel() {

    fun fetchPokemons() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPokemonList()))
        } catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchSpecieInformation(name: String) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getSpecieInformation(name)))
        } catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class PokemonViewModelFactory(private val repo: PokemonRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PokemonRepository::class.java).newInstance(repo)
    }
}