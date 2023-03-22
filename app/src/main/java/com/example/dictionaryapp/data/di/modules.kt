package com.example.dictionaryapp.data.di

import com.example.dictionaryapp.data.api.SkyengApiProvider
import com.example.dictionaryapp.data.mapper.SearchResponseToEntityMapper
import com.example.dictionaryapp.data.repository.SkyengRepository
import com.example.dictionaryapp.data.repository.SkyengRepositoryImpl
import com.example.dictionaryapp.presentation.mapper.MeaningUiModelMapper
import com.example.dictionaryapp.presentation.viewModel.MainViewModel
import com.example.dictionaryapp.presentation.viewModel.MainViewModelContract
import org.koin.dsl.module

val appModule = module {
    single { SkyengApiProvider.provide() }

    factory { SearchResponseToEntityMapper() }
    factory { MeaningUiModelMapper() }
    factory<SkyengRepository> { SkyengRepositoryImpl(get(), get()) }

    //factory { MainPresenter(get(), get()) }
    factory<MainViewModelContract.ViewModel> { MainViewModel(get(), get()) }
}