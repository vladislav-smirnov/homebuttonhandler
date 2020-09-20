package io.github.airdaydreamers.homebuttonhandler.data

import android.content.Context

object Injection {

    fun provideTasksRepository(context: Context?): SharedRepository { //NOTES: I put context because have some plans for that.
        return SharedRepository.getInstance()
    }
}