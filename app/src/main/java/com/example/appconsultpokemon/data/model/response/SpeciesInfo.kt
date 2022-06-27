package com.example.appconsultpokemon.data.model.response

data class SpeciesInfo(
    val base_happiness: Int = -1,
    val capture_rate: Int = -1,
    val color: Basic,
    val egg_groups: List<Basic> = listOf(),
    val evolution_chain: Url
)

data class Basic (
    val name: String = "",
    val url: String = ""
)

data class Url(
    val url: String = ""
)