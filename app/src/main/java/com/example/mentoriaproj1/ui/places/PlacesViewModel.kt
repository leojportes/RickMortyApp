package com.example.mentoriaproj1.ui.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoriaproj1.data.CharactersInfrastructure
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.domain.services.CharactersService
import com.example.mentoriaproj1.ui.characters.CharacterViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class PlacesViewModel: ViewModel() {
    private val charactersService: CharactersService = CharactersInfrastructure()
    private val _locationsViewState = MutableStateFlow<LocationViewState>(LocationViewState())

    val locationsViewState = _locationsViewState.asStateFlow()

    fun retrieveCharacters() {
        viewModelScope.launch {
            _locationsViewState.emit(LocationViewState(isLoading = true))

            kotlin.runCatching {
                charactersService.retrieveLocations()
            }.fold(
                onSuccess = { locations ->
                    _locationsViewState.emit(LocationViewState(
                        isLoading = false,
                        locations = locations,
                        error = null
                    ))
                },
                onFailure = { error ->
                    _locationsViewState.emit(LocationViewState(
                        isLoading = false,
                        locations = emptyList(),
                        error = error
                    ))
                }
            )
        }
    }

}

internal data class LocationViewState(
    val isLoading: Boolean = true,
    val locations: List<LocationResponse> = emptyList(),
    val error: Throwable? = null
)