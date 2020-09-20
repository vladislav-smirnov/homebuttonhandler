package io.github.airdaydreamers.homebuttonhandler.data

class SharedRepository {
    var state = -1 //TODO: make clear and better //Notes: stay for dev work

    companion object {

        private var INSTANCE: SharedRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         *
         * @return the [SharedRepository] instance
         */
        @JvmStatic
        fun getInstance() = INSTANCE ?: synchronized(SharedRepository::class.java) {
            INSTANCE ?: SharedRepository().also { INSTANCE = it }
        }


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}