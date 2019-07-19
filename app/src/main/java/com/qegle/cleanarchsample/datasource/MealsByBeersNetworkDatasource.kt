package com.qegle.cleanarchsample.datasource

import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.datasource.model.BeerResponse
import com.qegle.cleanarchsample.datasource.retrofit.BeersApiService
import com.qegle.cleanarchsample.datasource.retrofit.RetrofitConfiguration
import com.qegle.cleanarchsample.repository.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsByBeersNetworkDatasource(private val retrofitConfiguration: RetrofitConfiguration) {
	
	suspend fun getAllBeers(page: String): Result<List<BeerResponse>>? {
		var result: Result<List<BeerResponse>>? = Result.success(listOf())
		
		withContext(Dispatchers.IO) {
			try {
				val retrofitInstance = retrofitConfiguration.getRetrofitInstance()
				
				val beersService = retrofitInstance.create(BeersApiService::class.java)
				val request = beersService.getAllBeersAsync(
						page,
						Constants.MAX_RESULTS_PER_PAGE.toString()
				)
				
				val response = request?.await()
				
				request?.let {
					if (it.isCompleted) result = Result.success(response)
					else if (it.isCancelled) result =
							Result.error(Constants.NETWORK_DATASOURCE_ERROR_MESSAGE)
				}
			} catch (ex: Exception) {
				result = Result.error(Constants.NETWORK_DATASOURCE_ERROR_MESSAGE)
			}
		}
		
		return result
	}
}
