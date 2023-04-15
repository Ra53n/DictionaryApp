package com.example.history_feature.data.di

import androidx.room.Room
import com.example.history_feature.data.repository.HistoryRepositoryImpl
import com.example.history_feature.data.room.HistoryDatabase
import com.example.history_feature.domain.repository.HistoryRepository
import com.example.history_feature.presentation.mapper.HistoryUiModelMapper
import com.example.history_feature.presentation.view.HistoryActivity
import com.example.history_feature.presentation.viewModel.HistoryViewModel
import com.example.history_feature.presentation.viewModel.HistoryViewModelContract
import org.koin.dsl.module

val historyModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = HistoryDatabase::class.java,
            name = "HistoryDB"
        ).build()
    }

    single { get<HistoryDatabase>().historyDao() }

    factory<HistoryRepository> { HistoryRepositoryImpl(historyDao = get()) }

    scope<HistoryActivity> {


        factory { HistoryUiModelMapper() }

        factory<HistoryViewModelContract.ViewModel> {
            HistoryViewModel(
                historyInteractor = get(),
                mapper = get()
            )
        }
    }

}