package com.gklabs.earthquake.ui.earthquake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gklabs.earthquake.data.repository.EarthQuakeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EarthQuakesViewModel @Inject constructor(earthQuakeRepository: EarthQuakeRepository) :
    ViewModel() {

    val earthQuakes = earthQuakeRepository.getEarthQuakes(
        44.1,
        -9.9,
        -22.4,
        55.2
    ).asLiveData()
}