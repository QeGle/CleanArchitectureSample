package com.qegle.cleanarchsample.repository

import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.datasource.MealsByBeersNetworkDatasource
import com.qegle.cleanarchsample.domain.MealsByBeersRepository
import com.qegle.cleanarchsample.domain.model.BeerModel
import com.qegle.cleanarchsample.repository.constants.Constants
import com.qegle.cleanarchsample.repository.mapper.toBeerModelListResult

class MealsByBeersRepositoryImpl(private val mealsByBeersNetworkDatasource: MealsByBeersNetworkDatasource) :
		MealsByBeersRepository {
	
	private val beers = mutableListOf<BeerModel>()
	
	override suspend fun getAllBeers(): Result<List<BeerModel>> {
		var page = -1
		
		do {
			page = getPageToCheckBeers(page)
			
			mealsByBeersNetworkDatasource.getAllBeers(page.toString())
					?.toBeerModelListResult()
					?.data
					?.forEach { beerModel -> beers.add(beerModel) }
			
		} while (page != -1)
		
		return Result.success(beers)
	}
	
	private fun getPageToCheckBeers(currentPage: Int): Int {
		var page: Int = currentPage
		
		if (hasBeers()) {
			if (isNecessaryFetchMoreBeers(currentPage)) page++ else page = -1
		} else {
			page = 1
		}
		
		return page
	}
	
	private fun hasBeers() = beers.size > 0
	
	private fun isNecessaryFetchMoreBeers(page: Int) =
			(beers.size / page) == Constants.MAX_RESULTS_PER_PAGE
}
