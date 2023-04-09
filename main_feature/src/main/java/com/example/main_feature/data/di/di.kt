package com.example.main_feature.data.di

import com.example.main_feature.data.api.SkyengApiProvider
import com.example.main_feature.data.mapper.SearchResponseToEntityMapper
import com.example.main_feature.data.repository.SkyengRepositoryImpl
import com.example.main_feature.domain.repository.SkyengRepository
import com.example.main_feature.presentation.mapper.MeaningUiModelMapper
import com.example.main_feature.presentation.viewModel.MainViewModel
import com.example.main_feature.presentation.viewModel.MainViewModelContract
import org.koin.dsl.module

val mainModule = module {
    single { SkyengApiProvider.provide() }

    factory { SearchResponseToEntityMapper() }
    factory { MeaningUiModelMapper() }
    factory<SkyengRepository> { SkyengRepositoryImpl(api = get(), mapper = get()) }

    factory<MainViewModelContract.ViewModel> {
        MainViewModel(
            repo = get(),
            mapper = get(),
            historyRepository = get()
        )
    }
}