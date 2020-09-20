package io.github.airdaydreamers.homebuttonhandler

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.airdaydreamers.homebuttonhandler.data.Injection
import io.github.airdaydreamers.homebuttonhandler.data.SharedRepository
import io.github.airdaydreamers.homebuttonhandler.viewmodels.HomeViewModel


@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory private constructor(
    private val sharedRepository: SharedRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(sharedRepository)
                //Notes: This code demonstrate that we can use different ViewModels with the same repository.
//                isAssignableFrom(<NameOfViewModel>::class.java) ->
//                    <NameOfViewModel>(sharedRepository)
                else ->
                    throw IllegalArgumentException("Unsupported ViewModel class: ${modelClass.name}")
            }
        } as T


    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: HomeViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(HomeViewModelFactory::class.java) {
                INSTANCE
                    ?: HomeViewModelFactory(Injection.provideTasksRepository(application.applicationContext))
                        .also { INSTANCE = it }
            }

        @VisibleForTesting
        fun destroyInstance() { //It's need for tests.
            INSTANCE = null
        }
    }
}