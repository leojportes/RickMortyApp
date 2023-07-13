package com.example.mentoriaproj1.data

import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.domain.services.RickAndMortyService

class CharactersInfrastructure(val api: CharactersGateway): RickAndMortyService {


    // Get All characters
    override suspend fun retrieveCharacters(): List<CharacterResponse> {
        return api.getCharacters().characters
    }

    // Get All locations
    override suspend fun retrieveLocations(): List<LocationResponse> {
        return api.getLocations().locations
    }
}
