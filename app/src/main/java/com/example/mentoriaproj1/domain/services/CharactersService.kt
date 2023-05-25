package com.example.mentoriaproj1.domain.services

import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse

interface CharactersService {
    suspend fun retrieveCharacters(): List<CharacterResponse>
    suspend fun retrieveLocations(): List<LocationResponse>
}