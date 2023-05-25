package com.example.mentoriaproj1.domain.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CharactersResponse(
    @SerialName("results") val characters: List<CharacterResponse>
)

@kotlinx.serialization.Serializable
data class CharacterResponse(
    val name: String,
    val status: String,
    val species: String,
    val origin: OriginResponse,
    val image: String,
): java.io.Serializable

@kotlinx.serialization.Serializable
data class OriginResponse(
    val name: String,
    val url: String
): java.io.Serializable


@kotlinx.serialization.Serializable
data class LocationsResponse(
    @SerialName("results") val locations: List<LocationResponse>
)

@kotlinx.serialization.Serializable
data class LocationResponse(
    val name: String,
    val type: String,
    val dimension: String
)
