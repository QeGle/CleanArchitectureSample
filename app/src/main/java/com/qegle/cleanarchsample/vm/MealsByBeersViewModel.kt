package com.qegle.cleanarchsample.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qegle.cleanarchsample.commons.utils.dto.Result
import com.qegle.cleanarchsample.commons.utils.enums.ResultType
import com.qegle.cleanarchsample.domain.model.BeerModel
import com.qegle.cleanarchsample.domain.usecase.GetBeersUseCase
import com.qegle.cleanarchsample.vm.mapper.toBeerUIList
import com.qegle.cleanarchsample.vm.model.BeerUI
import kotlinx.coroutines.launch

class MealsByBeersViewModel(private val getMealsByBeersUseCase: GetBeersUseCase) : ViewModel() {
	
	private val statusLiveData: MutableLiveData<ModelStatus> = MutableLiveData()
	
	val status
		get() = statusLiveData
	
	init {
		handleBeersLoad()
	}
	
	private fun handleBeersLoad() {
		viewModelScope.launch {
			isLoadingLiveData()
			updateAppropriateLiveData(getMealsByBeersUseCase.execute())
		}
	}
	
	private fun updateAppropriateLiveData(result: Result<List<BeerModel>>) {
		if (isResultSuccess(result)) {
			onResultSuccess(result.data)
		} else {
			onResultError()
		}
	}
	
	private fun isResultSuccess(result: Result<List<BeerModel>>) =
			result.resultType == ResultType.SUCCESS
	
	private fun onResultSuccess(beersModel: List<BeerModel>?) {
		val beers = beersModel?.toBeerUIList() ?: emptyList()
		if (beers.isEmpty())
			statusLiveData.postValue(EmptyData())
		else
			statusLiveData.postValue(GettedData(beers))
	}
	
	private fun onResultError() {
		statusLiveData.postValue(Error())
	}
	
	private fun isLoadingLiveData() {
		statusLiveData.postValue(Loading())
	}
}

sealed class ModelStatus
data class GettedData(val data: List<BeerUI>) : ModelStatus()
class Error : ModelStatus()
class Loading : ModelStatus()
class EmptyData : ModelStatus()
