package io.github.airdaydreamers.homebuttonhandler

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class HomeApplication : Application(), ViewModelStoreOwner {
    //region ViewModelStoreOwner
    //TODO:Notes: This is not a good way. This is not a Google approach.
    /*
    * Google way: We need to use Factory and Repository.
    * Also we can use ktx but only for fragments and one activity.
    */
    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
    //endregion
}