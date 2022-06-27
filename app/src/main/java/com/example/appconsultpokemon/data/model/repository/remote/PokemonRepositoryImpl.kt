package com.example.appconsultpokemon.data.model.repository.remote

import com.example.appconsultpokemon.data.model.PokemonList
import com.example.appconsultpokemon.data.model.repository.remote.PokemonRepository
import com.example.appconsultpokemon.data.model.request.PokemonDataSource
import com.example.appconsultpokemon.data.model.response.SpeciesInfo

class PokemonRepositoryImpl(private val dataSource: PokemonDataSource) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonList = dataSource.getPokemonList()
    override suspend fun getSpecieInformation(name: String): SpeciesInfo = dataSource.getSpecieInformation(name)

}