package com.qegle.cleanarchsample.domain

import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.domain.model.BeerModel

interface MealsByBeersRepository {
	
	suspend fun getAllBeers(): Result<List<BeerModel>>
}