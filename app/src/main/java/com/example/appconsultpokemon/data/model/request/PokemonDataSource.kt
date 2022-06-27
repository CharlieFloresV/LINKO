package com.example.appconsultpokemon.data.model.request

import com.example.appconsultpokemon.WebService
import com.example.appconsultpokemon.data.model.PokemonList
import com.example.appconsultpokemon.data.model.response.SpeciesInfo

class PokemonDataSource(private val webService: WebService) {

    suspend fun getPokemonList() : PokemonList = webService.getPokemonList(151)
    suspend fun getSpecieInformation(name:String): SpeciesInfo = webService.getSpecieInformation(name)
}