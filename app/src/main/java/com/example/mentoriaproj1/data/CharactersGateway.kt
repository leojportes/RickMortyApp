package com.example.mentoriaproj1.data

import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.CharactersResponse
import com.example.mentoriaproj1.domain.models.LocationsResponse
import retrofit2.http.GET

interface CharactersGateway {
    @GET("api/character")
    suspend fun getCharacters(): CharactersResponse

    @GET("api/location")
    suspend fun getLocations(): LocationsResponse
}
