package com.example.mentoriaproj1.di

import com.example.mentoriaproj1.data.CharactersGateway
import com.example.mentoriaproj1.data.CharactersInfrastructure
import com.example.mentoriaproj1.data.utils.BuildRetrofit
import com.example.mentoriaproj1.domain.services.RickAndMortyService
import com.example.mentoriaproj1.ui.characters.CharactersViewModel
import com.example.mentoriaproj1.ui.places.PlacesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.*

object Di: DIAware {
    override val di: DI = DI {
        bindSingleton<HttpLoggingInterceptor> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        bindSingleton<OkHttpClient> {
            val logInterceptor = instance<HttpLoggingInterceptor>()
            OkHttpClient().newBuilder().addInterceptor(logInterceptor).build()
        }

        bindSingleton<CharactersGateway> {
            val httpClient = instance<OkHttpClient>()
            BuildRetrofit(apiURL = "https://rickandmortyapi.com/", httpClient)
                .create(CharactersGateway::class.java)
        }

        bindProvider<RickAndMortyService> {
           CharactersInfrastructure(api = instance())
        }

        bindProvider<CharactersViewModel> {
            CharactersViewModel(charactersService = instance())
        }

        bindProvider<PlacesViewModel> {
            PlacesViewModel(charactersService = instance())
        }
    }
}