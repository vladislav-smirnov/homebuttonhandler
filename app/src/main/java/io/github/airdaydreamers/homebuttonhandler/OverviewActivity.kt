package io.github.airdaydreamers.homebuttonhandler

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import io.github.airdaydreamers.homebuttonhandler.viewmodels.HomeViewModel
import io.github.airdaydreamers.homebuttonhandler.viewmodels.SavedStateViewModel

class OverviewActivity : AppCompatActivity() {
    private var mSavedStateViewModel: SavedStateViewModel? = null
    private var mHomeViewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)

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

        mSavedStateViewModel?.getActivityState()?.observe(this, {
            if (it == 0) {
                startActivity(Intent(this, HomeActivity::class.java))
                finishAndRemoveTask()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        finishAndRemoveTask() //Need to remove from recently stack at moment.
    }
}