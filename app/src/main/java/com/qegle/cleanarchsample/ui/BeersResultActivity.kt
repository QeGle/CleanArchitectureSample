package com.qegle.cleanarchsample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.qegle.cleanarchsample.R
import com.qegle.cleanarchsample.ui.adapterlist.BeersAdapter
import com.qegle.cleanarchsample.vm.*
import com.qegle.cleanarchsample.vm.model.BeerUI
import kotlinx.android.synthetic.main.activity_beers_results.*
import org.koin.android.ext.android.inject

class BeersResultActivity : AppCompatActivity() {
	
	private val viewModel: MealsByBeersViewModel by inject()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_beers_results)
		
		observerVMStatus()
	}
	
	private fun observerVMStatus() {
		viewModel.status.observe(this, Observer(::onStatusChanged))
	}
	
	private fun onStatusChanged(status: ModelStatus?) {
		when (status) {
			is GettedData -> onBeersReceived(status.data)
			is EmptyData -> onEmptyBeersReceived()
			is Loading -> onLoading(true)
			is Error -> onError()
			else -> {
			}
		}
	}
	
	private fun onBeersReceived(beers: List<BeerUI>) {
		if (beers.isEmpty()) {
		} else
			showBeers(beers)
		
		onLoading(false)
	}
	
	private fun showBeers(beersUI: List<BeerUI>?) {
		beersUI?.let {
			recycler_view_beers.layoutManager = LinearLayoutManager(this)
			
			val beersAdapter = BeersAdapter(it, this)
			recycler_view_beers.adapter = beersAdapter
			beersAdapter.updateAdapter(it)
			
			recycler_view_beers.setHasFixedSize(true)
		}
	}
	
	private fun onError() {
		// todo something
	}
	
	private fun onEmptyBeersReceived() {
		// todo something
	}
	
	private fun onLoading(isLoading: Boolean) {
		showSpinner(isLoading)
	}
	
	private fun showSpinner(isLoading: Boolean) {
		main_activity_spinner.apply {
			visibility = if (isLoading) View.VISIBLE else View.GONE
		}
	}
}
