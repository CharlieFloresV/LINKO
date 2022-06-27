package com.example.appconsultpokemon.data.model.repository.remote

import com.example.appconsultpokemon.data.model.PokemonList
import com.example.appconsultpokemon.data.model.response.SpeciesInfo

interface PokemonRepository {
    suspend fun getPokemonList(): PokemonList
    suspend fun getSpecieInformation(name: String = ""): SpeciesInfo
}