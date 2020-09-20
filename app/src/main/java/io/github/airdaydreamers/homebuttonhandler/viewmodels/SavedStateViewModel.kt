package io.github.airdaydreamers.homebuttonhandler.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SavedStateViewModel(savedStateHandle: SavedStateHandle?) : ViewModel() {
    companion object {
        private const val STATE_KEY = "state"
    }

    private var mState: SavedStateHandle? = savedStateHandle

    private val mActivityState =
        MutableLiveData<Int>() //NOTES: LiveData is overmuch. But I want to show how to use.

    fun getActivityState(): LiveData<Int>? {
        return mState?.getLiveData(STATE_KEY)
    }

    fun saveActivityState(state: Int) {
        mState?.set(STATE_KEY, state)
    }

    override fun onCleared() {
        super.onCleared()
        //TODO: clear what do you need.
    }
}