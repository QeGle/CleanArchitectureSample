package com.qegle.cleanarchsample.domain.usecase

import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.commons.utils.enums.ResultType
import com.qegle.cleanarchsample.domain.MealsByBeersRepository
import com.qegle.cleanarchsample.domain.model.BeerModel

class GetBeersUseCase(private val mealsByBeersRepository: MealsByBeersRepository) {
	
	suspend fun execute(): Result<List<BeerModel>> {
		val unSortedBeers: Result<List<BeerModel>> = mealsByBeersRepository.getAllBeers()
		var sortedBeers: Result<List<BeerModel>> = unSortedBeers
		
		val isResultSuccess = unSortedBeers.resultType == ResultType.SUCCESS
		
		if (isResultSuccess) {
			val beersModel = getSortedAscendingBeers(unSortedBeers)
			sortedBeers = Result.success(beersModel)
		}
		
		return sortedBeers
	}
	
	private fun getSortedAscendingBeers(beers: Result<List<BeerModel>>): List<BeerModel>? {
		return beers.data?.sortedBy { it.abv }
	}
}