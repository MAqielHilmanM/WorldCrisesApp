package gits.internship.worldcrisesapp.data.source

import gits.internship.worldcrisesapp.data.Crises
import gits.internship.worldcrisesapp.data.source.local.CrisesLocalDataSource
import gits.internship.worldcrisesapp.data.source.remote.CrisesRemoteDataSource

class CrisesRepository(
        val remoteDataSource: CrisesDataSource,
        val localDataSource: CrisesLocalDataSource
) : CrisesDataSource {

    override fun getCrises(callback: CrisesDataSource.GetCrisesCallback) {
        remoteDataSource.getCrises(object : CrisesDataSource.GetCrisesCallback {
            override fun onCrisesLoaded(crises: MutableList<Crises>?) {
                callback.onCrisesLoaded(crises)
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

            override fun onNotAvailable() {
                callback.onNotAvailable()
            }
        })
    }

    companion object {
        private var INSTANCE: CrisesRepository? = null

        @JvmStatic
        fun getInstance(crisesRemoteDataSource: CrisesDataSource, crisesLocalDataSource: CrisesLocalDataSource) =
                INSTANCE ?: synchronized(CrisesRepository::class.java) {
                    INSTANCE ?: CrisesRepository(crisesRemoteDataSource, crisesLocalDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
