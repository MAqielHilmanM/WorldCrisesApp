package gits.internship.worldcrisesapp.data.source.local

import android.content.SharedPreferences
import android.preference.Preference
import android.support.annotation.VisibleForTesting
import gits.internship.worldcrisesapp.data.source.CrisesDataSource

class CrisesLocalDataSource private constructor(
        private val preference: SharedPreferences
) : CrisesDataSource {
    override fun getCrisesDetail(callback: CrisesDataSource.GetCrisesDetailCallback, id: String) {
        // NOTHING
    }

    override fun getCrises(callback: CrisesDataSource.GetCrisesCallback) {
        //NOTHING
    }

    companion object {
        private var INSTANCE : CrisesLocalDataSource? = null

        @JvmStatic
        fun getInstance(preference: SharedPreferences):CrisesLocalDataSource? {
            if(INSTANCE == null){
                synchronized(CrisesLocalDataSource::class.java){
                    INSTANCE = CrisesLocalDataSource(preference)
                }
            }
            return INSTANCE
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}