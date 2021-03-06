package io.github.airdaydreamers.homebuttonhandler

import android.app.UiModeManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import io.github.airdaydreamers.homebuttonhandler.viewmodels.HomeViewModel
import io.github.airdaydreamers.homebuttonhandler.viewmodels.SavedStateViewModel

class HomeActivity : AppCompatActivity() {
    private var mSavedStateViewModel: SavedStateViewModel? = null
    private var mHomeViewModel: HomeViewModel? = null

    private lateinit var mUiModeManager: UiModeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //region work with view
        findViewById<Button>(R.id.disable_mode_button).setOnClickListener { disableMode() }
        //endregion

        // Obtain the ViewModel
        //region is not google way
        //Notes: jsut for show how to use savedState with ViewModelStoreOwner
        mSavedStateViewModel =
            ViewModelProvider(
                application as HomeApplication,
                SavedStateViewModelFactory(application, this)
            ).get(SavedStateViewModel::class.java)
        //endregion

        //region Google way.
        mHomeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory.getInstance(application)
        ).get(HomeViewModel::class.java)
        //endregion

        //Enable car mode
        mUiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        mUiModeManager.enableCarMode(UiModeManager.ENABLE_CAR_MODE_GO_CAR_HOME)
    }

    override fun onResume() {
        super.onResume()

        mSavedStateViewModel?.saveActivityState(1)//TODO: change to const or emum or
        mHomeViewModel?.setActiveState(1)//TODO: change to const or emum or
    }

    override fun onStop() {
        super.onStop()

        mSavedStateViewModel?.saveActivityState(0) //TODO: change to const or emum or
        mHomeViewModel?.setActiveState(0) //TODO: change to const or emum or
    }

    fun disableMode() {
        //disable car mode
        mUiModeManager.disableCarMode(UiModeManager.DISABLE_CAR_MODE_GO_HOME)
    }
}