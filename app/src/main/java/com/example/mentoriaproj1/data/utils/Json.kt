package com.example.mentoriaproj1.data.utils

import kotlinx.serialization.json.Json

object Json {
    val parser by lazy {
        Json {
            allowSpecialFloatingPointValues = true
            coerceInputValues = true
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }
    }
}