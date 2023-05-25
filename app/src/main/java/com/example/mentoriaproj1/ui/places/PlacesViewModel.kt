package com.example.mentoriaproj1.ui.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoriaproj1.data.CharactersInfrastructure
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.domain.services.CharactersService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlacesViewModel: ViewModel() {
    private val charactersService: CharactersService = CharactersInfrastructure()
    private val _locations = MutableStateFlow<List<LocationResponse>>(emptyList())

    val locations = _locations.asStateFlow()

    fun retrieveCharacters() {
        viewModelScope.launch {
            val locations = charactersService.retrieveLocations()
            _locations.emit(locations)
        }
    }

}