package com.example.challenge.presentation.di

import com.example.challenge.presentation.viewmodel.VideoPlayerViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val myModule = module {
    factory {
        VideoPlayerViewModel(repository = get())
    }
}
