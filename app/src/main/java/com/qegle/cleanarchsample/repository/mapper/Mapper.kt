package com.qegle.cleanarchsample.repository.mapper

import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.commons.utils.enums.ResultType
import com.qegle.cleanarchsample.datasource.model.BeerResponse
import com.qegle.cleanarchsample.domain.model.BeerModel
import com.qegle.cleanarchsample.repository.constants.Constants

fun Result<List<BeerResponse>>.toBeerModelListResult(): Result<List<BeerModel>> {
	if (resultType != ResultType.SUCCESS)
		return Result.error(Constants.NETWORK_DATASOURCE_ERROR_MESSAGE)
	
	return Result.success((data ?: emptyList()).toBeerModelList())
}

fun List<BeerResponse>.toBeerModelList() = this.map { it.toBeerModel() }

fun BeerResponse.toBeerModel() = BeerModel(
		id = id,
		name = name,
		tagline = tagline,
		image = image,
		abv = abv
)