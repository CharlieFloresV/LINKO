package com.example.appconsultpokemon

import com.example.appconsultpokemon.data.AppConstants
import com.example.appconsultpokemon.data.model.PokemonList
import com.example.appconsultpokemon.data.model.response.SpeciesInfo
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokemonList

    @GET("pokemon-species/{name}/")
    suspend fun getSpecieInformation(@Path("name") name: String): SpeciesInfo
}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}