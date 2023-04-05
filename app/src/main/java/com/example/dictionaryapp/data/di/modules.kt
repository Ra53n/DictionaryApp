package com.example.dictionaryapp.data.di

import androidx.room.Room
import com.example.dictionaryapp.data.api.SkyengApiProvider
import com.example.dictionaryapp.data.mapper.SearchResponseToEntityMapper
import com.example.dictionaryapp.data.repository.HistoryRepository
import com.example.dictionaryapp.data.repository.HistoryRepositoryImpl
import com.example.dictionaryapp.data.repository.SkyengRepository
import com.example.dictionaryapp.data.repository.SkyengRepositoryImpl
import com.example.dictionaryapp.data.room.HistoryDatabase
import com.example.dictionaryapp.presentation.mapper.HistoryUiModelMapper
import com.example.dictionaryapp.presentation.mapper.MeaningUiModelMapper
import com.example.dictionaryapp.presentation.viewModel.HistoryViewModel
import com.example.dictionaryapp.presentation.viewModel.HistoryViewModelContract
import com.example.dictionaryapp.presentation.viewModel.MainViewModel
import com.example.dictionaryapp.presentation.viewModel.MainViewModelContract
import org.koin.dsl.module

val appModule = module {
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

    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }

    single { get<HistoryDatabase>().historyDao() }

    factory<HistoryRepository> { HistoryRepositoryImpl(historyDao = get()) }

    factory { HistoryUiModelMapper() }

    factory<HistoryViewModelContract.ViewModel> {
        HistoryViewModel(
            historyRepository = get(),
            mapper = get()
        )
    }

}