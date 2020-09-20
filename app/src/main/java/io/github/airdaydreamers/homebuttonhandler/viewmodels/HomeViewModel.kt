package io.github.airdaydreamers.homebuttonhandler.viewmodels

import androidx.lifecycle.ViewModel
import io.github.airdaydreamers.homebuttonhandler.data.SharedRepository

class HomeViewModel(private var repository: SharedRepository) : ViewModel() {

    fun getActiveState(): Int {
        return repository.state
    }

    fun setActiveState(state: Int) {
        repository.state = state
    }
}