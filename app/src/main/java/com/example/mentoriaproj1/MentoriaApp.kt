package com.example.mentoriaproj1

import android.app.Application
import com.example.mentoriaproj1.di.Di
import com.example.mentoriaproj1.ui.characters.CharactersViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider

class MentoriaApp: Application(), DIAware {
    override val di: DI = Di.di
}