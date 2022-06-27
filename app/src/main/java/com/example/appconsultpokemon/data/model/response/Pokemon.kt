package com.example.appconsultpokemon.data.model

data class Pokemon(
    val name: String = "",
    val url: String = ""
)

data class PokemonList(val results: List<Pokemon> = listOf())