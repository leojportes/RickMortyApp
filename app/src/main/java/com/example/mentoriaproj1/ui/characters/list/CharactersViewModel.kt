package com.example.mentoriaproj1.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mentoriaproj1.data.CharactersInfrastructure
import com.example.mentoriaproj1.domain.models.CharacterResponse
import com.example.mentoriaproj1.domain.services.CharactersService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CharactersViewModel: ViewModel() {
    private val charactersService: CharactersService = CharactersInfrastructure()
    private val _charactersViewState = MutableStateFlow<CharacterViewState>(CharacterViewState())

    val charactersViewState = _charactersViewState.asStateFlow()

    fun retrieveCharacters() {
        viewModelScope.launch {
            _charactersViewState.emit(CharacterViewState(isLoading = true))

            kotlin.runCatching {
                charactersService.retrieveCharacters()
            }.fold(
                onSuccess = { characters ->
                    _charactersViewState.emit(CharacterViewState(
                        isLoading = false,
                        characters = characters,
                        error = null
                    ))
                },
                onFailure = { error ->
                    _charactersViewState.emit(CharacterViewState(
                        isLoading = false,
                        characters = emptyList(),
                        error = error
                    ))
                }
            )
        }
    }

    suspend fun retrieveCharacters2(): List<CharacterResponse> {
        return charactersService.retrieveCharacters()
    }
}

internal data class CharacterViewState(
    val isLoading: Boolean = true,
    val characters: List<CharacterResponse> = emptyList(),
    val error: Throwable? = null
)