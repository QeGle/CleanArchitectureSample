package com.qegle.cleanarchsample.vm.mapper

import com.qegle.cleanarchsample.domain.model.BeerModel
import com.qegle.cleanarchsample.vm.model.AbvType
import com.qegle.cleanarchsample.vm.model.BeerUI

fun List<BeerModel>.toBeerUIList() = this.map { it.toBeerUI() }

fun BeerModel.toBeerUI() = BeerUI(
		id = id,
		name = name,
		tagline = tagline,
		image = image,
		abv = abv,
		abvType = abv.toAbvType()
)

fun Double.toAbvType() =
		when {
			this < 5 -> AbvType.GREEN
			this >= 5 && this < 8 -> AbvType.ORANGE
			else -> AbvType.RED
		}

