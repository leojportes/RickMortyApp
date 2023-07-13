package com.example.mentoriaproj1.ui.places

import com.example.mentoriaproj1.domain.models.LocationResponse
import com.example.mentoriaproj1.domain.services.RickAndMortyService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class PlacesViewModel(private val charactersService: RickAndMortyService) {
    private val _locationsViewState = MutableStateFlow<LocationViewState>(LocationViewState())

    val locationsViewState = _locationsViewState.asStateFlow()

    suspend fun retrieveCharacters() {
        _locationsViewState.emit(LocationViewState(isLoading = true))

        kotlin.runCatching {
            charactersService.retrieveLocations()
        }.fold(
            onSuccess = { locations ->
                _locationsViewState.emit(
                    LocationViewState(
                        isLoading = false,
                        locations = locations,
                        error = null
                    )
                )
            },
            onFailure = { error ->
                _locationsViewState.emit(
                    LocationViewState(
                        isLoading = false,
                        locations = emptyList(),
                        error = error
                    )
                )
            }
        )
    }

}

internal data class LocationViewState(
    val isLoading: Boolean = true,
    val locations: List<LocationResponse> = emptyList(),
    val error: Throwable? = null
)