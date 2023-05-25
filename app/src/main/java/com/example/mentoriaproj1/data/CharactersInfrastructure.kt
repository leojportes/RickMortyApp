package com.example.mentoriaproj1.data

import com.example.mentoriaproj1.data.utils.BuildRetrofit
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.domain.services.CharactersService
import kotlinx.serialization.SerialName
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET

class CharactersInfrastructure: CharactersService {
    val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val httpClient = OkHttpClient().newBuilder().addInterceptor(logInterceptor).build()
    val api = BuildRetrofit(apiURL = "https://rickandmortyapi.com/", httpClient)
        .create(CharactersGateway::class.java)

    // Get All characters
    override suspend fun retrieveCharacters(): List<CharacterResponse> {
        return api.getCharacters().characters
    }

    // Get All locations
    override suspend fun retrieveLocations(): List<LocationResponse> {
        return api.getLocations().locations
    }
}
