package com.qegle.cleanarchsample.di

import com.qegle.cleanarchsample.datasource.MealsByBeersNetworkDatasource
import com.qegle.cleanarchsample.datasource.retrofit.RetrofitConfiguration
import com.qegle.cleanarchsample.domain.MealsByBeersRepository
import com.qegle.cleanarchsample.domain.usecase.GetBeersUseCase
import com.qegle.cleanarchsample.repository.MealsByBeersRepositoryImpl
import com.qegle.cleanarchsample.vm.MealsByBeersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
	factory { RetrofitConfiguration() }
	factory { MealsByBeersNetworkDatasource(get()) }
	factory<MealsByBeersRepository> { MealsByBeersRepositoryImpl(get()) }
	factory { GetBeersUseCase(get()) }
	viewModel { MealsByBeersViewModel(get()) }
}